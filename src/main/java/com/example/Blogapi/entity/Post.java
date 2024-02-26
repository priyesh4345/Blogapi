package com.example.Blogapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class  Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title" , nullable = false)
    private String title;
    @Column(name = "content" , nullable = false)
    private String content;
    @Column(name = "description" , nullable = false)
    private String description;

    // One post can have multiple comments
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
