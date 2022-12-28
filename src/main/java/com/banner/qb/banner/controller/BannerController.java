package com.banner.qb.banner.controller;

import com.banner.qb.banner.entity.Banner;
import com.banner.qb.banner.dto.BannerDto;
import com.banner.qb.banner.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/getAllBanners")
    public ResponseEntity<List<Banner>> allBannerList() {
        List<Banner> list = bannerService.getAllList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/createBanner")
    public ResponseEntity<BannerDto> createBanner(@RequestBody BannerDto bannerDto) {
        return new ResponseEntity<>(bannerService.createBanner(bannerDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBannerById")
    public String deleteBannerById(@RequestParam int id) {
        return bannerService.deleteExistingBanner(id);
    }

    @GetMapping("/getBannerById")
    public Banner getBanner(@RequestParam int id) {
        return bannerService.getBannerById(id);
    }

    @PostMapping("/uploadImageForId")
    public String uploadImage(@RequestParam int id, @RequestParam MultipartFile file) throws IOException {
        return bannerService.uploadImage(id, file);
    }
    @PutMapping("/updateBannerDetails")
    public String updateBannerDetails(@RequestBody BannerDto bannerDto){
        return bannerService.updateBannerDetails(bannerDto);
    }
    @PutMapping("/updateImageByBannerId")
    public String imageUpdateByBannerId(@RequestParam int bannerId,@RequestParam MultipartFile file) throws IOException {
        return bannerService.updateImageByBannerId(bannerId,file);
    }
}
