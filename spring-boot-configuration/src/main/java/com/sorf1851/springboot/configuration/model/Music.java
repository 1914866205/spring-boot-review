package com.sorf1851.springboot.configuration.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/12 14:03
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "music")
public class Music {
    /**
     * 歌名
     */
    private String name;

    /**
     * 歌手名
     */
    private String artist;
    /**
     * 播放量
     */
    private Integer playCount;

    /**
     * 描述
     */
    private String description;

    /**
     * 评论列表
     */
    private List<String> comments;

    /**
     * 每月排名
     */
    private Map<String, Integer> rank;

}
