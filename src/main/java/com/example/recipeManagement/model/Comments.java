package com.example.recipeManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;
    @Column(name = "comments")
    private String comment;

    @ManyToMany(cascade = CascadeType.ALL)
    List<User> userList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    List<Recipe> recipeList = new ArrayList<>();
}
