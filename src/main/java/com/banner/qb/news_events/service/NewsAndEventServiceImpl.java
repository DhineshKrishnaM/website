package com.banner.qb.news_events.service;

import com.banner.qb.banner.entity.ImageEntity;
import com.banner.qb.exceptions.NewsException;
import com.banner.qb.news_events.entity.NewsAndEvents;
import com.banner.qb.news_events.entity.NewsEventImage;
import com.banner.qb.news_events.newsdto.NewsAndEventDto;
import com.banner.qb.news_events.repository.NewsAndEventRepo;
import com.banner.qb.news_events.repository.NewsEventImageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NewsAndEventServiceImpl implements NewsAndEventService {
    @Autowired
    private NewsAndEventRepo newsAndEventRepo;
    @Autowired
    private NewsEventImageRepo newsEventImageRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<NewsAndEvents> getAllListOfNewsEvents() {
        List<NewsAndEvents> list = newsAndEventRepo.findAll();
        return list;
    }


    @Override
    public NewsAndEvents getById(int byId) throws NewsException {
        NewsAndEvents news = newsAndEventRepo.findById(byId).orElseThrow(() -> new NewsException("nNEWS NOT AVAILABLE"));
        return news;
    }

    @Override
    public NewsAndEventDto createNewEvent(NewsAndEventDto newsAndEventDto) {
        NewsAndEvents newsEvent = modelMapper.map(newsAndEventDto, NewsAndEvents.class);
        NewsAndEvents event = newsAndEventRepo.save(newsEvent);
        return modelMapper.map(event, NewsAndEventDto.class);
    }

    @Override
    public NewsAndEventDto updateNewsEvent(int updateId, NewsAndEventDto newsAndEventDto) {
        Optional<NewsAndEvents> news = newsAndEventRepo.findById(updateId);
        if (news.isPresent()) {
            news.get().setSubtext(newsAndEventDto.getSubtext());
            news.get().setTopicText(newsAndEventDto.getTopicText());
            NewsAndEvents event = newsAndEventRepo.save(news.get());
            return modelMapper.map(event, NewsAndEventDto.class);
        }
        return null;
    }

    @Override
    public String deleteNewsEvent(int deleteId) {
        Optional<NewsAndEvents> news = newsAndEventRepo.findById(deleteId);
        if (news.isPresent()) {
            news.get().setDeletedAt(LocalDate.now());
            newsAndEventRepo.saveAndFlush(news.get());
            return "News And Event has been deleted successfully";
        }
        return null;
    }

    @Override
    public String uploadImage(int newsEventId, List<MultipartFile> file) throws NewsException, IOException {
        NewsAndEvents news = newsAndEventRepo.findById(newsEventId).orElseThrow(() -> new NewsException("NEWS AND EVENT DETAILS NOT AVAILABLE"));
        for (int i = 0; i < file.size(); i++) {
            NewsEventImage newsEventImage = new NewsEventImage();
            newsEventImage.setData(file.get(i).getBytes());
            newsEventImage.setFileName(file.get(i).getOriginalFilename());
            newsEventImage.setNews(news);
        }
        return "Images Uploaded";
    }
}
