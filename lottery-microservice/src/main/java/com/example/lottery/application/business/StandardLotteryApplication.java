package com.example.lottery.application.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.lottery.application.LotteryApplication;
import com.example.lottery.dto.LotteryDTO;
import com.example.lottery.service.RandomNumberGenerator;

@Service
@RefreshScope
public class StandardLotteryApplication implements LotteryApplication {
	private final RandomNumberGenerator randomNumberGenerator;
	private final int lotteryMax;
	private final int lotterySize;

	public StandardLotteryApplication(RandomNumberGenerator randomNumberGenerator, 
			@Value("${lotteryMax}") int lotteryMax, 
			@Value("${lotterySize}") int lotterySize) {
		this.randomNumberGenerator = randomNumberGenerator;
		this.lotteryMax = lotteryMax;
		this.lotterySize = lotterySize;
	}

	@Override
	public LotteryDTO getLotteryNumbers() {
		return new LotteryDTO(IntStream.generate(() -> randomNumberGenerator.generate(lotteryMax))
				        .distinct()
				        .limit(lotterySize)
				        .sorted()
				        .boxed()
				        .toList());
	}

	@Override
	public List<LotteryDTO> getLotteryNumbers(int column) {
		return IntStream.range(0, column).mapToObj(i -> getLotteryNumbers()).toList();
	}

}
