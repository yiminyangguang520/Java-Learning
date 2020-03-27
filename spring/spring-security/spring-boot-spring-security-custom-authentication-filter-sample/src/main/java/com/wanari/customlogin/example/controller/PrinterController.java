package com.wanari.customlogin.example.controller;

import com.wanari.customlogin.example.config.security.constant.Roles;
import com.wanari.customlogin.example.controller.dto.CreatePrinterRequest;
import com.wanari.customlogin.example.service.PrinterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 */
@RestController
@RequestMapping("/printers")
public class PrinterController extends BaseController {

  private final PrinterService printerService;

  public PrinterController(PrinterService printerService) {
    this.printerService = printerService;
  }

  @GetMapping
  @PreAuthorize(Roles.USER)
  public ResponseEntity<?> getAll() {
    return printerService.findAll().fold(
        this::errorToResponse,
        this::toResponse
    );
  }

  @PostMapping
  @PreAuthorize(Roles.USER)
  public ResponseEntity<?> create(@RequestBody CreatePrinterRequest request) {
    return printerService.create(request).fold(
        this::errorToResponse,
        this::toResponse
    );
  }

  @PostMapping(value = "/detonate/{id}")
  @PreAuthorize(Roles.DETONATOR)
  public ResponseEntity<?> detonate(@PathVariable("id") String id) {
    return printerService.detonate(id).fold(
        this::errorToResponse,
        this::toResponse
    );
  }
}
