package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StandardService {
    Page<Standard> findPageData(Pageable pageable);

    void save(Standard standard);
}
