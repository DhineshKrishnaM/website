package com.banner.qb.partner.entity;

import com.banner.qb.commonentity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partner")
public class PartnerEntity extends CommonEntity {
    private String title;
    private String description;
//    @OneToMany(mappedBy = )
//    private List<PartnerImage> partnerImages;
}
