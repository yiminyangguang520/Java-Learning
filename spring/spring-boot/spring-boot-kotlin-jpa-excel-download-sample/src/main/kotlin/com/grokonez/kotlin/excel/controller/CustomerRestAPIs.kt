package com.grokonez.kotlin.excel.controller

import com.grokonez.kotlin.excel.repository.CustomerRepository
import com.grokonez.kotlin.excel.util.ExcelGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/excel")
class CustomerRestAPIs {

    @Autowired
    lateinit var customerRepository: CustomerRepository;

    @GetMapping("/customers.xlsx")
    fun customerReport(): ResponseEntity<InputStreamResource> {
        val customers = customerRepository.findAll();

        val bis = ExcelGenerator.customerPDFReport(customers);

        val headers = HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(InputStreamResource(bis));
    }
}
