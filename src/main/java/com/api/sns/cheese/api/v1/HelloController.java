package com.api.sns.cheese.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.domain.DTranscodeResult;
import com.api.sns.cheese.domain.Test;
import com.api.sns.cheese.domain.TestExample;
import com.api.sns.cheese.repository.DTranscodeResultRepository;
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

	@Autowired
	private DTranscodeResultRepository repository;

	@RequestMapping("/trans")
	public String trans() {
		List<DTranscodeResult> result = repository.findByFileKey("1QQtEcKD0u");
		result.stream().forEach(r -> {
			System.out.println(r.getId());
			System.out.println(r.getFileKey());
			System.out.println(r.getInputKey());
			System.out.println(r.getOutputKey());
			System.out.println(r.getCreatedAt());
		});
		System.out.println(String.valueOf(result.size()));

		return String.valueOf(repository.count());
	}
}
