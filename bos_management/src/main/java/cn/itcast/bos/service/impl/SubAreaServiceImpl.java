package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.SubAreaRepository;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;
import cn.itcast.bos.service.SubAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {

    @Autowired
    private SubAreaRepository SubAreaRepository;

    @Override
    public Page<SubArea> findPageData(int page, int rows, FixedArea subArea) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return SubAreaRepository.findAll(pageable);
    }
}
