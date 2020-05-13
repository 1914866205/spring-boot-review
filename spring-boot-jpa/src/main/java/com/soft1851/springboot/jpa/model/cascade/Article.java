package com.soft1851.springboot.jpa.model.cascade;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 7:39
 * @Version 1.0
 **/
@Data
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长策略
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false,length = 50)//映射为字段，值不能为空
    private String title;

    @Lob//大对象，映射为MySQL的Long Text类型
    @Basic(fetch = FetchType.LAZY)//懒加载
    @Column(nullable = false)//映射为字段，值不能为空
    private String content;//文章全文内容

    /**
     * 可选属性  optional=false,表示author不能为空，删除文章，不影响用户
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name="author_id")//设置在article表中的关联字段(外键)
    private Author author;//所属作者


}
