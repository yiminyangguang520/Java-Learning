package com.grokonez.kotlin.excel

import com.grokonez.kotlin.excel.model.Customer
import com.grokonez.kotlin.excel.repository.CustomerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class KotlinSpringJpaExcelDownloadApplication {
    @Bean
    fun initial(repository: CustomerRepository) = CommandLineRunner {
        repository.saveAll(listOf(Customer("Jack Smith", "Massachusetts", 23),
                Customer("Adam Johnson", "New York", 27),
                Customer("Katherin Carter", "Washington DC", 26),
                Customer("Jack London", "Nevada", 33),
                Customer("Jason Bourne", "California", 36)));
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(KotlinSpringJpaExcelDownloadApplication::class.java, *args)
}