package com.example.recipeManagement.service;

import com.example.recipeManagement.dao.CommentsRepository;
import com.example.recipeManagement.model.Comments;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    @Autowired
    CommentsRepository commentsRepository;
    public void save(Comments comment) {
        commentsRepository.save(comment);
    }

    public JSONArray getComment(String id) {
        JSONArray comments = new JSONArray();
        if(null != id && commentsRepository.findById(Integer.valueOf(id)).isPresent()) {
            Comments comment = commentsRepository.findById(Integer.valueOf(id)).get();
            JSONObject commentObj = setComment(comment);
            comments.put(commentObj);
        } else {
            List<Comments> commentsList = commentsRepository.findAll();
            for(Comments comments1 : commentsList) {
                JSONObject commentObj = setComment(comments1);
                comments.put(commentObj);
            }
        }
        return comments;
    }
    private JSONObject setComment(Comments comment) {
        JSONObject object = new JSONObject();
        object.put("commentId", comment.getCommentId());
        object.put("comment", comment.getComment());

        return object;
    }

    public void deleteComment(String id) {
        Comments comments = commentsRepository.findById(Integer.valueOf(id)).get();
        commentsRepository.delete(comments);
    }

    public void updateComment(String id, Comments comment) {
        Comments comments = commentsRepository.findById(Integer.valueOf(id)).get();
        commentsRepository.save(comment);
        commentsRepository.delete(comments);
    }
}
