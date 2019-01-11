package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.FixedArea;
import org.springframework.data.domain.Page;

public interface FixedAreaService {
    Page<FixedArea> findPageData(int page, int rows, FixedArea fixedArea);
}
