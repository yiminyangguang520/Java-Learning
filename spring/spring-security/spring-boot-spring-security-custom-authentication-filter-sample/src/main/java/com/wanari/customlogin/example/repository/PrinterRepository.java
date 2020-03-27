package com.wanari.customlogin.example.repository;

import com.wanari.customlogin.example.domain.Printer;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author bruce
 */
@Repository
public interface PrinterRepository extends CrudRepository<Printer, String> {

  @Override
  List<Printer> findAll();
}
