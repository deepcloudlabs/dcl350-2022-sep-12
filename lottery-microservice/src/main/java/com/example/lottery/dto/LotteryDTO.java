package com.example.lottery.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LotteryDTO {
	private List<Integer> numbers;
}
