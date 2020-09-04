package com.spring.read.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.read.file.models.Tutorial;

public interface ExcelRepository extends JpaRepository<Tutorial, Long> {

}
