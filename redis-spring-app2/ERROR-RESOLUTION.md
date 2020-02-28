# Errors and Resolutions

## Error
```
The type redis.clients.jedis.JedisPoolConfig cannot be resolved. It is indirectly referenced from required .class files
```
**Resolution**
Looks like `spring-boot-starter-data-redis` comes with lettuce core bydefault, which is causing issue in getting `JedisPoolConfig` class in classpath. 
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
	<exclusions>
		<exclusion>
			<groupId>io.lettuce</groupId>
			<artifactId>lettuce-core</artifactId>
		</exclusion>
	</exclusions>
</dependency>
```	
