package cn.itcast.bos.service;

import cn.itcast.bos.domain.base.Archive;
import org.springframework.data.domain.Page;

public interface ArchiveService {
    Page<Archive> findPageData(int page, int rows);
}
