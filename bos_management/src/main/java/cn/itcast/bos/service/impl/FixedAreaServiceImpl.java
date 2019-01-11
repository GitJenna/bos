package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.FixedAreaRepository;
import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.service.FixedAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {

    @Autowired
    private FixedAreaRepository fixedAreaRepository;

    @Override
    public Page<FixedArea> findPageData(int page, int rows, FixedArea fixedArea) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return fixedAreaRepository.findAll(pageable);
    }
}
