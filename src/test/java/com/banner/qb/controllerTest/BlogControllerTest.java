package com.banner.qb.controllerTest;

import com.banner.qb.blog.controller.BlogController;
import com.banner.qb.blog.dto.BlogDto;
import com.banner.qb.blog.entity.BlogEntity;
import com.banner.qb.blog.service.BlogServiceImpl;
import com.banner.qb.exceptions.BlogException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BlogControllerTest {
    @InjectMocks
    private BlogController blogController;
    @Mock
    private BlogServiceImpl blogService;
    BlogDto blogDto=new BlogDto();
    BlogEntity blogEntity=new BlogEntity();
    List<BlogEntity> list=new ArrayList<>();
    @Test
    void saveBlog(){
        Mockito.when(blogService.createBlog(blogDto)).thenReturn(blogDto);
        Assertions.assertThat(blogController.createNewBlog(blogDto).getStatusCodeValue()).isEqualTo(201);
    }
    @Test
    void getAll(){
        Mockito.when(blogService.listOfBlog()).thenReturn(list);
        Assertions.assertThat(blogController.list().getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    void updateBlog() throws BlogException {
        Mockito.when(blogService.updateBlog(1,blogDto)).thenReturn("Blog Image Updated successfully");
        Assertions.assertThat(blogController.updateBlog(1,blogDto).getStatusCodeValue()).isEqualTo(202);
    }
    @Test
    void deleteBlog(){
        Mockito.when(blogService.deleteBlog(1)).thenReturn("Blog Details Deleted Successfully");
        Assertions.assertThat(blogController.deleteBlog(1).getStatusCodeValue()).isEqualTo(200);
    }
}
