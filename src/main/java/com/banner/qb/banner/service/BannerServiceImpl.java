package com.banner.qb.banner.service;

import com.banner.qb.banner.dto.BannerDto;
import com.banner.qb.banner.entity.Banner;
import com.banner.qb.banner.entity.ImageEntity;
import com.banner.qb.banner.repository.BannerRepository;
import com.banner.qb.banner.repository.ImageRepository;
import com.banner.qb.utility.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Banner> getAllList() {
        log.info("Getting all banner list....");
        return bannerRepository.findAll();
    }

    @Override
    public BannerDto createBanner(BannerDto bannerDto) {
        log.info("Create a banner processing...");
        Banner banner = modelMapper.map(bannerDto, Banner.class);
        Banner banner1 = bannerRepository.save(banner);
        return modelMapper.map(banner1, BannerDto.class);
    }

    @Override
    public String deleteExistingBanner(int id) {
        log.info("Deleting existing banner...");
        Banner banner = bannerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.BANNER_NOT_FOUND));
        banner.setDeletedAt(LocalDate.now());
        bannerRepository.saveAndFlush(banner);
        return "Successfully deleted";
    }

    @Override
    public Banner getBannerById(int id) {
        log.info("Banner detail is processing...");
        return bannerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.BANNER_NOT_FOUND));
    }

    @Override
    public String uploadImage(int id, MultipartFile file) throws IOException {
        log.info("Image uploading...");
        Banner banner = bannerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.BANNER_NOT_FOUND));
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setData(file.getBytes());
        imageEntity.setFileName(file.getOriginalFilename());
        imageEntity.setBanner(banner);
        imageRepository.save(imageEntity);
        return "Image Uploaded Successfully";
    }

    @Override
    public String updateBannerDetails(BannerDto bannerDto) {
        Banner banner = modelMapper.map(bannerDto, Banner.class);
        banner.setDescription(bannerDto.getDescription());
        banner.setButtonName(bannerDto.getButtonName());
        banner.setUrl(bannerDto.getUrl());
        bannerRepository.save(banner);
        return "Updated banner details";
    }

    @Override
    public String updateImageByBannerId(int bannerId, MultipartFile file) throws IOException {
        Optional<Banner> bannerInfo = bannerRepository.findById(bannerId);
        if (bannerInfo.isPresent()) {
            ImageEntity imageEntity = bannerInfo.get().getImage();
            imageEntity.setData(file.getBytes());
            imageEntity.setFileName(file.getOriginalFilename());
            bannerInfo.get().setImage(imageEntity);
            imageRepository.save(imageEntity);
        }
        return "Image Updated Successfully...";
    }
}
