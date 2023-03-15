package com.example.recipeManagement.controller;

import com.example.recipeManagement.Utils.CommonUtils;
import com.example.recipeManagement.dao.UserRepository;
import com.example.recipeManagement.model.Recipe;
import com.example.recipeManagement.service.RecipeService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/add-recipe")
    public ResponseEntity<String> addRecipe(@RequestBody String recipeData) {
        JSONObject isValid = CommonUtils.isValidRecipe(recipeData);
        Recipe recipe = null;
        if(isValid.isEmpty()) {
            recipe = CommonUtils.setRecipe(recipeData);
            recipeService.save(recipe);
        }
        else {
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }
    @GetMapping("/get-recipe")
    public ResponseEntity<String> getRecipe(@Nullable @RequestParam String id) {
        JSONArray recipeDetails = recipeService.getRecipe(id);
        return new ResponseEntity<>(recipeDetails.toString(), HttpStatus.OK);
    }
    @DeleteMapping("/delete-recipe/id/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>("recipe deleted", HttpStatus.OK);
    }
    @PutMapping("/update-Recipe/id/{id}")
    public ResponseEntity<String> updateRecipe(@PathVariable String id, @RequestBody String recipeData) {
        JSONObject isValid = CommonUtils.isValidRecipe(recipeData);
        Recipe recipe = null;
        if(isValid.isEmpty()) {
            recipe = CommonUtils.setRecipe(recipeData);
            recipeService.updateRecipe(id, recipe);
        }
        else {
            return new ResponseEntity<>(isValid.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>("recipe updated", HttpStatus.OK);
    }

}
