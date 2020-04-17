package com.devglan.service.impl;

import com.devglan.dao.DocumentDao;
import com.devglan.model.Document;
import com.devglan.service.DocumentService;
import com.devglan.model.ResponseMetadata;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author min
 */
@Service
public class DocumentServiceImpl implements DocumentService {

  @Autowired
  private DocumentDao documentDao;

  @Override
  public ResponseMetadata save(MultipartFile file) throws IOException {

    Document doc = new Document();
    doc.setDocName(file.getOriginalFilename());
    doc.setFile(file.getBytes());
    documentDao.save(doc);
    ResponseMetadata metadata = new ResponseMetadata();
    metadata.setMessage("success");
    metadata.setStatus(200);
    return metadata;
  }

  @Override
  public byte[] getDocumentFile(Long id) {
    return documentDao.findById(id).orElse(null).getFile();
  }

  @Override
  public List<Document> findAll() {
    return (List<Document>) documentDao.findAll();
  }

}
