# Useful links
 * About Spring Data Relational library used here and it's kotlin support: https://docs.spring.io/spring-data/relational/reference/object-mapping.html#mapping.kotlin
 * Nice article about Kotlin delegation strategies and how it works: https://medium.com/til-kotlin/how-kotlins-delegated-properties-and-lazy-initialization-work-552cbad8be60

# Problem

```
org.springframework.data.relational.core.conversion.DbActionExecutionException: Failed to execute DbAction.DeleteAll(propertyPath=money.foo$delegate)

	at org.springframework.data.jdbc.core.AggregateChangeExecutor.execute(AggregateChangeExecutor.java:118)
	at org.springframework.data.jdbc.core.AggregateChangeExecutor.lambda$executeDelete$1(AggregateChangeExecutor.java:78)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at org.springframework.data.relational.core.conversion.DeleteAggregateChange.forEachAction(DeleteAggregateChange.java:82)
	at org.springframework.data.jdbc.core.AggregateChangeExecutor.executeDelete(AggregateChangeExecutor.java:78)
	at org.springframework.data.jdbc.core.JdbcAggregateTemplate.deleteAll(JdbcAggregateTemplate.java:404)
	at org.springframework.data.jdbc.repository.support.SimpleJdbcRepository.deleteAll(SimpleJdbcRepository.java:129)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
	at java.base/java.lang.reflect.Method.invoke(Method.java:580)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:354)
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker$RepositoryFragmentMethodInvoker.lambda$new$0(RepositoryMethodInvoker.java:277)
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:170)
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:158)
	at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:516)
	at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:285)
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:628)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:168)
	at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:143)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:392)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:138)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.data.repository.core.support.MethodInvocationValidator.invoke(MethodInvocationValidator.java:95)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:223)
	at jdk.proxy2/jdk.proxy2.$Proxy74.deleteAll(Unknown Source)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)
	at java.base/java.lang.reflect.Method.invoke(Method.java:580)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:354)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:138)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:223)
	at jdk.proxy2/jdk.proxy2.$Proxy74.deleteAll(Unknown Source)
	at com.example.demo.DemoApplicationTests.contextLoads(DemoApplicationTests.kt:39)
	at java.base/java.lang.reflect.Method.invoke(Method.java:580)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
Caused by: org.springframework.jdbc.BadSqlGrammarException: StatementCallback; bad SQL grammar [DELETE FROM "LAZY" WHERE "LAZY"."MY_ENTITY" IS NOT NULL]
	at org.springframework.jdbc.support.SQLExceptionSubclassTranslator.doTranslate(SQLExceptionSubclassTranslator.java:103)
	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:107)
	at org.springframework.jdbc.core.JdbcTemplate.translateException(JdbcTemplate.java:1548)
	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:408)
	at org.springframework.jdbc.core.JdbcTemplate.update(JdbcTemplate.java:567)
	at org.springframework.data.jdbc.core.convert.DefaultDataAccessStrategy.deleteAll(DefaultDataAccessStrategy.java:232)
	at org.springframework.data.jdbc.core.JdbcAggregateChangeExecutionContext.executeDeleteAll(JdbcAggregateChangeExecutionContext.java:167)
	at org.springframework.data.jdbc.core.AggregateChangeExecutor.execute(AggregateChangeExecutor.java:99)
	... 44 more
Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "LAZY" not found; SQL statement:
DELETE FROM "LAZY" WHERE "LAZY"."MY_ENTITY" IS NOT NULL [42102-224]
	at org.h2.message.DbException.getJdbcSQLException(DbException.java:514)
	at org.h2.message.DbException.getJdbcSQLException(DbException.java:489)
	at org.h2.message.DbException.get(DbException.java:223)
	at org.h2.message.DbException.get(DbException.java:199)
	at org.h2.command.Parser.getTableOrViewNotFoundDbException(Parser.java:8064)
	at org.h2.command.Parser.getTableOrViewNotFoundDbException(Parser.java:8035)
	at org.h2.command.Parser.readTableOrView(Parser.java:8024)
	at org.h2.command.Parser.readTableOrView(Parser.java:7990)
	at org.h2.command.Parser.readSimpleTableFilter(Parser.java:1094)
	at org.h2.command.Parser.parseDelete(Parser.java:1108)
	at org.h2.command.Parser.parsePrepared(Parser.java:677)
	at org.h2.command.Parser.parse(Parser.java:592)
	at org.h2.command.Parser.parse(Parser.java:564)
	at org.h2.command.Parser.prepareCommand(Parser.java:483)
	at org.h2.engine.SessionLocal.prepareLocal(SessionLocal.java:639)
	at org.h2.engine.SessionLocal.prepareCommand(SessionLocal.java:559)
	at org.h2.jdbc.JdbcConnection.prepareCommand(JdbcConnection.java:1166)
	at org.h2.jdbc.JdbcStatement.executeUpdateInternal(JdbcStatement.java:190)
	at org.h2.jdbc.JdbcStatement.executeUpdate(JdbcStatement.java:147)
	at com.zaxxer.hikari.pool.ProxyStatement.executeUpdate(ProxyStatement.java:119)
	at com.zaxxer.hikari.pool.HikariProxyStatement.executeUpdate(HikariProxyStatement.java)
	at org.springframework.jdbc.core.JdbcTemplate$1UpdateStatementCallback.doInStatement(JdbcTemplate.java:555)
	at org.springframework.jdbc.core.JdbcTemplate$1UpdateStatementCallback.doInStatement(JdbcTemplate.java:552)
	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:393)
	... 48 more

```

# ChatGPT 4o stance 
I was just curious, but it seems right to me.

**Response 23.6.2024**:

Spring Data Relational doesn't support lazy initialization directly on entity properties because it interferes with how the framework initializes and manages entities. Lazy properties in Kotlin use a delegate that Spring Data Relational cannot handle properly.

To resolve this, you should remove the `by lazy` delegate for fields that are part of the entity. Instead, you can initialize them directly or use other strategies that don't interfere with the entity lifecycle management.

Here’s how you can adjust the class to avoid the lazy initialization issue:

1. **Remove the Lazy Initialization**:
   - Initialize the property directly or calculate it within a method if it depends on other properties.

Here's a modified version of the `Money` class:

```kotlin
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("money") // Maps the class to the "money" table in the database
data class Money(
    @Id
    val id: Long? = null, // ID property for the entity

    var amount: Int = 0,
    var currency: String = ""
) {
    // Remove the lazy property, initialize it directly if needed
    val foo: Int = 123
}
```

### Explanation of Changes:

1. **Spring Data Relational Annotations**:
   - Use `@Table` from Spring Data Relational instead of `@Entity` for JPA.
   - Use `@Id` from Spring Data Relational to denote the primary key.

2. **Initialization of `foo`**:
   - Instead of using lazy initialization, directly initialize `foo` with the value `123`.

### Additional Note:

If the value of `foo` is derived from other properties and needs to be calculated dynamically, you can use a method or a custom getter instead:

```kotlin
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("money")
data class Money(
    @Id
    val id: Long? = null,

    var amount: Int = 0,
    var currency: String = ""
) {
    // Custom getter for derived property
    val foo: Int
        get() = 123 // Replace this with the actual computation if needed
}
```

With these changes, your `Money` class should work correctly with Spring Data Relational without causing exceptions related to the lazy field.
