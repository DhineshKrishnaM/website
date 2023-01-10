package com.banner.qb.news_events.entity;

import com.banner.qb.commonentity.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Data
@Setter
@Getter
@Builder
@Entity
@Table(name = "newsAndEvents")
@NoArgsConstructor
@AllArgsConstructor
public class NewsAndEvents extends CommonEntity {
    private String topicText;
    private String subtext;
    private String redirectUrl;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, mappedBy = "news")
    private List<NewsEventImage> images;
}
