package com.example.demo.controllers;

import com.example.demo.entities.Bakery;
import com.example.demo.requests.GetBakeriesResponse;
import com.example.demo.requests.GetBakeryResponse;
import com.example.demo.requests.PostBakeryRequest;
import com.example.demo.requests.PutBakeryRequest;
import com.example.demo.services.BakeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/bakeries")
public class BakeryController {

    private BakeryService bakeryService;

    @Autowired
    public BakeryController(BakeryService baS)
    {
        this.bakeryService = baS;
    }

    @GetMapping("{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetBakeryResponse> getBakery(@PathVariable("name") String name)
    {
        return bakeryService.find(name)
                .map(value -> ResponseEntity.ok(GetBakeryResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetBakeriesResponse> getBakeries()
    {
        List<Bakery> all = bakeryService.findAll();
        Function<Collection<Bakery>, GetBakeriesResponse> mapper = GetBakeriesResponse.entityToDtoMapper();
        GetBakeriesResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> postBakery(@RequestBody PostBakeryRequest request,
                                           UriComponentsBuilder builder)
    {
        Bakery bakery = PostBakeryRequest
                .dtoToEntityMapper().apply(request);
        bakery = bakeryService.create(bakery);
        return ResponseEntity.created(builder.pathSegment("api","bakeries","{name}")
                .buildAndExpand(bakery.getName()).toUri()).build();
    }

    @PutMapping("{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> putBakery(@RequestBody PutBakeryRequest request,
                                          @PathVariable("name") String name)
    {
        Optional<Bakery> bakery = bakeryService.find(name);
        if(bakery.isPresent())
        {
            PutBakeryRequest.dtoToEntityMapper().apply(bakery.get(), request);
            bakeryService.update(bakery.get());
            return ResponseEntity.accepted().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{name}")
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8000"})
    public ResponseEntity<Void> deleteBakery(@PathVariable("name") String name)
    {
        Optional<Bakery> bakery = bakeryService.find(name);
        if(bakery.isPresent())
        {
            bakeryService.delete(name);
            return ResponseEntity.accepted().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
