package com.banner.qb.ourcoreservices.entity;

import com.banner.qb.banner.entity.ImageEntity;
import com.banner.qb.commonentity.CommonEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ourCoreServices")
public class OurCoreServicesEntity extends CommonEntity {
    private String tagName;
    private String title;
    private String description;
    private String buttonName;
    private String redirectUrl;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinColumn(name = "images_id",referencedColumnName = "id")
    private ImageEntity image;
}
