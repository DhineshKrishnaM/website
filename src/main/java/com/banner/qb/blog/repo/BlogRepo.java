package com.banner.qb.blog.repo;

import com.banner.qb.blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<BlogEntity,Integer> {
}
