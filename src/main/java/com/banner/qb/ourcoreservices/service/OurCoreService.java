package com.banner.qb.ourcoreservices.service;

import com.banner.qb.exceptions.OurCoreServiceException;
import com.banner.qb.ourcoreservices.dto.OurCoreServiceDto;
import com.banner.qb.ourcoreservices.entity.OurCoreServicesEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface OurCoreService {
    List<OurCoreServicesEntity> getAllOurCoreServices();

    String createNewService(OurCoreServiceDto ourCoreServiceDto);

    String uploadImageForCoreService(int ourCoreServiceId, MultipartFile file ) throws OurCoreServiceException, IOException;

    String deleteOurCoreService(int ourCoreServiceId) throws OurCoreServiceException;

    String updateService(int coreServiceId, OurCoreServiceDto ourCoreServiceDto) throws OurCoreServiceException;
}
