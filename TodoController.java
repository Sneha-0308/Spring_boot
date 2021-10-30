package com.example.demotodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TodoController {
    @Autowired
    private TodoService service;
    // RESTful API methods for Retrieval operations

    @GetMapping("/todos")
    public List<Todo> list(){
        return service.listAll();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> get(@PathVariable Integer id) {
        try {
            Todo todo = service.get(id);
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/todos")
    public void add(@RequestBody Todo todo){
        service.save(todo);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> update(@RequestBody Todo todo, @PathVariable Integer id) {
        try {
            Todo existProduct = service.get(id);
            service.save(todo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/todos/{id}")
    public void delete(@PathVariable Integer id) {
    service.delete(id);
}
}
