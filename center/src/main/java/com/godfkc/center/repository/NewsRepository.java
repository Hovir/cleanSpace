package com.godfkc.center.repository;

import com.godfkc.center.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
public interface NewsRepository extends JpaRepository<News, Long> {
    /**
     * @Author:lhy
     * @Data:2018/2/2 14:03
     * @Version:V1.0
     * @Des:后台新闻列表
     */
    Page<News> findByStatusAndTitleLike(Integer status, String title, Pageable pageable);

    /**
     * @Author:lhy
     * @Data:2018/2/2 14:03
     * @Version:V1.0
     * @Des:后台新闻列表,删除新闻功能
     */
    @Modifying
    @Query(value = "UPDATE News SET status = :status WHERE id = :id")
    void updateNewsById(@Param("id") Long id, @Param("status") int status);

    /**
     * @Author:lhy
     * @Data:2018/2/2 14:04
     * @Version:V1.0
     * @Des:后台查询单条新闻数据
     */
    News findNewsById(long id);

    /**
     * @Author:lhy
     * @Data:2018/2/2 14:51
     * @Version:V1.0
     * @Des:后台保存修改后的新闻
     */
    @Modifying
    @Query(value = "UPDATE News SET title = :title , img_url = :img_url , details = :details , updateTime = :updateTime WHERE id = :id")
    void saveUpdateNewsById(@Param("title") String title, @Param("img_url") String img_url, @Param("details") String details, @Param("updateTime") Date updateTime, @Param("id") long id);

    //查询所有新闻
    List<News> findByStatusOrderByCreateTimeDesc(Integer status);

    //按id查询新闻
    News findById(Long newId);
    List<News> findByStatus(Integer status);

    long countByStatusAfter(Integer status);
}
