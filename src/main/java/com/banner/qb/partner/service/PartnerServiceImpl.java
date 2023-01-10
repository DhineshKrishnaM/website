package com.banner.qb.partner.service;

import com.banner.qb.partner.entity.PartnerEntity;
import com.banner.qb.partner.repo.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerServiceImpl implements PartnerService{
    @Autowired
    private PartnerRepository partnerRepository;
    @Override
    public List<PartnerEntity> listOfPartners() {
        return partnerRepository.findAll();
    }

    @Override
    public String deleteById(int partnerId) {
        Optional<PartnerEntity> partner = partnerRepository.findById(partnerId);
        if(partner.isPresent()){
            partner.get().setDeletedAt(LocalDate.now());
            partnerRepository.saveAndFlush(partner.get());
            return "Deleted Successfully";
        }
        return "Deletion is not completed";
    }
}
