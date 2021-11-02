# Overeiw

Starter project:
- micronaut https://micronaut.io
- security: sessions security, sessions stored in redis (https://micronaut-projects.github.io/micronaut-redis/latest/guide/#sessions)
- JPA: h2, hikariCP
- SSR on pebble templates https://pebbletemplates.io/
- jdk 17
- maven
- twitter bootstrap

# Running

1. Bootstrap local environment (redis is needed)

```shell
cd env/local && docker-compose up -d
```

2. Run app from IDE, or using maven:

```shell
./mvnw mn:run
```

# Security

https://micronaut-projects.github.io/micronaut-security/latest/guide/#session

# References

## Micronaut 3.1.0 Documentation

- [User Guide](https://docs.micronaut.io/3.1.0/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.1.0/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.1.0/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

## Feature hibernate-jpa documentation

- [Micronaut Hibernate JPA documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#hibernate)
