package com.godfkc.center.service;

import com.godfkc.center.entity.Content;
import org.springframework.data.domain.Page;

public interface ContentService {

    /**
     * @Author:lhy
     * @Data:2018/2/9 14:38
     * @Version:V1.0
     * @Des:查询资讯列表
     */
    Page<Content> selectList(String status, Integer page, Integer size, String dir, String data, String search);

    /**
     * @Author:lhy
     * @Data:2018/2/9 14:57
     * @Version:V1.0
     * @Des:删除资讯
     */
    void deleteContent(long id, String status);

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:20
     * @Version:V1.0
     * @Des:添加资讯
     */
    void insertContent(Content content);

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:36
     * @Version:V1.0
     * @Des:根据id查询单条资讯
     */
    Content selectContent(long id);

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:53
     * @Version:V1.0
     * @Des:保存修改后的资讯
     */
    void saveUpdateContent(Content content);
}
