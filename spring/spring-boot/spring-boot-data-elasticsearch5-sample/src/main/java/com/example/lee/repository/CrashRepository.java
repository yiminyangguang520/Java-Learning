package com.example.lee.repository;

import com.example.lee.entity.Crash;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author min
 */
public interface CrashRepository extends ElasticsearchRepository<Crash, String> {

}