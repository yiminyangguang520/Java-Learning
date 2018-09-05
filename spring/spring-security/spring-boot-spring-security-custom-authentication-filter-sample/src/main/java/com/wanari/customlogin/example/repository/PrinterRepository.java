package com.wanari.customlogin.example.repository;

import com.wanari.customlogin.example.domain.Printer;
import java.util.List;
import org.springframework.boot.actuate.metrics.util.SimpleInMemoryRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public class PrinterRepository extends SimpleInMemoryRepository<Printer> {

  @Override
  public List<Printer> findAll() {
    return (List<Printer>) super.findAll();
  }
}
