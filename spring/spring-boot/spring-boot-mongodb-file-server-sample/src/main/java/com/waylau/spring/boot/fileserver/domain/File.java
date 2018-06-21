package com.waylau.spring.boot.fileserver.domain;

import java.util.Date;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * File 文档类.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年3月28日
 */
@Document
public class File {

  /**
   * 主键
   */
  @Id
  private String id;

  /**
   * 文件名称
   */
  private String name;

  /**
   * 文件类型
   */
  private String contentType;

  /**
   * 文件大小
   */
  private long size;

  /**
   * 上传日期
   */
  private Date uploadDate;

  /**
   * md5值
   */
  private String md5;

  /**
   * 文件内容
   */
  private Binary content;

  /**
   * 文件路径
   */
  private String path;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public Binary getContent() {
    return content;
  }

  public void setContent(Binary content) {
    this.content = content;
  }

  protected File() {
  }

  public File(String name, String contentType, long size, Binary content) {
    this.name = name;
    this.contentType = contentType;
    this.size = size;
    this.uploadDate = new Date();
    this.content = content;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    File fileInfo = (File) object;
    return java.util.Objects.equals(size, fileInfo.size)
        && java.util.Objects.equals(name, fileInfo.name)
        && java.util.Objects.equals(contentType, fileInfo.contentType)
        && java.util.Objects.equals(uploadDate, fileInfo.uploadDate)
        && java.util.Objects.equals(md5, fileInfo.md5)
        && java.util.Objects.equals(id, fileInfo.id);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(name, contentType, size, uploadDate, md5, id);
  }

  @Override
  public String toString() {
    return "File{"
        + "name='" + name + '\''
        + ", contentType='" + contentType + '\''
        + ", size=" + size
        + ", uploadDate=" + uploadDate
        + ", md5='" + md5 + '\''
        + ", id='" + id + '\''
        + '}';
  }
}
