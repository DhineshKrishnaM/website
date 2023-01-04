package com.banner.qb.blog.repo;

import com.banner.qb.blog.entity.BlogImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogImageRepo extends JpaRepository<BlogImage, Integer> {
}
