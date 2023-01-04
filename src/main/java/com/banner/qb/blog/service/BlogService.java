package com.banner.qb.blog.service;

import com.banner.qb.blog.dto.BlogDto;
import com.banner.qb.blog.entity.BlogEntity;
import com.banner.qb.exceptions.BlogException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface BlogService {

    BlogDto createBlog(BlogDto blogDto);

    String updateBlog(int blogId, BlogDto blogDto) throws BlogException;

    String deleteBlog(int blogId);

    String imageUpload(int blogId,MultipartFile file);

    List<BlogEntity> listOfBlog();

    String updateBlogImage(int blogId, MultipartFile file) throws IOException;
}
