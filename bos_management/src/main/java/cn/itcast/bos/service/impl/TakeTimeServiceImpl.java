package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.TakeTimeRepository;
import cn.itcast.bos.domain.base.TakeTime;
import cn.itcast.bos.service.TakeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService {
    @Autowired
    private TakeTimeRepository takeTimeRepository;

    @Override
    public Page<TakeTime> findPageData(int page, int rows, TakeTime takeTime) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return takeTimeRepository.findAll(pageable);
    }
}
