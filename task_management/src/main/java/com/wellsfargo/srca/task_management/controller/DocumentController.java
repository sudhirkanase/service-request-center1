package com.wellsfargo.srca.task_management.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wellsfargo.srca.task_management.beans.Document;
import com.wellsfargo.srca.task_management.service.DocumentService;
import com.wellsfargo.srca.task_management.service.TaskValidatorService;

@RestController
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    TaskValidatorService validatorService;

    // Temp path for file upload
    private static String UPLOAD_FOLDER = "file_upload";

    @PostMapping("/documentUpload")
    public ResponseEntity<List<Document>> documentUpload(@RequestParam("file") MultipartFile file,
                                                         @RequestParam("taskId") long taskId,
                                                         @RequestParam("documentTypeId") long documentTypeId,
                                                         @RequestParam("notes") String notes,
                                                         @RequestParam("accountNumber") long accountNumber) {
        String message = "";
        try {
            save(file);
            // saving document to ServiceRequestTask
            Document document = new Document();
            document.setDocumentTypeId(documentTypeId);
            document.setNotes(notes);
            document.setDocumentName(file.getOriginalFilename());
            document.setTaskId(taskId);
            document.setAccountNumber(accountNumber);
            return ResponseEntity.ok(documentService.saveDocument(document));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ArrayList<>());
        }
    }

    @PostMapping("/deleteDocument")
    public ResponseEntity<List<Document>> deleteDocument(@RequestBody long documentId) {
        return ResponseEntity.ok(documentService.deleteDocumentByDocumentId(documentId));
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

        // <TODO> File Folder existence should be
        Path fileUploadFolder = Paths
                .get(Paths.get(".").normalize().toAbsolutePath().getParent() + File.separator + UPLOAD_FOLDER);
        // Path path = Paths.get(Paths.get(".").normalize().toAbsolutePath()+ "\\test\\"
        // + file.getOriginalFilename());
        if (!Files.isDirectory(fileUploadFolder)) {
            Files.createDirectories(fileUploadFolder);
        }
        System.out.println("File upload path:- " + fileUploadFolder.toString());
        Path uploadFilePath = Paths.get(fileUploadFolder + File.separator + fileName);
        return uploadFilePath;
    }
}
