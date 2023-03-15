package com.example.recipeManagement.service;

import com.example.recipeManagement.dao.UserRepository;
import com.example.recipeManagement.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public void save(User user) {
        userRepository.save(user);
    }

    public JSONArray getUser(String id) {
        JSONArray userArray = new JSONArray();
        if(null != id && userRepository.findById(Integer.valueOf(id)).isPresent()) {
            User user = userRepository.findById(Integer.valueOf(id)).get();
            JSONObject userObj = setUser(user);
            userArray.put(userObj);
        }
        else {
            List<User> userList = userRepository.findAll();
            for(User user : userList) {
                JSONObject userObj = setUser(user);
                userArray.put(userObj);
            }
        }
        return userArray;
    }
    private JSONObject setUser(User user) {
        JSONObject userObj = new JSONObject();
        userObj.put("userId", user.getUserId());
        userObj.put("name", user.getName());
        userObj.put("email", user.getEmail());
        userObj.put("phoneNumber", user.getPhoneNumber());
        userObj.put("gender", user.getGender());
        return userObj;
    }

    public void deleteUser(String id) {
        User user = userRepository.findById(Integer.valueOf(id)).get();
        userRepository.delete(user);
    }

    public void updateUser(String id, User user) {
        User user1 = userRepository.findById(Integer.valueOf(id)).get();
        userRepository.save(user);
        userRepository.delete(user1);
    }
}
