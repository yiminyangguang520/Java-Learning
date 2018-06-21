package com.example.lee.repository;

import com.example.lee.entity.Crash;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author litz-a
 */
public interface CrashRepository extends ElasticsearchRepository<Crash, String> {

}