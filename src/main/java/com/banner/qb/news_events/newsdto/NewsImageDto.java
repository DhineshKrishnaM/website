package com.banner.qb.news_events.newsdto;

import com.banner.qb.commonentity.CommonEntity;
import com.banner.qb.news_events.entity.NewsAndEvents;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsImageDto extends CommonEntity {
    private String fileName;
    @Lob
    private byte[] data;
    @JsonIgnore
    @ManyToOne
    private NewsAndEvents news;

}
