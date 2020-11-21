package com.pandy.demo.model

import javax.persistence.*

@Entity
@Table(name = "book_category")
data class BookCategory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        var name: String? = null,
        /**
         * mappedBy不是一对多的关系维护端 定义在被拥有方
         * 在一的一方声明
         * 在多的一方 必须出现这个字段 bookCategory 然后在数据库中对应一个字段book_category_id
         * 维护的时候 删除的话  会对多进行级联删除 但是一的一方不会删除
         */
        @OneToMany(mappedBy = "bookCategory",cascade = [CascadeType.ALL])
        var books: List<Book>? = null
)