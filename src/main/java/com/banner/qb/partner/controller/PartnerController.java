package com.banner.qb.partner.controller;

import com.banner.qb.partner.entity.PartnerEntity;
import com.banner.qb.partner.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @GetMapping("/getAllPartner")
    public List<PartnerEntity> getAll() {
        return partnerService.listOfPartners();
    }

    @DeleteMapping("/deletePartnerById")
    public String deletePartnerById(@RequestParam int partnerId) {
        return partnerService.deleteById(partnerId);
    }
}
