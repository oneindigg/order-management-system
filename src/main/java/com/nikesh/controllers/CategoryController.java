package com.nikesh.controllers;

import com.nikesh.models.Category;
import com.nikesh.models.Product;
import com.nikesh.models.Response;
import com.nikesh.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<Object> list(){
        List<Category> categoryList = this.categoryService.list();
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message(categoryList.isEmpty() ? "No Record Found" : "Data listed successfully")
                        .data(categoryList)
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }


    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Category request){
        Category category = this.categoryService.create(request);
        return new ResponseEntity<>(
                Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("Data stored successfully")
                .data(category)
                .status(CREATED)
                .statusCode(CREATED.value())
                .build(), CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data retrieved successfully")
                        .data(this.categoryService.getById(id))
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Category request){
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data updated successfully")
                        .data(this.categoryService.update(id, request))
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) throws Exception {
        this.categoryService.delete(id);
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data deleted successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

}
