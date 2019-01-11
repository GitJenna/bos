package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.TakeTime;
import org.springframework.data.domain.Page;

public interface TakeTimeService {
    Page<TakeTime> findPageData(int page, int rows, TakeTime takeTime);
}
