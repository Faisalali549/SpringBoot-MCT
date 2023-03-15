package com.example.recipeManagement.Utils;

import com.example.recipeManagement.model.Comments;
import com.example.recipeManagement.model.Recipe;
import com.example.recipeManagement.model.User;
import org.json.JSONObject;

public class CommonUtils {

    public static JSONObject isValidRecipe(String recipeData) {
        JSONObject object = new JSONObject(recipeData);
        JSONObject errorList = new JSONObject();
        if(object.has("recipeName")) {
            String name = object.getString("recipeName");
        }
        else {
            errorList.put("recipeName", "missing parameter");
        }
        if(object.has("ingredients")) {
            String ingredients = object.getString("ingredients");
        } else {
            errorList.put("ingredients", "missing parameter");
        }
        if(object.has("instructions")) {
            String instructions = object.getString("instructions");
        } else  {
            errorList.put("instructions", "missing parameters");
        }

        return errorList;
    }

    public static Recipe setRecipe(String recipeData) {
        JSONObject object = new JSONObject(recipeData);
        Recipe recipe = new Recipe();
        recipe.setRecipeName(object.getString("recipeName"));
        recipe.setIngredients(object.getString("ingredients"));
        recipe.setInstructions(object.getString("instructions"));

        return recipe;
    }

    public static User setUser(String userData) {
        JSONObject object = new JSONObject(userData);
        User user = new User();
        user.setName(object.getString("name"));
        user.setEmail(object.getString("email"));
        user.setPhoneNumber(object.getString("phoneNumber"));
        user.setGender(object.getString("gender"));
        return user;
    }

    public static JSONObject isValidUser(String userData) {
        JSONObject object = new JSONObject(userData);
        JSONObject errorList = new JSONObject();
        if(object.has("name")) {

        } else {
            errorList.put("name", "missing parameter");
        }
        if(object.has("email")) {

        } else {
            errorList.put("email", "missing parameter");
        }
        if(object.has("phoneNumber")) {

        } else {
            errorList.put("phoneNumber", "missing parameter");
        }
        if(object.has("gender")) {

        } else {
            errorList.put("gender", "missing parameter");
        }
        return errorList;
    }

    public static JSONObject isValidComment(String commentData) {
        JSONObject object = new JSONObject(commentData);
        JSONObject errorList = new JSONObject();
        if(object.has("comment")) {
            String comment = object.getString("comment");
        } else {
            errorList.put("comment", "missing parameter");
        }
        return errorList;
    }

    public static Comments setComment(String commentData) {
        JSONObject object = new JSONObject(commentData);
        Comments comments = new Comments();
        comments.setComment(object.getString("comment"));
        return comments;
    }
}
