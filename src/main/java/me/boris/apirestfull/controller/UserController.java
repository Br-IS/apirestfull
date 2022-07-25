package me.boris.apirestfull.controller;

import me.boris.apirestfull.entity.User;
import me.boris.apirestfull.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //private User usuario;

    private Optional<User> userOptional;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Integer id) {
        userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User user) {
        userOptional = userService.findById(id);

        if (userOptional.isPresent()) {

            userOptional.get().setName(user.getName());
            userOptional.get().setPassword(user.getPassword());
            userOptional.get().setEmail(user.getEmail());
            userOptional.get().setStatus(user.isStatus());

            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<User> findAll() {
       // List<User> users = StreamSupport.stream(userService.findAll().spliterator(), false).collect(Collectors.toList());
        return StreamSupport.stream(userService.findAll().spliterator(), false).collect(Collectors.toList());
    }

}
