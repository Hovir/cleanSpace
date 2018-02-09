package com.godfkc.center.repository;

import com.godfkc.center.entity.Content;
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
 * @date 2018/1/30
 * @description
 */
public interface ContentRepository extends JpaRepository<Content, Long> {

    Page<Content> findByStatusAndTitleLike(String status, String title, Pageable pageable);

    Content findById(long id);

    /**
     * @Author:lhy
     * @Data:2018/2/9 16:02
     * @Version:V1.0
     * @Des:修改资讯
     */
    @Modifying
    @Query("update Content set title=:title , imgUrl = :imgUrl , content = :content , updateTime = :updateTime where id = :id")
    void updateContent(@Param("title") String title, @Param("imgUrl") String imgUrl, @Param("content") String content, @Param("updateTime") Date date, @Param("id") long id);
    /**
     * 发布状态集团资讯查询，限制条数，无分页
     * @param: limit 5 status 1
     * @return:
     * @author: Qi Zining
     * @date: 2018/2/9
     */
    Page<Content> findByStatus(String status, Pageable pageable);

    /**
     * 按id查询集团资讯
     * @param: id
     * @return: 一条集团资讯 Content
     * @author: Qi Zining
     * @date: 2018/2/9
     */
    Content findContentById(Long id);
}
