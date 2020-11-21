package com.pandy.demo.model

import javax.persistence.*

@Entity
@Table(name = "book")
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "book_category_id") //指定关联的字段
        var bookCategory: BookCategory? = null,

        var name: String? = null
)