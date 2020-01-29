package com.ncarignan.pumas;

import com.ncarignan.pumas.controllers.ApplicationUserController;
import org.junit.jupiter.api.Test;
//import org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PumasApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicationUserController applicationUserController;

	@Test
	void contextLoads() {
//		assertThat
	}

	@Test
	public void shouldHaveAGoodHomePage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<h2> make new users here</h2>")))
				.andExpect(content().string(containsString("<h2> make new users here</h2>")))
				.andExpect(content().string(containsString("<h2> make new users here</h2>")))
				.andExpect(content().string(containsString("<h2> make new users here</h2>")))
				.andExpect(content().string(containsString("<form action=\"/signup\" method=\"POST\">")));
	}

}
