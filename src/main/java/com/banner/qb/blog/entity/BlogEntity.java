package com.banner.qb.blog.entity;

import com.banner.qb.banner.entity.ImageEntity;
import com.banner.qb.commonentity.CommonEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogEntity extends CommonEntity {
    private String topic;
    private String description;
    private String url;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "images_id",referencedColumnName = "id")
    private ImageEntity blogImage;
}
