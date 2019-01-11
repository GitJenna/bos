package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.ArchiveRepository;
import cn.itcast.bos.domain.base.Archive;
import cn.itcast.bos.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Override
    public Page<Archive> findPageData(int page, int rows) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return archiveRepository.findAll(pageable);
    }
}
