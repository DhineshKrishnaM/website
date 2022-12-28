package com.banner.qb.banner.entity;

import com.banner.qb.commonentity.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Table(name="banner")
@Where(clause = "deleted_At is null")
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper=false)
@Builder
public class Banner extends CommonEntity {
    private String description;
    private String url;
    private String buttonName;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "banner")
    private ImageEntity image;
}