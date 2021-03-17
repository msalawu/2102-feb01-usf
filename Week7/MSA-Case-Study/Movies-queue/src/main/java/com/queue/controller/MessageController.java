package com.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.queue.service.QueueService;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private QueueService qs;
	
	@GetMapping
	public String getMessage() {
		return qs.getMessage(); 
	}
	
	@PostMapping
	public String addMessage(@RequestBody String message) {
		qs.sendMessage(message);
		return "sent";
	}
}
