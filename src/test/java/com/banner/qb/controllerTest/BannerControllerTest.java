package com.banner.qb.controllerTest;

import com.banner.qb.banner.controller.BannerController;
import com.banner.qb.banner.dto.BannerDto;
import com.banner.qb.banner.entity.Banner;
import com.banner.qb.banner.service.BannerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BannerControllerTest {
    @InjectMocks
    BannerController bannerController;
    @Mock
    BannerServiceImpl bannerService;
    Banner banner = new Banner();
    BannerDto bannerDto = new BannerDto();
    List<Banner> list = new ArrayList<>();
    List<BannerDto> listDto = new ArrayList<>();

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

    @DisplayName("Create a new banner")
    @Test
    void createBanner() {
        Mockito.when(bannerService.createBanner(bannerDto)).thenReturn(bannerDto);
        Assertions.assertThat(bannerController.createBanner(bannerDto).getStatusCodeValue()).isEqualTo(201);
    }

    @DisplayName("To fetch all banner list")
    @Test
    void getAllBanner() {
        Mockito.when(bannerService.getAllList()).thenReturn(list);
        Assertions.assertThat(bannerController.allBannerList().getStatusCodeValue()).isEqualTo(200);
    }

    @DisplayName("To delete or remove banner")
    @Test
    void deleteBanner() {
        Mockito.when(bannerService.deleteExistingBanner(1)).thenReturn("Successfully deleted");
        Assertions.assertThat(bannerController.deleteBannerById(1)).isEqualTo("Successfully deleted");
    }


}
