package com.example.demo.controllers;

import com.example.demo.entities.File;
import com.example.demo.requests.FileResponseMessage;
import com.example.demo.requests.GetFilesResponse;
import com.example.demo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/files")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fS)
    {
        this.fileService = fS;
    }

    @PostMapping()
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<FileResponseMessage> uploadFile(@RequestParam("file") MultipartFile ffile)
    {
        String message;
        try
        {
            fileService.create(ffile.getOriginalFilename(), ffile.getContentType(), ffile.getBytes());
            message = "Uploaded the file successfully: " + ffile.getOriginalFilename();
            java.io.File file = new java.io.File(
                    "/home/node/app"
                    +ffile.getOriginalFilename());
            ffile.transferTo(file);
            return ResponseEntity.status(HttpStatus.OK).body(new FileResponseMessage(message));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e);
            message = "Could not upload the file: " + ffile.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileResponseMessage(message));
        }
    }

    @GetMapping("{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<byte[]> getFile(@PathVariable String id)
    {
        Optional<File> file = fileService.find(id);
        if(file.isPresent())
        {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + file.get().getName() + "\"")
                    .body(file.get().getContent());
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetFilesResponse> getBreads()
    {
        List<File> files = this.fileService.findAll();
        Function<Collection<File>, GetFilesResponse> mapper = GetFilesResponse.entityToDtoMapper();
        GetFilesResponse response = mapper.apply(files);
        return ResponseEntity.ok(response);
    }


}
