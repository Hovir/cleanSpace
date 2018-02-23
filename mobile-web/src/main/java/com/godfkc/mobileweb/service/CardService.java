package com.godfkc.mobileweb.service;

/**
 * @author syx
 * @date 16:52 2018-2-5
 * @description
 */
public interface CardService {
    //根据UserId查询检测卡
    boolean selectUserCard(Long id);

    //检测卡的账号密码
    String checkCard(String num, String password);

    //根据cardId查询companyId
    Long selectCompanyIdByCard(Long cardId);

    //修改userId和status
    boolean updateUserIdAndStatus(Long userId,Long cardId);

    //根据userId查询卡的剩余次数，卡号
    String selectCardByUserId(Long userId);

    //修改卡次数
    boolean updateCardTimes(Long cardId,int times);

}
