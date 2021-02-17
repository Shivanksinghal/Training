package com.shivank.springdemo;

public class CricketCoach implements Coach {
	private FortuneService fortuneService;
	private String emailAddress;
	private String name;
	
	public CricketCoach() {
		System.out.println("CricketCoach: no-arg constructor");
	}
	
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice Batting";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
