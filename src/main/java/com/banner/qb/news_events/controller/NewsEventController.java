package com.banner.qb.news_events.controller;

import com.banner.qb.exceptions.NewsException;
import com.banner.qb.news_events.entity.NewsAndEvents;
import com.banner.qb.news_events.newsdto.NewsAndEventDto;
import com.banner.qb.news_events.repository.NewsAndEventRepo;
import com.banner.qb.news_events.service.NewsAndEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/newsAndEvent")
public class NewsEventController {
    @Autowired
    private NewsAndEventService newsAndEventService;
    @Autowired
    private NewsAndEventRepo newsAndEventRepo;

    @GetMapping("/getAll")
    public ResponseEntity<List<NewsAndEvents>> getAllNewsAndEventsList() {
        return new ResponseEntity<>(newsAndEventService.getAllListOfNewsEvents(), HttpStatus.OK);
    }

    @GetMapping("/{byId}")
    public ResponseEntity<NewsAndEvents> getNewsAndEventById(@PathVariable("byId") int byId) throws NewsException {

        return new ResponseEntity<>(newsAndEventService.getById(byId), HttpStatus.OK);
    }

    @PostMapping("/addNewNewsEvent")
    public ResponseEntity<NewsAndEventDto> saveNewEvent(@RequestBody NewsAndEventDto newsAndEventDto) {
        return new ResponseEntity<>(newsAndEventService.createNewEvent(newsAndEventDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{updateId}")
    public ResponseEntity<NewsAndEventDto> updateEvent(@PathVariable("updateId") int updateId, @RequestBody NewsAndEventDto newsAndEventDto) {

        return new ResponseEntity<>(newsAndEventService.updateNewsEvent(updateId, newsAndEventDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteId")
    public ResponseEntity<String> deleteNewsEvent(@RequestParam int deleteId) {
        return new ResponseEntity<>(newsAndEventService.deleteNewsEvent(deleteId), HttpStatus.OK);
    }

    @PostMapping("/ImageUpload/{newsEventId}")
    public ResponseEntity<String> uploadImageForNewsEvent(@PathVariable("newsEventId") int newsEventId, @RequestParam List<MultipartFile> file) throws NewsException, IOException {
        return new ResponseEntity<>(newsAndEventService.uploadImage(newsEventId, file), HttpStatus.OK);
    }
}
