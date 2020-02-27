# Reference Implementation of Redis Caching in Spring Boot.

In this reference implementation, method caching is implemented using annotation `@Cacheable` at Service layer. 

* Make sure `@EnableCaching` added BootApplication Configuration.
* Make sure entity implements `java.io.Serializable` as Redis Cache expects Serializable Object for caching.