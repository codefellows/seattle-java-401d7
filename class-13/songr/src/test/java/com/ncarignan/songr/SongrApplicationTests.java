package com.ncarignan.songr;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

@SpringBootTest
class SongrApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@BeforeAll
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	

	@Test
	void contextLoads() {
	}

	@Test
	public void givenWac_whenServletContext_thenItProvidesGreetController() {

		this.mockMvc.perform(get("/")).andDo(print())
				.andExpect(view().name("home"))
				.andExpect(status().is(200))
				.andExpect(content().string(containsString("<li><a href=\"/pacMan\">pac Man</a></li>")))
	}



	@ExtendWith(SpringExtension.class)
	@ContextConfiguration(classes = { SongrApplicationTests.class }) // TODO: confirm
	public class GreetingsSpringTest {
		
	}
}
