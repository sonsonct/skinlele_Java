package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Models.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{
    List<Products> findByNameContaining(@Param("nameprd") String nameprd);

}
