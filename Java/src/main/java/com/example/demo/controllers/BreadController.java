package com.example.demo.controllers;

import com.example.demo.entities.Bread;
import com.example.demo.requests.GetBreadResponse;
import com.example.demo.requests.GetBreadsResponse;
import com.example.demo.requests.PostBreadRequest;
import com.example.demo.requests.PutBreadRequest;
import com.example.demo.services.BakeryService;
import com.example.demo.services.BreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/breads")
public class BreadController {

    private BreadService breadService;
    private BakeryService bakeryService;

    @Autowired
    public BreadController(BreadService brS, BakeryService baS)
    {
        this.breadService = brS;
        this.bakeryService = baS;
    }

    @GetMapping("{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetBreadResponse> getBread(@PathVariable("name") String name)
    {
        return breadService.find(name).map(
                value -> ResponseEntity.ok(GetBreadResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetBreadsResponse> getCharacters()
    {
        List<Bread> all = breadService.findAll();
        Function<Collection<Bread>, GetBreadsResponse> mapper = GetBreadsResponse.entityToDtoMapper();
        GetBreadsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> postBread(@RequestBody PostBreadRequest request,
                                              UriComponentsBuilder builder)
    {
        Bread bread = PostBreadRequest
                .dtoToEntityMapper(name -> bakeryService.find(name).orElseThrow())
                .apply(request);
        bread = breadService.create(bread);
        return ResponseEntity.created(builder.pathSegment("api","breads","{name}")
                .buildAndExpand(bread.getName()).toUri()).build();
    }

    @PutMapping("{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> putBread(@RequestBody PutBreadRequest request, @PathVariable("name") String name)
    {
        Optional<Bread> bread = breadService.find(name);
        if(bread.isPresent())
        {
            PutBreadRequest.dtoToEntityMapper().apply(bread.get(), request);
            breadService.update(bread.get());
            return ResponseEntity.accepted().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> deleteBread(@PathVariable("name") String name)
    {
        Optional<Bread> bread = breadService.find(name);
        if(bread.isPresent())
        {
            breadService.delete(bread.get().getName());
            return ResponseEntity.accepted().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
