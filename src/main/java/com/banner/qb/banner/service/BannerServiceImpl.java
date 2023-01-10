package com.banner.qb.banner.service;

import com.banner.qb.banner.dto.BannerDto;
import com.banner.qb.banner.entity.Banner;
import com.banner.qb.banner.entity.ImageEntity;
import com.banner.qb.banner.repository.BannerRepository;
import com.banner.qb.banner.repository.ImageRepository;
import com.banner.qb.exceptions.BannerException;
import com.banner.qb.utility.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
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
        Date date=new Date();
        banner.setCreatedAt(date);
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
    public String uploadImage(int id, MultipartFile file) throws IOException {
        log.info("Image uploading...");
        Banner banner = bannerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(ErrorCodes.BANNER_NOT_FOUND));
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setData(file.getBytes());
        imageEntity.setFileName(file.getOriginalFilename());
        ImageEntity image = imageRepository.save(imageEntity);
        banner.setImage(image);
        bannerRepository.save(banner);
        return "Image Uploaded Successfully";
    }

    @Override
    public String updateBannerDetails(int bannerId,BannerDto bannerDto) {
        Optional<Banner> banner1 = bannerRepository.findById(bannerId);
        if(banner1.isPresent()){
            banner1.get().setButtonName(bannerDto.getButtonName());
            banner1.get().setDescription(bannerDto.getDescription());
            banner1.get().setUrl(bannerDto.getUrl());
            bannerRepository.save(banner1.get());
            return "Updated banner details";
        }
        return "Not Updated banner details";
    }

    @Override
    public String updateImageByBannerId(int bannerId, MultipartFile file) throws IOException {
        Optional<Banner> bannerInfo = bannerRepository.findById(bannerId);
        if (bannerInfo.isPresent()) {
            ImageEntity imageEntity = bannerInfo.get().getImage();
            imageEntity.setData(file.getBytes());
            imageEntity.setFileName(file.getOriginalFilename());
            ImageEntity image = imageRepository.save(imageEntity);
            bannerInfo.get().setImage(image);
            bannerRepository.save(bannerInfo.get());
            return "Image uploaded successfully";
        }
        return "Image Not Updated...";
    }

    @Override
    public String deleteImage(int bannerId) {
        Optional<Banner> bannerInfo = bannerRepository.findById(bannerId);
        if(bannerInfo.isPresent()){
            ImageEntity image = bannerInfo.get().getImage();
            image.setDeletedAt(LocalDate.now());
            imageRepository.save(image);
            bannerInfo.get().setImage(null);
            bannerRepository.save(bannerInfo.get());
            return "Image deleted successfully";
        }
        return "Image is not deleted..";
    }

    @Override
    public Banner getBannerById(int bannerId) throws BannerException {
        Banner bannerInfo = bannerRepository.findById(bannerId).orElseThrow(() -> new BannerException("BANNER NOT AVAILABLE"));
        return bannerInfo;
    }

}
