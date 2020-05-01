package it.allshop.service.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import it.allshop.service.app.ArticleWebService;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleWebServiceTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ArticleWebService service;
	
	@Test
	public void serviceShouldNotBeNull() {
		assertThat(service).isNotNull();
	}
	
	@Test
	public void getGreatingTest() throws Exception {
		mvc.perform(get("/api/test").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").value("Hello, this is a web service test!"))
		.andDo(print());
		assertTrue(true);
	}
}
