// BlogService.java
package com.jedos.jedosbackend.service;

import com.jedos.jedosbackend.dto.BlogDTO;
import com.jedos.jedosbackend.dto.BlogDetailDTO;
import com.jedos.jedosbackend.model.Blog;
import com.jedos.jedosbackend.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<BlogDTO> getAllBlogs() {
        return blogRepository.findAll().stream().map(blog -> new BlogDTO(
                blog.getId(),
                blog.getCodBlog(),
                blog.getTitle(),
                blog.getImageUrl(),
                blog.getDescription(),
                blog.getVisitas(),
                blog.getCreatedAt()
        )).collect(Collectors.toList());
    }

    public BlogDetailDTO getBlogByCodBlog(String codBlog) {
        Blog blog = blogRepository.findByCodBlog(codBlog).orElseThrow(() -> new RuntimeException("Blog not found"));
        blog.setVisitas(blog.getVisitas() + 1);
        blogRepository.save(blog);
        return new BlogDetailDTO(
                blog.getId(),
                blog.getCodBlog(),
                blog.getTitle(),
                blog.getImageUrl(),
                blog.getDescription(),
                blog.getContent(),
                blog.getVisitas(),
                blog.getCreatedAt()
        );
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }
}