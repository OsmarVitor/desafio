package com.challenge.test.api.controller;

import com.challenge.test.domain.model.User;
import com.challenge.test.domain.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private GenericService<User> userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User userSaved = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> find(@PathVariable Long userId) {
        User user = userService.find(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<User>> list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.list(page, size));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId,
                                    @RequestBody User user) {
        userService.update(userId, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/teste/{name}",method = RequestMethod.GET)
    public String getChamada(@PathVariable String name, ModelMap model) {
        return name;
    }

}
