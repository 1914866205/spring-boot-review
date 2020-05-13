package com.soft1851.springbootjpaadvance.model.cascade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/3/31 11:28
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clazzId;
    @Column(length = 32, nullable = false)
    private String clazzName;
    @Column(length = 32,nullable = false)
    private Integer teacherId;
    @Transient
    private Teacher teacher;
    @Transient
    private List<Student> students;
}
