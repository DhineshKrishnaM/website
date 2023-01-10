package com.banner.qb.banner.service;

import com.banner.qb.banner.entity.Banner;
import com.banner.qb.banner.dto.BannerDto;
import com.banner.qb.exceptions.BannerException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface BannerService {
    List<Banner> getAllList();

    BannerDto createBanner(BannerDto bannerDto);

    String deleteExistingBanner(int id);

    String uploadImage(int id, MultipartFile file) throws IOException;

    String updateBannerDetails(int bannerId,BannerDto bannerDto);

    String updateImageByBannerId(int bannerId, MultipartFile file) throws IOException;

    String deleteImage(int bannerId);

    Banner getBannerById(int bannerId) throws BannerException;
}
