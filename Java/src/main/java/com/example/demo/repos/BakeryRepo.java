package com.example.demo.repos;

import com.example.demo.entities.Bakery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BakeryRepo extends JpaRepository<Bakery, String> {

}
