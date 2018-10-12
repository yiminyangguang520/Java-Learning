package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.SecurityFilterMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface SecurityFilterMetadataRepository extends JpaRepository<SecurityFilterMetadata, Integer> {

}
