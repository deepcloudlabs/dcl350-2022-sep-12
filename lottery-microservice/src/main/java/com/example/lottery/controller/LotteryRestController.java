package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.application.LotteryApplication;
import com.example.lottery.dto.LotteryDTO;

@RestController
@RequestScope
@RequestMapping("/numbers")
@Validated
@CrossOrigin
public class LotteryRestController {
	private final LotteryApplication lotteryApplication;
	@Value("${server.port}")
	private int serverPort;

	public LotteryRestController(LotteryApplication lotteryApplication) {
		this.lotteryApplication = lotteryApplication;
	}

	@GetMapping(params = "column")
	public List<LotteryDTO> drawNumbers(@RequestParam int column) {
		System.err.println("New request has arrived at port %d.".formatted(serverPort));
		return lotteryApplication.getLotteryNumbers(column);
	}
}
