package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.SubArchive;
import org.springframework.data.domain.Page;

public interface SubArchiveService {
    Page<SubArchive> findPageData(int page, int rows);
}
