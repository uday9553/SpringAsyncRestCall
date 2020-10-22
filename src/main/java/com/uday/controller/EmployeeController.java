package com.uday.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uday.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	public EmployeeService employeeService;
	
	@GetMapping("/asyncCall")
	public List<Object> getComments(){
		long time=System.currentTimeMillis();
		System.out.println("before time is"+time);
		CompletableFuture<List<Object>> objectsList =employeeService.getEmployees();
		List<Object> list=null;
		try {
			list=objectsList.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after time is  "+(System.currentTimeMillis()-time)+"  list size is "+list.size());
		return list;
	}
	
	@GetMapping("/asyncCall1")
	public List<Object> getEmployessAndComments(){
		long time=System.currentTimeMillis();
		System.out.println("before time is"+time);
		CompletableFuture<List<Object>> commentsList =employeeService.getComments();
		CompletableFuture<List<Object>> employeesList =employeeService.getEmployees();
		List<Object> list=null;
		try {			
			list=new ArrayList<>();
			list.addAll(commentsList.get());
			list.addAll(employeesList.get());
		} catch (ExecutionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after time is  "+(System.currentTimeMillis()-time)+"  list size is "+list.size());
		return list;
	}

}
