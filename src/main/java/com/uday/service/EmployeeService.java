package com.uday.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {
	RestTemplate restTemplate = new RestTemplate();
	
		
		@Async
	    public CompletableFuture<List<Object>> getComments() {
	        String url = "https://jsonplaceholder.typicode.com/comments";	        
	        Object[] response = restTemplate.getForObject(url, Object[].class);
	        return CompletableFuture.completedFuture(Arrays.asList(response));
	    }

	    @Async
	    public CompletableFuture<List<Object>> getEmployees() {
	        String url = "https://jsonplaceholder.typicode.com/comments";
	        Object[] response = restTemplate.getForObject(url, Object[].class);
	        return CompletableFuture.completedFuture(Arrays.asList(response));
	    }
}
