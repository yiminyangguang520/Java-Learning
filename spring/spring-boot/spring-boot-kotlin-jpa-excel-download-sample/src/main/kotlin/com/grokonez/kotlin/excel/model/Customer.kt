package com.grokonez.kotlin.excel.model

import javax.persistence.*

@Entity
@Table(name = "customers")
data class Customer(
        @Column(name = "name")
        var name: String = "",

        @Column(name = "address")
        var address: String = "",

        @Column(name = "age")
        var age: Int = 0,

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1
) {}