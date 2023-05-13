package com.example.demo.repos;

import com.example.demo.entities.Bakery;
import com.example.demo.entities.Bread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreadRepo extends JpaRepository<Bread, String> {

    List<Bread> findAllByBakery(Bakery bakery);

}
