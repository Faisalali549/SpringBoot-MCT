package com.example.recipeManagement.controller;

import com.example.recipeManagement.Utils.CommonUtils;
import com.example.recipeManagement.model.User;
import com.example.recipeManagement.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody String userData) {
        JSONObject isValid = CommonUtils.isValidUser(userData);
        User user = null;
        if(isValid.isEmpty()) {
            user = CommonUtils.setUser(userData);
            userService.save(user);
        }
        else {
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }
    @GetMapping("/user")
    public ResponseEntity<String> getUser(@Nullable @RequestParam String id) {
        JSONArray userDetails = userService.getUser(id);
        return new ResponseEntity<>(userDetails.toString(), HttpStatus.OK);
    }
    @DeleteMapping("delete-user/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }
    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody String userData) {
        JSONObject isValid = CommonUtils.isValidRecipe(userData);
        User user = null;
        if(isValid.isEmpty()) {
            user = CommonUtils.setUser(userData);
            userService.updateUser(id, user);
        }
        else {
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("user updated", HttpStatus.OK);
    }
}
