package com.pandy.demo

import com.pandy.demo.dao.BookCategoryJpaDao
import com.pandy.demo.dao.BookJpaDao
import com.pandy.demo.model.Book
import com.pandy.demo.model.BookCategory
import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    lateinit var bookCategoryJpaDao: BookCategoryJpaDao

    @Autowired
    lateinit var bookJpaDao: BookJpaDao

    @Test
    fun test() {
        println("test---------------------------------------------------------------")
    }

    @Test
    fun saveCategoryTest() {
        //两分类
        val bookCategory = BookCategory(name = "分类1")
        val bookCategory2 = BookCategory(name = "分类2")

        //两本书
        val books = mutableListOf<Book>().apply {
            add(Book(name = "书籍1", bookCategory = bookCategory))
            add(Book(name = "书籍2", bookCategory = bookCategory))
            add(Book(name = "书籍1", bookCategory = bookCategory2))
            add(Book(name = "书籍2", bookCategory = bookCategory2))
        }

        //分类1增加两本书
        bookCategory.books = books
        bookCategory2.books = books

        val list = mutableListOf<BookCategory>().apply {
            add(bookCategory)
            add(bookCategory2)
        }
        //任何一方进行保存 都能维护关联关系 但是只有在主的一方删除次的一方才会级联删除

        //这条语句也能执行成功
        //只需要对one的一方进行保存 但是属性中已经有多的一方了
        //val saveAll = bookCategoryJpaDao.saveAll(list)


        books.forEach {
            bookJpaDao.save(it)
        }

        assert(bookJpaDao.findAll().size == 4)
    }

    @Ignore
    @Test
    fun deleteCategoryTest() {
        val findAll = bookCategoryJpaDao.findAll()
        findAll.forEach{
            bookCategoryJpaDao.delete(it)
        }
        assert(bookJpaDao.findAll().size == 0)
    }

    @Test
    fun findByPaging() {

        val p = PageRequest.of(1,1)
        val findAll = bookCategoryJpaDao.findAll(p)
        println("查询的总页数 = ${findAll.totalPages}")
        println("查询的总条数 = ${findAll.totalElements}")
        assert(findAll.totalElements == 2L)
    }
}