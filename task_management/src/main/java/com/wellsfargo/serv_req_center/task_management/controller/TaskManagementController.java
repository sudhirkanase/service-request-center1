package com.wellsfargo.serv_req_center.task_management.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wellsfargo.serv_req_center.task_management.beans.ServiceRequestTask;
import com.wellsfargo.serv_req_center.task_management.service.TaskManagementService;

@RestController
//@RequestMapping("")
public class TaskManagementController {

	@Autowired
	TaskManagementService taskService;

	// Temp path for file upload
	private static String UPLOAD_FOLDER = "file_upload";

	@GetMapping("/getServiceReqTasks")
	public ResponseEntity<List<ServiceRequestTask>> getServiceReqTasks() {
		return ResponseEntity.ok(taskService.getServiceReqTasks());
	}

	@PostMapping("/documentUpload")
	public ResponseEntity<String> documentUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			save(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	public void save(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			Path uploadFilePath = getUploadFilePath(file.getOriginalFilename());
			Files.write(uploadFilePath, bytes);
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	private Path getUploadFilePath(String fileName) throws IOException {
		
		// <TODO> File Folder existance should be
		Path fileUploadFolder = Paths
				.get(Paths.get(".").normalize().toAbsolutePath().getParent() + File.separator + UPLOAD_FOLDER);
		// Path path = Paths.get(Paths.get(".").normalize().toAbsolutePath()+ "\\test\\"
		// + file.getOriginalFilename());
		if (!Files.isDirectory(fileUploadFolder)) {
			Files.createDirectories(fileUploadFolder);
		}
		System.out.println("File upload path:- " + fileUploadFolder.toString() );
		Path uploadFilePath = Paths.get(fileUploadFolder + File.separator + fileName);
		return uploadFilePath;
	}

}
