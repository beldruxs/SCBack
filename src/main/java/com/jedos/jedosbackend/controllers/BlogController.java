// BlogController.java
package com.jedos.jedosbackend.controllers;

import com.jedos.jedosbackend.dto.BlogDTO;
import com.jedos.jedosbackend.dto.BlogDetailDTO;
import com.jedos.jedosbackend.model.Blog;
import com.jedos.jedosbackend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "*")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<BlogDTO>> getAllBlogs() {
        List<BlogDTO> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{codBlog}")
    public ResponseEntity<BlogDetailDTO> getBlogByCodBlog(@PathVariable String codBlog) {
        BlogDetailDTO blog = blogService.getBlogByCodBlog(codBlog);
        return ResponseEntity.ok(blog);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.createBlog(blog);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }
}