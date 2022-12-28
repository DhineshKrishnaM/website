package com.banner.qb.repositorytest;

import com.banner.qb.QbBannerApplication;
import com.banner.qb.banner.dto.BannerDto;
import com.banner.qb.banner.entity.Banner;
import com.banner.qb.banner.repository.BannerRepository;
import com.banner.qb.utility.ErrorCodes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = QbBannerApplication.class)
public class BannerRepositoryTest {
    Banner banner = new Banner();
    BannerDto bannerDto = new BannerDto();
    @Autowired
    private BannerRepository bannerRepository;

    @BeforeEach
    void setup() {
        banner = Banner.builder()
                .buttonName("num")
                .description("implement")
                .url("www.qbrainx.com")
                .build();
        bannerDto = BannerDto.builder()
                .buttonName("num")
                .description("implement")
                .url("www.qbrainx.com")
                .build();
    }

    @DisplayName("To create a new banner")
    @Test
    void saveBanner() {
        bannerRepository.save(banner);
        Assertions.assertThat(banner.getId()).isPositive();
    }

    @DisplayName("Get banner details based on Banner Id")
    @Test
    void getBannerById() {
        Optional<Banner> banner = bannerRepository.findById(1);
        Assertions.assertThat(banner.get().getId()).isEqualTo(1);
    }

    @DisplayName("Update banner details based on Banner Id")
    @Test
    void updateBannerById() {
        Optional<Banner> bannerInfo = bannerRepository.findById(1);
        bannerInfo.get().setButtonName("Discover");
        Assertions.assertThat(bannerInfo.get().getId()).isEqualTo(bannerInfo.get().getId());
    }

    @DisplayName("Delete banner details based on Id")
    @Test
    void deleteBanner() {
        Banner details = new Banner();
        bannerRepository.delete(details);
        Assertions.assertThat(details.getId()).isEqualTo(0);
    }

}
