package com.muller.muller.repository;

import com.muller.muller.entity.Logic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogicRepository extends JpaRepository<Logic, Long> {
}
