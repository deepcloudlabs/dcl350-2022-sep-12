package com.example.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.reactivestreams.Publisher;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CachingFilter implements GlobalFilter, Ordered {
	private final CacheManager cacheManager;

	public CachingFilter(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		System.out.println("Path        :\t%s.".formatted(request.getPath()));
		final var cache = cacheManager.getCache("MyCache");

	    final var cachedRequest = getCachedRequest(exchange.getRequest());
	    int cachedRequestHashCode = Objects.hash(cachedRequest);
		if (Objects.nonNull(cache.get(cachedRequestHashCode))) {
	    	final var cachedResponse = cache.get(cachedRequestHashCode, CachedResponse.class);

	        final var serverHttpResponse = exchange.getResponse();
	        System.err.println("Cached response: %s".formatted(cachedResponse));
	        serverHttpResponse.setStatusCode(cachedResponse.httpStatus);
	        serverHttpResponse.getHeaders().addAll(cachedResponse.headers);
	        final var buffer = exchange.getResponse().bufferFactory().wrap(cachedResponse.body);
	        return exchange.getResponse().writeWith(Flux.just(buffer));
	    }

	    final var mutatedHttpResponse = getServerHttpResponse(exchange, cache, cachedRequest);
	    return chain.filter(exchange.mutate().response(mutatedHttpResponse).build());

	}

	@Override
	public int getOrder() {
		return -1;
	}

	private ServerHttpResponse getServerHttpResponse(ServerWebExchange exchange, Cache cache, CachedRequest cachedRequest) {
		final var originalResponse = exchange.getResponse();
		final var dataBufferFactory = originalResponse.bufferFactory();

		return new ServerHttpResponseDecorator(originalResponse) {

			@NonNull
			@Override
			public Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
				if (body instanceof Flux) {
					final var flux = (Flux<? extends DataBuffer>) body;
					return super.writeWith(flux.buffer().map(dataBuffers -> {
						final var outputStream = new ByteArrayOutputStream();
						dataBuffers.forEach(dataBuffer -> {
							final var responseContent = new byte[dataBuffer.readableByteCount()];
							dataBuffer.read(responseContent);
							try {
								outputStream.write(responseContent);
							} catch (IOException e) {
								throw new RuntimeException("Error while reading response stream", e);
							}
						});
						if (Objects.requireNonNull(getStatusCode()).is2xxSuccessful()) {
							final var cachedResponse = new CachedResponse(getStatusCode(), getHeaders(),
									outputStream.toByteArray());
							cache.put(Objects.hash(cachedRequest), cachedResponse);
						}
						return dataBufferFactory.wrap(outputStream.toByteArray());
					}));
				}
				return super.writeWith(body);
			}
		};
	}

	private CachedRequest getCachedRequest(ServerHttpRequest request) {
		return CachedRequest.builder().method(request.getMethod()).path(request.getPath())
				.queryParams(request.getQueryParams()).build();
	}

	@Value
	@Builder
	private static class CachedRequest {
		RequestPath path;
		HttpMethod method;
		MultiValueMap<String, String> queryParams;

	}

	@Value
	private static class CachedResponse {
		HttpStatus httpStatus;
		HttpHeaders headers;
		byte[] body;
	}
}
