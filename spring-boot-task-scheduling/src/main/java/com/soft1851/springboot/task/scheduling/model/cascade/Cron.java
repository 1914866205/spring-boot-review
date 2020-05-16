package com.soft1851.springboot.task.scheduling.model.cascade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/16 8:20
 * @Version 1.0
 **/
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cronId;

    @Column(name = "crom_name", nullable = false, length = 30)
    private String cronName;

    @Column(name = "cron", nullable = false, length = 50)
    private String cron;

}
