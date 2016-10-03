package com.coding.house.store.test;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.coding.house.store.conf.AppWebConfiguration;
import com.coding.house.store.conf.DataSourceConfigurationTest;
import com.coding.house.store.conf.JPAConfiguration;
import com.coding.house.store.conf.SecurityConfiguration;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, SecurityConfiguration.class})
@ActiveProfiles("test")
public class ProductsControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
	    		.addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void mustReturnToHome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	@Test
	public void onlyAdmMustAccessProductForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/form")
						.with(SecurityMockMvcRequestPostProcessors
								.user("user@codinghouse.com")
								.password("123456")
								.roles("USER")))
						.andExpect(MockMvcResultMatchers.status().is(403));
	}
}
