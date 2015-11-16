package com.ghoon.webapp.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghoon.webapp.domain.UserInfo;
import com.ghoon.webapp.domain.UserType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-*.xml")
public class UserTypeTests {
	private static Logger logger = LoggerFactory.getLogger(UserTypeTests.class);
	
	@Test
	public void testSerializeAndDeserializeEnumAsInt() throws IOException {
		long userId = 5001L;
		String name = "Jihoon Kang";
		String email = "kang@ghoon.net";
		UserType type = UserType.Administrator;
		
		UserInfo user = new UserInfo(userId, name, email);
		UserDetail userDetail = new UserDetail(user, type);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonText = mapper.writeValueAsString(userDetail);
		
		logger.info("jsonText:{}", jsonText);
		UserDetail deserialized = mapper.readValue(jsonText, UserDetail.class);
		
		logger.info("object:{}", deserialized);
		assertEquals(deserialized, userDetail);
	}

	static class UserDetail {
		private UserInfo user;
		private UserType type;

		public UserDetail() {
		}
		
		public UserDetail(UserInfo user, UserType type) {
			this.user = user;
			this.type = type;
		}

		public UserInfo getUser() {
			return user;
		}

		public UserType getType() {
			return type;
		}

		@Override
		public String toString() {
			return String.format("UserDetail [user=%s, type=%s]", user, type);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserDetail other = (UserDetail) obj;
			if (type != other.type)
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}
	}
}
