package com.example.recipeManagement.controller;

import com.example.recipeManagement.Utils.CommonUtils;
import com.example.recipeManagement.model.Comments;
import com.example.recipeManagement.service.CommentsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentsController {
    @Autowired
    CommentsService commentsService;
    @PostMapping("/add-comment")
    public ResponseEntity<String> addComment(@RequestBody String commentData) {
        JSONObject isValid = CommonUtils.isValidComment(commentData);
        Comments comment = null;
        if(isValid.isEmpty()) {
            comment = CommonUtils.setComment(commentData);
            commentsService.save(comment);
        } else {
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("comment added", HttpStatus.OK);
    }
    @GetMapping("/get-comment")
    public ResponseEntity<String> getComment(@Nullable @RequestParam String id) {
        JSONArray comments = commentsService.getComment(id);
        return new ResponseEntity<>(comments.toString(), HttpStatus.OK);
    }
    @DeleteMapping("/delete-comment/id/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable String id) {
        commentsService.deleteComment(id);
        return new ResponseEntity<>("comment Deleted", HttpStatus.OK);
    }
    @PutMapping("/update-comment/id/{id}")
    public ResponseEntity<String> updateComment(@PathVariable String id, @RequestBody String commentData) {
        JSONObject isValid = CommonUtils.isValidComment(commentData);
        if(isValid.isEmpty()) {
            Comments comment = CommonUtils.setComment(commentData);
            commentsService.updateComment(id, comment);
        } else {
            return  new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("comment updated", HttpStatus.OK);
    }

}
