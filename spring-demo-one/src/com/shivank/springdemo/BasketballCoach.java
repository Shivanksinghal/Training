package com.shivank.springdemo;

public class BasketballCoach implements Coach {
	private FortuneService fortuneService;
	public BasketballCoach(FortuneService fortune) {
		fortuneService = fortune;
	}
	@Override
	public String getDailyWorkout() {
		return "30 minute running.";
	}
	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
}
