package org.sopt.kotlinsemina.domain.customer.controller

import org.sopt.kotlinsemina.domain.customer.dto.CustomerRequest
import org.sopt.kotlinsemina.domain.customer.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping
    fun createCustomer(@RequestBody request: CustomerRequest): ResponseEntity<Void> {
        customerService.create(request)
        return ResponseEntity.ok().build()
    }
}