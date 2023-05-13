package com.example.demo.services;

import com.example.demo.entities.Bakery;
import com.example.demo.entities.Bread;
import com.example.demo.repos.BreadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BreadService {
    private BreadRepo breadRepo;

    @Autowired
    public BreadService(BreadRepo bR)
    {
        this.breadRepo = bR;
    }

    public Optional<Bread> find(String name)
    {
        return this.breadRepo.findById(name);
    }

    public List<Bread> findAll()
    {
        return this.breadRepo.findAll();
    }

    public List<Bread> findAll(Bakery bakery) { return this.breadRepo.findAllByBakery(bakery); }

    @Transactional
    public Bread create(Bread bread)
    {
        return this.breadRepo.save(bread);
    }

    @Transactional
    public void update(Bread bread)
    {
        this.breadRepo.save(bread);
    }

    @Transactional
    public void delete(String name)
    {
        this.breadRepo.deleteById(name);
    }

}
