package com.vds.springdatajpa.repository;

import com.vds.springdatajpa.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
}
