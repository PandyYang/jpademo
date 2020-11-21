package com.pandy.demo.dao

import com.pandy.demo.model.BookCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface BookCategoryJpaDao: JpaRepository<BookCategory, Int>, PagingAndSortingRepository<BookCategory, Int> {
}