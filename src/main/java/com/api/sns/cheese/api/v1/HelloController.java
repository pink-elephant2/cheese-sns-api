package com.api.sns.cheese.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.domain.Test;
import com.api.sns.cheese.domain.TestExample;
import com.api.sns.cheese.repository.TestMapper;

@CrossOrigin
@RestController
public class HelloController {

	@Autowired
	private TestMapper testMapper;

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello, World!";
	}

	@RequestMapping("/sqlsample")
	@ResponseBody
	public List<Test> sqlSample() {

		TestExample example = new TestExample();
		example.createCriteria().andIdIsNotNull();

		return testMapper.selectByExample(example);
	}
}
