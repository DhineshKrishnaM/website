package com.banner.qb.banner.entity;

import com.banner.qb.commonentity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at is null")
@Table(name = "images")
public class ImageEntity extends CommonEntity {
    private String fileName;
    @Lob
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banner_id", nullable = false, unique = true,referencedColumnName = "id")
    private Banner banner;
}
