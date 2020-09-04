package com.spring.read.file.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.read.file.helper.ExcelHelper;
import com.spring.read.file.models.Tutorial;
import com.spring.read.file.repository.ExcelRepository;

@Service
public class ExcelService {

	@Autowired
	ExcelRepository repo;

	public void save(MultipartFile file) {
		try {
			List<Tutorial> tuto = ExcelHelper.excelToTutorals(file.getInputStream());
			repo.saveAll(tuto);

		} catch (Exception e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public List<Tutorial> getAllTutorial() {

		return repo.findAll();
	}
}
