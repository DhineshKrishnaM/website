package com.banner.qb.blog.entity;

import com.banner.qb.commonentity.CommonEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog")
@NoArgsConstructor
@AllArgsConstructor
public class BlogEntity extends CommonEntity {
    private String topic;
    private String description;
    private String url;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "blog")
    private BlogImage blogImage;
}
