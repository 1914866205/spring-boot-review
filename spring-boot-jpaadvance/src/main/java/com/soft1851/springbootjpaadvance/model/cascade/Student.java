package com.soft1851.springbootjpaadvance.model.cascade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 17:12
 * @Version 1.0
 **/
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name="Student.selectById",query = "select s from Student s where s.studentId=?1")
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Integer studentId;
    @Column(name="student_name",nullable = false,length = 32)
    private String studentName;
    @Column(name="hometown",nullable = true)
    private String hometown;
    @Column(name="birthday",nullable = true)
    private LocalDateTime birthday;

}
