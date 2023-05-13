package com.example.demo.services;

import com.example.demo.entities.Bakery;
import com.example.demo.repos.BakeryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BakeryService {
    private BakeryRepo bakeryRepo;
    @Autowired
    public BakeryService(BakeryRepo bR)
    {
        this.bakeryRepo = bR;
    }

    public Optional<Bakery> find(String name)
    {
        return this.bakeryRepo.findById(name);
    }

    public List<Bakery> findAll() { return this.bakeryRepo.findAll(); }

    @Transactional
    public Bakery create(Bakery bakery)
    {
        return this.bakeryRepo.save(bakery);
    }

    @Transactional
    public void update(Bakery bakery)
    {
        this.bakeryRepo.save(bakery);
    }

    @Transactional
    public void delete(String name)
    {
        this.bakeryRepo.deleteById(name);
    }

}
