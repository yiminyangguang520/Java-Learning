package com.example.lee.entity;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author min
 */
@Document(indexName = "gcrs-crash", type = "crash")
public class Crash implements Serializable {

  @Id
  private String id;

  private String product;

  private String product_name;

  private String version;

  private String gcr_version;

  private String crash_time;

  private String send_time;

  private Boolean inprocess;

  private Boolean outer;

  private String bits;

  private String ip;

  private String description;

  private String username;

  private String device_id;

  private String source;

  private String sub_product;

  private String module_name;

  private String module_version;

  private String if_send;

  public Crash() {
  }

  public Crash(String id, String product, String product_name, String version, String gcr_version, String crash_time, String send_time, Boolean inprocess,
      Boolean outer, String bits, String ip, String description, String username, String device_id, String source, String sub_product, String module_name,
      String module_version, String if_send) {
    this.id = id;
    this.product = product;
    this.product_name = product_name;
    this.version = version;
    this.gcr_version = gcr_version;
    this.crash_time = crash_time;
    this.send_time = send_time;
    this.inprocess = inprocess;
    this.outer = outer;
    this.bits = bits;
    this.ip = ip;
    this.description = description;
    this.username = username;
    this.device_id = device_id;
    this.source = source;
    this.sub_product = sub_product;
    this.module_name = module_name;
    this.module_version = module_version;
    this.if_send = if_send;
  }

  public Crash(Crash esCrash) {
    this.id = esCrash.id;
    this.product = esCrash.product;
    this.product_name = esCrash.product_name;
    this.version = esCrash.version;
    this.gcr_version = esCrash.gcr_version;
    this.crash_time = esCrash.crash_time;
    this.send_time = esCrash.send_time;
    this.inprocess = esCrash.inprocess;
    this.outer = esCrash.outer;
    this.bits = esCrash.bits;
    this.ip = esCrash.ip;
    this.description = esCrash.description;
    this.username = esCrash.username;
    this.device_id = esCrash.device_id;
    this.source = esCrash.source;
    this.sub_product = esCrash.sub_product;
    this.module_name = esCrash.module_name;
    this.module_version = esCrash.module_version;
    this.if_send = esCrash.if_send;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getProduct_name() {
    return product_name;
  }

  public void setProduct_name(String product_name) {
    this.product_name = product_name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getGcr_version() {
    return gcr_version;
  }

  public void setGcr_version(String gcr_version) {
    this.gcr_version = gcr_version;
  }

  public String getCrash_time() {
    return crash_time;
  }

  public void setCrash_time(String crash_time) {
    this.crash_time = crash_time;
  }

  public String getSend_time() {
    return send_time;
  }

  public void setSend_time(String send_time) {
    this.send_time = send_time;
  }

  public Boolean getInprocess() {
    return inprocess;
  }

  public void setInprocess(Boolean inprocess) {
    this.inprocess = inprocess;
  }

  public Boolean getOuter() {
    return outer;
  }

  public void setOuter(Boolean outer) {
    this.outer = outer;
  }

  public String getBits() {
    return bits;
  }

  public void setBits(String bits) {
    this.bits = bits;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDevice_id() {
    return device_id;
  }

  public void setDevice_id(String device_id) {
    this.device_id = device_id;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getSub_product() {
    return sub_product;
  }

  public void setSub_product(String sub_product) {
    this.sub_product = sub_product;
  }

  public String getModule_name() {
    return module_name;
  }

  public void setModule_name(String module_name) {
    this.module_name = module_name;
  }

  public String getModule_version() {
    return module_version;
  }

  public void setModule_version(String module_version) {
    this.module_version = module_version;
  }

  public String getIf_send() {
    return if_send;
  }

  public void setIf_send(String if_send) {
    this.if_send = if_send;
  }

  @Override
  public String toString() {
    return "Crash{" +
        "id='" + id + '\'' +
        ", product='" + product + '\'' +
        ", product_name='" + product_name + '\'' +
        ", version='" + version + '\'' +
        ", gcr_version='" + gcr_version + '\'' +
        ", crash_time='" + crash_time + '\'' +
        ", send_time='" + send_time + '\'' +
        ", inprocess=" + inprocess +
        ", outer=" + outer +
        ", bits='" + bits + '\'' +
        ", ip='" + ip + '\'' +
        ", description='" + description + '\'' +
        ", username='" + username + '\'' +
        ", device_id='" + device_id + '\'' +
        ", source='" + source + '\'' +
        ", sub_product='" + sub_product + '\'' +
        ", module_name='" + module_name + '\'' +
        ", module_version='" + module_version + '\'' +
        ", if_send='" + if_send + '\'' +
        '}';
  }
}
