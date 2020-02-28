# Reference Implementation of Redis Caching in Spring Boot.

In this reference implementation, AOP (Aspect Oriented Programming) is used for caching the objects.  

* Make sure `@EnableCaching` added BootApplication Configuration.
* Make sure entity implements `java.io.Serializable` as Redis Cache expects Serializable Object for caching.