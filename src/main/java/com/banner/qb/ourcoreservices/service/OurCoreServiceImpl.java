package com.banner.qb.ourcoreservices.service;

import com.banner.qb.banner.entity.ImageEntity;
import com.banner.qb.banner.repository.ImageRepository;
import com.banner.qb.exceptions.OurCoreServiceException;
import com.banner.qb.ourcoreservices.dto.OurCoreServiceDto;
import com.banner.qb.ourcoreservices.entity.OurCoreServicesEntity;
import com.banner.qb.ourcoreservices.repository.OurCoreServiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class OurCoreServiceImpl implements OurCoreService {
    @Autowired
    private OurCoreServiceRepository ourCoreServiceRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<OurCoreServicesEntity> getAllOurCoreServices() {
        return ourCoreServiceRepository.findAll();
    }

    @Override
    public String createNewService(OurCoreServiceDto ourCoreServiceDto) {
        OurCoreServicesEntity ourCoreServices = modelMapper.map(ourCoreServiceDto, OurCoreServicesEntity.class);
        ourCoreServiceRepository.save(ourCoreServices);
        return "Created Successfully";
    }

    @Override
    public String uploadImageForCoreService(int ourCoreServiceId, MultipartFile file) throws OurCoreServiceException, IOException {
        OurCoreServicesEntity coreServices = ourCoreServiceRepository.findById(ourCoreServiceId).orElseThrow(() -> new OurCoreServiceException("OUR CORE SERVICE NOT AVAILABLE "));
        if (coreServices.getImage() == null) {
            ImageEntity image = new ImageEntity();
            image.setFileName(file.getOriginalFilename());
            image.setData(file.getBytes());
            ImageEntity savedImage = imageRepository.save(image);
            coreServices.setImage(savedImage);
            ourCoreServiceRepository.save(coreServices);
            return "Image has been Uploaded successfully..";
        }
        if(coreServices.getImage()!=null){
            ImageEntity imageInfo = coreServices.getImage();
            imageInfo.setFileName(file.getOriginalFilename());
            imageInfo.setData(file.getBytes());
            ImageEntity savedImage = imageRepository.save(imageInfo);
            coreServices.setImage(savedImage);
            ourCoreServiceRepository.save(coreServices);
            return "Image has been Uploaded successfully..";
        }
        return "Image is not uploaded...";
    }

    @Override
    public String deleteOurCoreService(int ourCoreServiceId) throws OurCoreServiceException {
        OurCoreServicesEntity coreServices = ourCoreServiceRepository.findById(ourCoreServiceId).orElseThrow(() -> new OurCoreServiceException("OUR CORE SERVICE NOT AVAILABLE "));
        coreServices.setDeletedAt(LocalDate.now());
        ourCoreServiceRepository.save(coreServices);
        return "Deletion Completed...";
    }

    @Override
    public String updateService(int coreServiceId, OurCoreServiceDto ourCoreServiceDto) throws OurCoreServiceException {
        OurCoreServicesEntity coreService = ourCoreServiceRepository.findById(coreServiceId).orElseThrow(() -> new OurCoreServiceException("OUR CORE SERVICE NOT AVAILABLE"));
        coreService.setDescription(ourCoreServiceDto.getDescription());
        coreService.setTitle(ourCoreServiceDto.getTitle());
        coreService.setButtonName(ourCoreServiceDto.getTagName());
        coreService.setRedirectUrl(ourCoreServiceDto.getRedirectUrl());
        coreService.setTagName(ourCoreServiceDto.getTagName());
        ourCoreServiceRepository.save(coreService);
        return "Updated Successfully....";
    }
}
