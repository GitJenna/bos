package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.FixedArea;
import cn.itcast.bos.domain.base.SubArea;
import org.springframework.data.domain.Page;

public interface SubAreaService {
    Page<SubArea> findPageData(int page, int rows, FixedArea subArea);
}
