package com.example.recipeManagement.service;

import com.example.recipeManagement.Utils.CommonUtils;
import com.example.recipeManagement.dao.RecipeRepository;
import com.example.recipeManagement.model.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public JSONArray getRecipe(String id) {
        JSONArray recipeArray = new JSONArray();
        if(null != id && recipeRepository.findById(Integer.valueOf(id)).isPresent()) {
            Recipe recipe = recipeRepository.findById(Integer.valueOf(id)).get();
            JSONObject recipeObj = setRecipe(recipe);
            recipeArray.put(recipeObj);
        }
        else {
            List<Recipe> recipeList = recipeRepository.findAll();
            for(Recipe recipe : recipeList) {
                JSONObject recipeObj = setRecipe(recipe);
                recipeArray.put(recipeObj);
            }
        }
        return recipeArray;
    }
    private JSONObject setRecipe(Recipe recipe) {
        JSONObject recipeObj = new JSONObject();
        recipeObj.put("id", recipe.getId());
        recipeObj.put("recipeName", recipe.getRecipeName());
        recipeObj.put("ingredients", recipe.getIngredients());
        recipeObj.put("instructions", recipe.getInstructions());
        return recipeObj;
    }

    public void deleteRecipe(String id) {
        Recipe recipe = recipeRepository.findById(Integer.valueOf(id)).get();
        recipeRepository.delete(recipe);
    }

    public void updateRecipe(String id, Recipe recipe) {
        Recipe recipe1 = recipeRepository.findById(Integer.valueOf(id)).get();
        recipeRepository.save(recipe);
        recipeRepository.delete(recipe1);
    }
}
