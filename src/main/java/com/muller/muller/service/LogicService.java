package com.muller.muller.service;

import com.muller.muller.entity.Logic;
import com.muller.muller.repository.LogicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogicService {

    @Autowired
    private LogicRepository logicRepository;

    public void save(Logic logic){
        logicRepository.save(logic);
    }
}
