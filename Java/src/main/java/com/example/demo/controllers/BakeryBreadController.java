package com.example.demo.controllers;

import com.example.demo.entities.Bakery;
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

import java.util.Optional;

@RestController
@RequestMapping("api/bakeries/{bakeryname}/breads")
public class BakeryBreadController {

    private BreadService breadService;
    private BakeryService bakeryService;

    @Autowired
    public BakeryBreadController(BreadService brS, BakeryService baS)
    {
        this.bakeryService = baS;
        this.breadService = brS;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetBreadsResponse> getBreads(@PathVariable("bakeryname") String bakeryname)
    {
        Optional<Bakery> bakery = bakeryService.find(bakeryname);
        return bakery.map(value -> ResponseEntity.ok(GetBreadsResponse.entityToDtoMapper()
                .apply(breadService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GetBreadResponse> getBread(@PathVariable("bakeryname") String bakeryname,
                                                     @PathVariable("name") String name)
    {
        return breadService.find(name).map(
                        value -> ResponseEntity.ok(GetBreadResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Void> postBread(@PathVariable("bakeryname") String bakeryname,
                                          @RequestBody PostBreadRequest request,
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
    public ResponseEntity<Void> putBread(@PathVariable("bakeryname") String bakeryname,
                                         @RequestBody PutBreadRequest request, @PathVariable("name") String name)
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
    public ResponseEntity<Void> deleteBread(@PathVariable("bakeryname") String bakeryname,
                                            @PathVariable("name") String name)
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
