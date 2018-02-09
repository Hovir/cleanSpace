package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.Content;
import org.springframework.data.domain.Page;

public interface MobileContentService {
    /**
     * 限定条目的集团资讯
     * @param status:1
     * @param page:1
     * @param size:需要的条目数
     * @author Qi
     * @return Page<content>
     * @date 2018/2/9
     */
    Page<Content> selectByStatus(String status, Integer page, Integer size);

    /**
     * 按id 查询
     * @param id:id of content
     * @return content:单个content
     * @author Qi
     * @date 2018/2/9
     */
    Content selectContentById(Long id);
}
