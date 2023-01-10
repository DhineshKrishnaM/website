package com.banner.qb.partner.repo;

import com.banner.qb.partner.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity ,Integer> {
}
