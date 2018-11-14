package com.ola.qh;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest
public class TestCaseBase {
	
		@Autowired
		private WebApplicationContext wac;
		private MockMvc mockMvc;
		
		@Before
		public void init(){
			mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		}

		public MockMvc getMockMvc() {
			return mockMvc;
		}

		public void setMockMvc(MockMvc mockMvc) {
			this.mockMvc = mockMvc;
		}
		
}
