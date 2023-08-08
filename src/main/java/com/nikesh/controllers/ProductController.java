package com.nikesh.controllers;

import com.nikesh.models.Category;
import com.nikesh.models.Product;
import com.nikesh.models.Response;
import com.nikesh.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Product request){
       Product product = this.productService.create(request);
        return new ResponseEntity<>(
               Response.builder()
                       .timeStamp(LocalDateTime.now())
                       .message("Data stored successfully")
                       .data(product)
                       .status(CREATED)
                       .statusCode(CREATED.value())
                       .build(),CREATED);
    }

    @GetMapping
    public ResponseEntity<Response> list(){
        List<Product> productList = this.productService.list();
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message(productList.isEmpty() ? "No Record Found!" : "Data listed successfully")
                        .data(productList)
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") Long id, @RequestBody Product request){
        Product product = this.productService.update(id, request);
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data updated successfully!")
                        .data(product)
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id){
        this.productService.delete(id);
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data deleted successfully")
                        .statusCode(OK.value())
                        .status(OK)
                        .build(), OK);
    }
}
