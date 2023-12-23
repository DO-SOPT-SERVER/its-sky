package org.sopt.kotlinsemina.domain.customer.repository

import org.sopt.kotlinsemina.domain.customer.model.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository: JpaRepository<CustomerEntity, Long>