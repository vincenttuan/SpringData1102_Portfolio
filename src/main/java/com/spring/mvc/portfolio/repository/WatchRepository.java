package com.spring.mvc.portfolio.repository;

import com.spring.mvc.portfolio.entities.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "watchRepository")
public interface WatchRepository extends JpaRepository<Watch, Integer>{

}