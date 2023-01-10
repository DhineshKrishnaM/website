package com.banner.qb.blog.service;

import com.banner.qb.banner.entity.ImageEntity;
import com.banner.qb.banner.repository.ImageRepository;
import com.banner.qb.blog.dto.BlogDto;
import com.banner.qb.blog.entity.BlogEntity;
import com.banner.qb.blog.repo.BlogRepo;
import com.banner.qb.exceptions.BlogException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepo blogRepository;
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        BlogEntity blog = modelMapper.map(blogDto, BlogEntity.class);
        BlogEntity blog1 = blogRepository.save(blog);
        return modelMapper.map(blog1,BlogDto.class);
    }

    @Override
    public String updateBlog(int blogId, BlogDto blogDto) throws BlogException {
        BlogEntity blogInfo = blogRepository.findById(blogId).orElseThrow(() -> new BlogException("Blog Not Available") );
        blogInfo.setDescription(blogDto.getDescription());
        blogInfo.setUrl(blogDto.getUrl());
        blogInfo.setTopic(blogDto.getTopic());
        blogRepository.save(blogInfo);
        return "Blog Information Updated Successfully";
    }

    @Override
    public String deleteBlog(int blogId) {
        Optional<BlogEntity> blogDetails = blogRepository.findById(blogId);
        if(blogDetails.isPresent()){
            blogDetails.get().setDeletedAt(LocalDate.now());
            blogRepository.save(blogDetails.get());
            return "Blog Details Deleted Successfully";
        }
        return null;
    }

    @Override
    public String imageUpload(int blogId,MultipartFile file) {
        try{
            Optional<BlogEntity> blog = blogRepository.findById(blogId);
            if(blog.get().getBlogImage()==null){
                ImageEntity blogImage=new ImageEntity();
                blogImage.setData(file.getBytes());
                blogImage.setFileName(file.getOriginalFilename());
                ImageEntity bloImageDetails = imageRepo.save(blogImage);
                blog.get().setBlogImage(bloImageDetails);
                blogRepository.save(blog.get());
                return "Image uploaded successfully...";
            }
            if(blog.get().getBlogImage()!=null){
                ImageEntity blogImage1 = blog.get().getBlogImage();
                blogImage1.setData(file.getBytes());
                blogImage1.setFileName(file.getOriginalFilename());
                ImageEntity bloImageDetails = imageRepo.save(blogImage1);
                blog.get().setBlogImage(bloImageDetails);
                blogRepository.save(blog.get());
                return "Image Uploaded Successfully";
            }
        } catch (IOException e) {
            new BlogException("Image Not Uploaded");
        }
        return "Image Not uploaded";
    }

    @Override
    public List<BlogEntity> listOfBlog() {
        List<BlogEntity> blog = blogRepository.findAll();
        return blog;
    }

}
