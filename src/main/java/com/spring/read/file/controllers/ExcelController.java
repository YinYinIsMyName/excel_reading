package com.spring.read.file.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.read.file.helper.ExcelHelper;
import com.spring.read.file.models.Tutorial;
import com.spring.read.file.reponse.Reponse;
import com.spring.read.file.services.ExcelService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/excel")
public class ExcelController {

	@Autowired
	ExcelService fileService;

	@GetMapping(path = "/test")
	public void test() {
		System.out.println("hello");
	}

	@PostMapping(path = "/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

		String message = "";

		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				fileService.save(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new Reponse(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				System.out.println(e.getMessage());
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Reponse(message));
			}
		}

		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Reponse(message));
	}

	@GetMapping(path = "/tutorial")
	public ResponseEntity<List<Tutorial>> getAllTutorials() {
		try {
			List<Tutorial> tuto = fileService.getAllTutorial();

			if (tuto.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tuto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
