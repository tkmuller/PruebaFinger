package com.muller.muller;

import com.muller.muller.entity.Logic;
import com.muller.muller.repository.LogicRepository;
import com.muller.muller.service.LogicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MullerApplicationTests {

	@Autowired
	private LogicService logicService;

	@Autowired
	private LogicRepository logicRepository;

	@Test
	public void contextLoads() {
		logicRepository.delete(logicRepository.getOne(1L));
	}

}
