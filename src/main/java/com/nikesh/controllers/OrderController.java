package com.nikesh.controllers;

import com.nikesh.dto.order.CreateOrderRequest;
import com.nikesh.models.Order;
import com.nikesh.models.Response;
import com.nikesh.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Object> list(@RequestParam(defaultValue = "",name = "status") String status){
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data listed successfully")
                        .data(status.isEmpty() ? this.orderService.list() : this.orderService.getByStatus(status))
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateOrderRequest request){
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data stored successfully")
                        .data(this.orderService.create(request))
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
                        .data(this.orderService.getById(id))
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody CreateOrderRequest request){
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data updated successfully")
                        .data(this.orderService.update(id,request))
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        this.orderService.delete(id);
        return new ResponseEntity<>(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Data deleted successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .build(), OK);
    }

}
