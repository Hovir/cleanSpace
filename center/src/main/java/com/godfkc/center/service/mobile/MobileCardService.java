package com.godfkc.center.service.mobile;

import com.godfkc.center.entity.Card;

import java.util.List;

/**
 * @author syx
 * @date 17:04 2018-2-5
 * @description
 */
public interface MobileCardService {

    List<Card> selectUserCard(Long userId);

    Card checkCard(String num, String password);

    //根据cardId查询companyId
    Long selectCompanyIdByCard(Long cardId);

    //修改userId和status
    int updateUserIdAndStatus(Long userId,Long cardId);

    //根据userId查找times,cardId
    Card selectCardByUserId(Long userId, int status);

    //修改card使用次数
    int updateCardTimes(Long cardId, int times);

    //根据cardId查询卡账号
    String selectCardNumById(Long cardId);
}
