package org.sopt.kotlinsemina.domain.customer.service

import org.sopt.kotlinsemina.domain.customer.dto.CustomerRequest
import org.sopt.kotlinsemina.domain.customer.model.CustomerEntity
import org.sopt.kotlinsemina.domain.customer.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CustomerService(
    private val customerRepository: CustomerRepository
) {

    @Transactional
    fun create(request: CustomerRequest) {
        CustomerEntity(
            name = request.name,
            age = request.age,
            nickname = request.nickname
        ).let(customerRepository::save)
    }
}