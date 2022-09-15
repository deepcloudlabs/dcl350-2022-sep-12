package com.example.lottery.application;

import java.util.List;

import com.example.lottery.dto.LotteryDTO;

public interface LotteryApplication {
	LotteryDTO getLotteryNumbers();
	List<LotteryDTO> getLotteryNumbers(int column);
}
