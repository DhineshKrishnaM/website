package com.banner.qb.news_events.newsdto;

import com.banner.qb.commonentity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsAndEventDto extends CommonEntity {
    private String topicText;
    private String subtext;
    @OneToMany
    private List<NewsImageDto> imageListDto;
}
