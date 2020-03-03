package com.dfc;

import com.dfc.entity.Signin;
import com.dfc.service.SigninService;
import com.dfc.util.HttpsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private SigninService signinService;

	@Test
	public void contextLoads() {
		Signin signin = new Signin();
		signin.setCourseCode(8816);
        signinService.save(signin);


	}

}
