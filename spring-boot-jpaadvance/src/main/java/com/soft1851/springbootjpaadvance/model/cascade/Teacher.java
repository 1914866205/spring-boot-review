package com.soft1851.springbootjpaadvance.model.cascade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/3/31 11:10
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;
    @Column(name="teacher_name",length = 32, nullable = false)
    private String teacherName;
    @Column(name="clazz_id")
    private Integer clazzId;
    @Transient
    private Clazz clazz;
}
