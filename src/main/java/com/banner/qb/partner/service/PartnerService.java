package com.banner.qb.partner.service;

import com.banner.qb.partner.entity.PartnerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerService {
    List<PartnerEntity> listOfPartners();

    String deleteById(int partnerId);
}
