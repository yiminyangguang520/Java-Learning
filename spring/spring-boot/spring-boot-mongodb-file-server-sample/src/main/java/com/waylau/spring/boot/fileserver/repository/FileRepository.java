package com.waylau.spring.boot.fileserver.repository;

import com.waylau.spring.boot.fileserver.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * File 存储库.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年3月28日
 */
public interface FileRepository extends MongoRepository<File, String> {

}
