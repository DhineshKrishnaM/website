package com.banner.qb.ourcoreservices.controller;

import com.banner.qb.exceptions.OurCoreServiceException;
import com.banner.qb.ourcoreservices.dto.OurCoreServiceDto;
import com.banner.qb.ourcoreservices.entity.OurCoreServicesEntity;
import com.banner.qb.ourcoreservices.service.OurCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/OurCoreService")
public class OurCoreServiceController {
    @Autowired
    private OurCoreService ourCoreService;

    @GetMapping("/getAll")
    public ResponseEntity<List<OurCoreServicesEntity>> getAllServices() {
        return new ResponseEntity<>(ourCoreService.getAllOurCoreServices(), HttpStatus.OK);
    }

    @PostMapping("/createNewCoreService")
    public ResponseEntity<String> createNewCoreService(@RequestBody OurCoreServiceDto ourCoreServiceDto) {
        return new ResponseEntity<>(ourCoreService.createNewService(ourCoreServiceDto), HttpStatus.CREATED);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam int ourCoreServiceId, @RequestParam MultipartFile file) throws OurCoreServiceException, IOException {
        return new ResponseEntity<>(ourCoreService.uploadImageForCoreService(ourCoreServiceId, file), HttpStatus.OK);
    }

    @DeleteMapping("/deleteOurCoreServiceById")
    public ResponseEntity<String> deleteOurCoreServiceById(@RequestParam int ourCoreServiceId) throws OurCoreServiceException {
        return new ResponseEntity<>(ourCoreService.deleteOurCoreService(ourCoreServiceId), HttpStatus.OK);
    }
    @PutMapping("/updateCoreService")
    public ResponseEntity<String> updateCoreService(@RequestParam int coreServiceId,@RequestBody OurCoreServiceDto ourCoreServiceDto) throws OurCoreServiceException {
        return new ResponseEntity<>(ourCoreService.updateService(coreServiceId,ourCoreServiceDto), HttpStatus.CREATED);
    }


}
