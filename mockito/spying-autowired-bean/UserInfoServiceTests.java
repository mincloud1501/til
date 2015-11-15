package com.ghoon.webapp.service;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.ghoon.webapp.domain.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class UserInfoServiceTests {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceTests.class);

	@Autowired
	private UserInfoService userService;
	
	@Test
	public void test() {
		long userId = 1L;
		
		UserInfo userInfo = userService.getUserInfo(userId);
		assertSame(userInfo.getUserId(), userId);
		
		verify(userService).getUserInfo(userId);
	}
	
	@Configuration
	@ImportResource("classpath*:spring-*.xml")
	static class ContextConfiguration {
		@Bean
		@Primary
		public UserInfoService userInfoService(UserInfoService userInfoService) {
			return spy(userInfoService);
		}
	}
}
