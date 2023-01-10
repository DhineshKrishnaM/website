package com.banner.qb.servicetest;

import com.banner.qb.banner.dto.BannerDto;
import com.banner.qb.banner.entity.Banner;
import com.banner.qb.banner.repository.BannerRepository;
import com.banner.qb.banner.service.BannerServiceImpl;
import com.banner.qb.commonentity.CommonEntity;
import com.banner.qb.exceptions.BannerException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BannerServiceTest {
    @Mock
    BannerRepository bannerRepository;
    Banner banner = new Banner();
    BannerDto bannerDto = new BannerDto();
    List<Banner> list = Arrays.asList(banner);
    List<BannerDto> listDto = new ArrayList<>();
    @InjectMocks
    private BannerServiceImpl bannerService;
    @Mock
    private ModelMapper modelMapper;

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

    @Test
    void createBanner() {
        Mockito.when(modelMapper.map(bannerDto, Banner.class)).thenReturn(banner);
        Mockito.when(bannerRepository.save(banner)).thenReturn(banner);
        Mockito.when(modelMapper.map(banner, BannerDto.class)).thenReturn(bannerDto);
        Assertions.assertThat(bannerService.createBanner(bannerDto)).isEqualTo(bannerDto);
    }

    @Test
    void getAllBannerList() {
        Mockito.when(bannerRepository.findAll()).thenReturn(list);
        Assertions.assertThat(bannerService.getAllList()).hasSize(1);
    }

    @Test
    void deleteExistingBanner() {
        Mockito.when(bannerRepository.findById(1)).thenReturn(Optional.of(banner));
        Mockito.when(bannerRepository.saveAndFlush(banner)).thenReturn(banner);
        Assertions.assertThat(bannerService.deleteExistingBanner(1)).isEqualTo("Successfully deleted");
    }

    @Test
    void getBannerById() throws BannerException {
        Mockito.when(bannerRepository.findById(1)).thenReturn(Optional.of(banner));
        Assertions.assertThat(bannerService.getBannerById(1)).isEqualTo(banner);
    }
}
