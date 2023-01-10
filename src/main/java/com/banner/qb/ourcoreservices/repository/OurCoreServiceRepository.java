package com.banner.qb.ourcoreservices.repository;

import com.banner.qb.ourcoreservices.entity.OurCoreServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OurCoreServiceRepository extends JpaRepository <OurCoreServicesEntity, Integer>{
}
