package com.lschaan.feign;

import feign.Feign;
import feign.gson.GsonDecoder;

public class Main {
	public static void main(String... args) {
		GitHub github = Feign.builder().decoder(new GsonDecoder()).target(GitHub.class, "https://api.github.com/");

		GitHubObj githubObj = github.repAmount("lschaan", "data-analysis").get(0);
		System.out.println(githubObj.getLogin() + " (" + githubObj.getUrl() + ") ");

	}

}