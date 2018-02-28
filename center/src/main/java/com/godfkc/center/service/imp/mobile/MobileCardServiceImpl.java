package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.Card;
import com.godfkc.center.repository.CardRepository;
import com.godfkc.center.service.mobile.MobileCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author syx
 * @date 17:10 2018-2-5
 * @description
 */
@Service
@Transactional
public class MobileCardServiceImpl implements MobileCardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> selectUserCard(Long userId) {
        return cardRepository.selectUserCard(userId);
    }

    @Override
    public Card checkCard(String num, String password) {
        return cardRepository.checkCard(num,password);
    }

    //根据cardId查询companyId
    @Override
    public Long selectCompanyIdByCard(Long cardId) {
        Long companyId = cardRepository.selectCompanyIdByCard(cardId);
        return companyId;
    }

    @Override
    public int updateUserIdAndStatus(Long userId,Long cardId) {
        return cardRepository.updateUserIdAndStatus(userId,cardId);
    }

    @Override
    public Card selectCardByUserId(Long userId, int status) {
        return cardRepository.selectCardByUserId(userId,status);
    }

    //修改card使用次数
    @Override
    public int updateCardTimes(Long cardId, int times) {
        return cardRepository.updateCardTimes(times,cardId);
    }

    //根据cardId查询卡账号
    @Override
    public String selectCardNumById(Long cardId) {
        return cardRepository.selectCardNumById(cardId);
    }
}
