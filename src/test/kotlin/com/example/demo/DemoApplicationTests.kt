package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Embedded
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

data class Money(
    val amount: Int,
    val currency: String,
) {
    @delegate:Transient /* readonly property shouldn't be part of a persistence model */
    val foo by lazy { 123 }
}

@Repository
interface MyRepository : CrudRepository<MyRepository.MyEntity, Long> {
    data class MyEntity(
        @Id
        val id: Long?,
        val name: String,
        @Embedded(prefix = "my_", onEmpty = Embedded.OnEmpty.USE_NULL)
        val money: Money,
    )
}

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    lateinit var myRepository: MyRepository

    @Test
    fun contextLoads() {
        myRepository.deleteAll()
    }

}
