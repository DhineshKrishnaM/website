package com.banner.qb.blog.entity;

import com.banner.qb.commonentity.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "blogimage")
@NoArgsConstructor
@AllArgsConstructor
public class BlogImage extends CommonEntity {
    private String fileName;
    @Lob
    private byte[] data;

    //    @JsonBackReference
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private BlogEntity blog;
}
