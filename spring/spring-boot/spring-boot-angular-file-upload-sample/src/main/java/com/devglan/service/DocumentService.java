package com.devglan.service;

import com.devglan.model.Document;
import com.devglan.model.ResponseMetadata;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author min
 */
public interface DocumentService {

  /**
   * save
   * @param multipartFile
   * @return
   * @throws IOException
   */
  ResponseMetadata save(MultipartFile multipartFile) throws IOException;

  /**
   * getDocumentFile
   * @param id
   * @return
   */
  byte[] getDocumentFile(Long id);

  /**
   * findAll
   * @return
   */
  List<Document> findAll();
}
