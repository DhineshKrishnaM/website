package com.banner.qb.news_events.service;

import com.banner.qb.exceptions.NewsException;
import com.banner.qb.news_events.entity.NewsAndEvents;
import com.banner.qb.news_events.newsdto.NewsAndEventDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface NewsAndEventService {
    List<NewsAndEvents> getAllListOfNewsEvents();

    NewsAndEvents getById(int byId) throws NewsException;

    NewsAndEventDto createNewEvent(NewsAndEventDto newsAndEventDto);

    NewsAndEventDto updateNewsEvent(int updateId, NewsAndEventDto newsAndEventDto);

    String deleteNewsEvent(int deleteId);

    String uploadImage(int newsEventId,List<MultipartFile> file) throws NewsException, IOException;
}
