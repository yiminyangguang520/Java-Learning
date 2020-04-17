package com.thoughtmechanix.licenses.repository;

import com.thoughtmechanix.licenses.model.License;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface LicenseRepository extends CrudRepository<License, String> {

  List<License> findByOrganizationId(String organizationId);

  License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
