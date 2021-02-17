package com.shivank.springdemo;

public class HappyFortuneService implements FortuneService{
	@Override
	public String getFortune() {
		return "You are lucky today.";
	}
}
