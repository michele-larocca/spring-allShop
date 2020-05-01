package it.allshop.service.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArticleWebService {

	@GetMapping("/test")
	public String getGreating() {
		return "Hello, this is a web service test!";
	}
}
