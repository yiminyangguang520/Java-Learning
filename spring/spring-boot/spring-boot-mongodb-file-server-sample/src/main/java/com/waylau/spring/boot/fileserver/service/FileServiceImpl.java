package com.waylau.spring.boot.fileserver.service;

import com.waylau.spring.boot.fileserver.domain.File;
import com.waylau.spring.boot.fileserver.repository.FileRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * File 服务.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年7月30日
 */
@Service
public class FileServiceImpl implements FileService {

  @Autowired
  public FileRepository fileRepository;

  @Override
  public File saveFile(File file) {
    return fileRepository.save(file);
  }

  @Override
  public void removeFile(String id) {
    fileRepository.delete(id);
  }

  @Override
  public File getFileById(String id) {
    return fileRepository.findOne(id);
  }

  @Override
  public List<File> listFilesByPage(int pageIndex, int pageSize) {
    Sort sort = new Sort(Direction.DESC, "uploadDate");
    Pageable pageable = new PageRequest(pageIndex, pageSize, sort);

    Page<File> page = fileRepository.findAll(pageable);
    List<File> list = page.getContent();
    return list;
  }
}
