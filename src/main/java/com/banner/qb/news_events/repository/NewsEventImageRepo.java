package com.banner.qb.news_events.repository;

import com.banner.qb.news_events.entity.NewsEventImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsEventImageRepo extends JpaRepository<NewsEventImage,Integer> {
}
