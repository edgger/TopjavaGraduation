package com.github.edgarzed.topjavagraduation.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({
        "classpath:spring/spring.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = {"classpath:db/initDB_hsql.sql","classpath:db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"))
abstract public class AbstractServiceTest {

    @Autowired
    protected CacheManager cacheManager;

}