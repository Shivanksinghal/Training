package com.shivank.springdemo;

public class TrackCoach implements Coach {
	private FortuneService fortuneService;
	
	public TrackCoach() {
		System.out.println("TrackCoach: inside no-arg constructor");
	}
	
	public TrackCoach(FortuneService fortune) {
		System.out.println("TrackCoach: inside parameterized constructor");
		fortuneService = fortune;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Try hard";
	}
	
	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	public void doMyStartupStuff() {
		System.out.println("TrackCoach: inside method init");
	}
	
	public void doMyCleanupStuff() {
		System.out.println("TrackCoach: inside method destroy");
	}
	

}
