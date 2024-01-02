package com.example.demo.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
	@GetMapping("/getall")
	String Hello(){
		return "ajshbdja";
	}

}
