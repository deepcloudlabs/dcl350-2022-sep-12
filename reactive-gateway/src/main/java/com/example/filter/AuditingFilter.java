package com.example.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class AuditingFilter implements GlobalFilter {
//TODO: order?, caching filter example?
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		System.out.println("Path        :\t%s.".formatted(request.getPath()));
		System.out.println("Query Params:\t%s.".formatted(request.getQueryParams()));
		request.getHeaders()
		       .forEach((headerName,headerValue) -> System.out.println("%24s: %s.".formatted(headerName,headerValue)));
		request.getBody()
		       .subscribe(System.out::println);
		var out = chain.filter(exchange);
		var response = exchange.getResponse();
		System.out.println(response);
		return out;
	}

}
