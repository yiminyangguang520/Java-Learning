package com.devglan.rest;

import com.devglan.model.Document;
import com.devglan.service.DocumentService;
import com.devglan.model.ResponseMetadata;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author min
 */
@Controller
@RequestMapping(value = "/doc")
public class DocumentController {

  private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);

  @Autowired
  DocumentService documentService;

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody
  ResponseMetadata handleFileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
    return documentService.save(file);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public HttpEntity<byte[]> getDocument(@PathVariable Long id) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.IMAGE_JPEG);
    return new ResponseEntity<>(documentService.getDocumentFile(id), httpHeaders, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody
  List<Document> getDocument() {
    return documentService.findAll();
  }

}
