package com.banner.qb.repositorytest;

import com.banner.qb.QbBannerApplication;
import com.banner.qb.blog.entity.BlogEntity;
import com.banner.qb.blog.entity.BlogImage;
import com.banner.qb.blog.repo.BlogRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = QbBannerApplication.class)
class BlogRepositoryTest {
    Date date;
    @Autowired
    private BlogRepo blogRepo;
    private BlogEntity blogEntity;
    private BlogImage blogImage;

    @BeforeEach
    void setup() {
        blogEntity=BlogEntity.builder()
                .topic("value")
                .description("val")
                .url("ww.com")
                .build();
    }

    @Test
    void save() {
        blogRepo.save(blogEntity);
        Assertions.assertThat(blogEntity.getId()).isPositive();
    }
    @Test
    void getAll(){
        List<BlogEntity> blog = blogRepo.findAll();
        Assertions.assertThat(blog).hasSize(1);
    }
    @Test
    void updateBlog(){
        Optional<BlogEntity> blogInfo = blogRepo.findById(1);
        blogInfo.get().setUrl("www.comEample.in");
        Assertions.assertThat(blogInfo.get().getId()).isEqualTo(blogInfo.get().getId());
    }
    @Test
    void deleteBlog(){
        BlogEntity blogEntity=new BlogEntity();
        blogRepo.delete(blogEntity);
        Assertions.assertThat(blogEntity.getId()).isZero();
    }

}
