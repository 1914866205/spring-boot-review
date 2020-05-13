package com.soft1851.springboot.jpa.dao;

import com.soft1851.springboot.jpa.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.List;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/13 7:52
 * @Version 1.0
 **/
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    /**
     * 解析方法名创建查询，根据摘要查询
     *
     * @param blogSummary
     * @return
     */
    List<Blog> findByBlogSummary(String blogSummary);

    /**
     * 解析方法名创建查询，根据内容或摘要查询
     *
     * @param blogText
     * @param blogSummary
     * @return
     */
    List<Blog> findByBlogTextOrBlogSummary(String blogText, String blogSummary);

    /**
     * 解析方法名创建查询
     *
     * @param text 根据内容模糊查询
     * @return
     */
    List<Blog> findByBlogTextLike(String text);


    /**
     * 解析方法名创建查询，查询比知道maxId小的消息集合
     *
     * @param maxId
     * @return
     */
    List<Blog> findDistinctByBlogIdLessThan(int maxId);

    /**
     * JPQL查询，类似Hibernate的 HQL语法没在接口上使用 @Query
     *
     * @param blogId
     * @return
     */
    @Query("SELECT blogId,blogText,blogSummary FROM Blog WHERE blogId= ?1")
    Blog findById(int blogId);


    /**
     * 修改
     *
     * @param blogText
     * @param blogId
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeErrorException.class)
    @Query(value = "UPDATE blog SET blog_text=?1 WHERE blog_id < ?2", nativeQuery = true)
    int updateName(String blogText, int blogId);


    /**
     * 分页查询
     *
     * @param blogSummary
     * @param pageable
     * @return
     */
    @Query("SELECT b FROM Blog b WHERE b.blogSummary=?1")
    Page<Blog> findByName(String blogSummary, Pageable pageable);


    /**
     * 插入
     * @param blogText
     * @param blogSummary
     * @return
     */
    @Transactional(rollbackFor = RuntimeErrorException.class)
    @Modifying
    @Query(value = "INSERT INTO blog(blog_text,blog_summary)VALUES(:blogText,:blogSummary)", nativeQuery = true)
    Integer insertBlog(@Param("blogText") String blogText, @Param("blogSummary") String blogSummary);
}
// And： 等价于 SQL 中的 and 关键字，比如 findByMsgTextAndMsgSummary(String msgText, String msgSummary)；
// Or： 等价于 SQL 中的 or 关键字，比如 findByMsgTextOrMsgSummary(String msgText, String msgSummary)；
// Between： 等价于 SQL 中的 between 关键字，比如 findByMsgIdBetween(int max, int min)；
// LessThan： 等价于 SQL 中的 "<"
// GreaterThan： 等价于 SQL 中的">"
// IsNull： 等价于 SQL 中的 "is null"
// IsNotNull： 等价于 SQL 中的 "is not null"
// NotNull： 与 IsNotNull 等价；
// Like： 等价于 SQL 中的 "like"
// NotLike： 等价于 SQL 中的 "not like"
// OrderBy： 等价于 SQL 中的 "order by"
// Not： 等价于 SQL 中的 "！ ="
// In： 等价于 SQL 中的 "in"， 方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；
// NotIn： 等价于 SQL 中的 "not in"，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数；
