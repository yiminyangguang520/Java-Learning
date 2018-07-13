package com.javasampleapproach.couchbase.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.spring.cache.CacheBuilder;
import com.couchbase.client.spring.cache.CouchbaseCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@EnableCaching
@Configuration
public class CacheConfig {

  public static final String CACHE_NAME = "customers";

  @Value("${jsa.couchbase.server}")
  private String couchbaseServer;

  @Value("${jsa.couchbase.bucket}")
  private String couchbaseBucket;

  @Value("${jsa.couchbase.password}")
  private String couchbasePassword;

  @Bean(destroyMethod = "disconnect")
  public Cluster cluster() {
    // connect to the Couchbase server running on your local machine
    return CouchbaseCluster.create(couchbaseServer);
  }

  @Bean(destroyMethod = "close")
  public Bucket bucket() {
    // connect to the bucket named 'jsabucket' (which must exist on your Couchbase server)
    // every cache related element will use this bucket
    return cluster().openBucket(couchbaseBucket, couchbasePassword);
  }

  @Bean
  public CacheManager cacheManager() {
    CacheBuilder cacheBuilder = CacheBuilder.newInstance(bucket()).withExpiration(0);
    return new CouchbaseCacheManager(cacheBuilder, CACHE_NAME);
  }

}