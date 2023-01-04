package com.banner.qb.blog.controller;

import com.banner.qb.blog.dto.BlogDto;
import com.banner.qb.blog.entity.BlogEntity;
import com.banner.qb.blog.service.BlogService;
import com.banner.qb.exceptions.BlogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * Creating new blog based on passing BlogDto and returns BlogDto
     * @param blogDto
     */
    @PostMapping("/createBlog")
    public ResponseEntity<BlogDto> createNewBlog(@RequestBody BlogDto blogDto) {
        return new ResponseEntity<>(blogService.createBlog(blogDto), HttpStatus.CREATED);
    }

    /**
     * This method used to update the existing blog by passing BlogDto
     * @param blogId,BlogDto
     * @return "Updated successfully"
     * @throws BlogException
     */
    @PutMapping("/updateBlog/{blogId}")
    public ResponseEntity<String> updateBlog(@PathVariable("blogId") int blogId, @RequestBody BlogDto blogDto) throws BlogException {
        return new ResponseEntity<>(blogService.updateBlog(blogId, blogDto), HttpStatus.ACCEPTED);
    }

    /**
     * To delete the blog
     * @param blogId
     * @return
     */
    @DeleteMapping("/deleteBlog")
    public ResponseEntity<String> deleteBlog(@RequestParam int blogId) {
        return new ResponseEntity<>(blogService.deleteBlog(blogId), HttpStatus.OK);
    }

    /**
     * We can upload image based on by passing blogId
     * @param blogId,file
     * @return
     */
    @PostMapping("/{blogId}/uploadBlogImage")
    public String uploadBlogImage(@PathVariable("blogId") int blogId, @RequestParam MultipartFile file) {
        return blogService.imageUpload(blogId, file);
    }
    /**
     * To fetch all the Blogs are available in database
     */
    @GetMapping("/getAllBlogs")
    public ResponseEntity<List<BlogEntity>> list() {
        return new ResponseEntity<>(blogService.listOfBlog(), HttpStatus.OK);
    }

    /**
     * To update the blog image and returns "Updated successfully"
     * @param blogId,file
     * @throws IOException
     */
    @PutMapping("/{blogId}/updateBlogImage")
    public String updateBlogImage(@PathVariable("blogId") int blogId,@RequestParam MultipartFile file) throws IOException {
        return blogService.updateBlogImage(blogId,file);
    }
}
