package com.godfkc.managerweb.service;

import com.godfkc.common.pojo.dataTables.SentParameters;

import java.util.List;
import java.util.Map;

public interface ContentService {

    /**
     * @Author:lhy
     * @Data:2018/2/9 14:29
     * @Version:V1.0
     * @Des:资讯列表
     */
    String contentLis(SentParameters sentParameters);

    /**
     * @Author:lhy
     * @Data:2018/2/9 14:52
     * @Version:V1.0
     * @Des:删除资讯
     */
    Boolean delect(List<Long> list);

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:15
     * @Version:V1.0
     * @Des:添加资讯
     */
    void insertContent(Map<String, Object> map);

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:33
     * @Version:V1.0
     * @Des:资讯修改
     */
    String openUpdateContent(long id);

    /**
     * @Author:lhy
     * @Data:2018/2/9 15:50
     * @Version:V1.0
     * @Des:保存修改后的资讯
     */
    void saveUpdateContent(Map<String, Object> map);
}
