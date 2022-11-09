package com.lschaan.feign;

import java.util.List;

import feign.Param;
import feign.RequestLine;

public interface GitHub {

	 @RequestLine("GET /repos/{owner}/{repo}/contributors")
	List<GitHubObj> repAmount(@Param("owner") String owner, @Param("repo") String repo);
}
