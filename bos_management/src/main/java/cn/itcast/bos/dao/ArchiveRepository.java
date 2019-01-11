package cn.itcast.bos.dao;

import cn.itcast.bos.domain.base.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<Archive,Integer> {
}
