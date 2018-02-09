package com.godfkc.center.service.imp.mobile;

import com.godfkc.center.entity.Content;
import com.godfkc.center.repository.ContentRepository;
import com.godfkc.center.service.mobile.MobileContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MobileContentServiceImpl implements MobileContentService {
    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Page<Content> selectByStatus(String status, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(page, size, sort);
        return contentRepository.findByStatus(status, pageable);
    }

    @Override
    public Content selectContentById(Long id) {
        return contentRepository.findContentById(id);
    }
}
