package com.soft1851.springboot.task.scheduling.dao;

import com.soft1851.springboot.task.scheduling.model.cascade.Cron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author 涛涛
 * @Date 2020/5/16 8:29
 * @Version 1.0
 **/
public interface CronRepository extends JpaRepository<Cron, Integer> {
    /**
     * 根据cronId
     *
     * @param cronId
     * @return
     */
    Cron findCronByCronIdEquals(int cronId);


//    @Modifying
//    @Transactional(rollbackFor = RuntimeException.class)
//    @Query(value = "update cron set cron= ?1 where cron_id=?2",nativeQuery = true)
//    void updateCron(String cron, int id);
    /**
     * 根据id修改cron
     * @param cron
     * @param id
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update cron set cron = ?1 where cron_id = ?2", nativeQuery = true)
    void updateCron(String cron, int id);
}
