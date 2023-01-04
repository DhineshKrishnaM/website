package com.banner.qb.banner.entity;

import com.banner.qb.commonentity.CommonEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at is null")
@Table(name = "images")
public class ImageEntity extends CommonEntity {
    private String fileName;
    @Lob
    private byte[] data;

//    @JsonBackReference
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id",referencedColumnName = "id")
    private Banner banner;
}
