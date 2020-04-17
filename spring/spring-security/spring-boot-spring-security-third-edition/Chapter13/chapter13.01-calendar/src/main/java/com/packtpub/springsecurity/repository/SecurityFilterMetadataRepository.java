package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.SecurityFilterMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author min
 */
public interface SecurityFilterMetadataRepository extends JpaRepository<SecurityFilterMetadata, Integer> {

}
