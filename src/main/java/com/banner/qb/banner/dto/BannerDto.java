package com.banner.qb.banner.dto;

import com.banner.qb.commonentity.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {
    private String description;
    private String url;
    private String buttonName;
}
