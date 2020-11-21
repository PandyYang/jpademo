package com.pandy.demo.dao

import com.pandy.demo.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookJpaDao: JpaRepository<Book, Int> {
}