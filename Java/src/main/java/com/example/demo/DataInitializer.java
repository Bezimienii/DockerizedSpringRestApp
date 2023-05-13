package com.example.demo;

import com.example.demo.entities.Bakery;
import com.example.demo.entities.Bread;
import com.example.demo.services.BakeryService;
import com.example.demo.services.BreadService;
import com.example.demo.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DataInitializer {

    private BreadService breadService;
    private BakeryService bakeryService;
    private FileService fileService;

    @Autowired
    public DataInitializer(BreadService brS, BakeryService baS, FileService fS)
    {
        this.breadService = brS;
        this.bakeryService = baS;
        this.fileService = fS;
    }

    @PostConstruct
    private synchronized void init()
    {
        Bakery ba1 = Bakery.builder().name("Mielnik").numberclients(50).salesOfLastMonth(100).build();
        Bakery ba2 = Bakery.builder().name("Szydlowski").numberclients(62).salesOfLastMonth(98).build();

        Bread br1 = Bread.builder().name("Litvian").quantity(30).bakery(ba1).build();
        Bread br2 = Bread.builder().name("Pomerenian").quantity(20).bakery(ba1).build();
        Bread br3 = Bread.builder().name("Mediterranean").quantity(23).bakery(ba2).build();


        this.bakeryService.create(ba1);
        this.bakeryService.create(ba2);

        this.breadService.create(br1);
        this.breadService.create(br2);
        this.breadService.create(br3);

        java.io.File dir = new java.io.File("/home/node/app");
        String directory = "/home/node/app";
        if(dir.isDirectory())
        {
            String[] arr = dir.list();
            if(arr.length > 0)
            {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directory))) {
                    for (Path path : stream) {
                        if (!Files.isDirectory(path)) {
                            String readalllines = Files.readAllLines(path).get(0);
                            this.fileService.create(path.getFileName().toString(), Files.probeContentType(path),
                                    readalllines.getBytes());

                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }


    }
}
