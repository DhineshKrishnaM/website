package com.banner.qb.news_events.entity;

import com.banner.qb.commonentity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "eventImage")
@NoArgsConstructor
@AllArgsConstructor
public class NewsEventImage extends CommonEntity {
    private String fileName;
    private byte[] data;
    @ManyToOne
    @JoinColumn(name = "images_id")
    private NewsAndEvents news;
}
