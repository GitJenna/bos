package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.SubArchiveRepository;
import cn.itcast.bos.domain.base.SubArchive;
import cn.itcast.bos.service.SubArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubArchiveServiceImpl implements SubArchiveService {

    @Autowired
    private SubArchiveRepository subArchiveRepository;

    @Override
    public Page<SubArchive> findPageData(int page, int rows) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return subArchiveRepository.findAll(pageable);
    }
}
