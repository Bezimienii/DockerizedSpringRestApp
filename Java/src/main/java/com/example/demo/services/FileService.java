package com.example.demo.services;

import com.example.demo.entities.File;
import com.example.demo.repos.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private FileRepo fileRepo;

    @Autowired
    public FileService(FileRepo FR) { this.fileRepo = FR; }

    public Optional<File> find(String id)
    {
        return this.fileRepo.findById(id);
    }

    public List<File> findAll() {return this.fileRepo.findAll(); }

    @Transactional
    public File create(String filename, String contentType, byte[] lot) throws IOException
    {
        File file = new File().builder().name(filename).type(contentType)
                .content(lot).build();
        return this.fileRepo.save(file);
    }
}
