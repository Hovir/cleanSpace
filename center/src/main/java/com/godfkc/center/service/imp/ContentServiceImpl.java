package com.godfkc.center.service.imp;

import com.godfkc.center.entity.Content;
import com.godfkc.center.repository.ContentRepository;
import com.godfkc.center.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Page<Content> selectList(String status, Integer page, Integer size, String dir, String data, String search) {
        Sort sort = null;
        //判断排序规则
        if (dir.equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, data);
        } else if (dir.equals("asc")) {
            sort = new Sort(Sort.Direction.ASC, data);
        }
        Pageable pageable = new PageRequest(page, size, sort);
        return contentRepository.findByStatusAndTitleLike(status, search, pageable);
    }

    @Override
    public void deleteContent(long id, String status) {
        Content content = this.contentRepository.findById(id);
        content.setStatus(status);
        contentRepository.save(content);
    }

    @Override
    public void insertContent(Content content) {
        content.setCreateTime(new Date());
        content.setUpdateTime(new Date());
        this.contentRepository.save(content);
    }

    @Override
    public Content selectContent(long id) {
        return this.contentRepository.findById(id);
    }

    @Override
    public void saveUpdateContent(Content content) {
        this.contentRepository.updateContent(content.getTitle(), content.getImgUrl(), content.getContent(), new Date(), content.getId());
    }
}
