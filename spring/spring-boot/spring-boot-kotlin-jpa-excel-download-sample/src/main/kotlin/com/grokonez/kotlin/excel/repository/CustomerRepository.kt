package com.grokonez.kotlin.excel.repository

import com.grokonez.kotlin.excel.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, Long> {
}