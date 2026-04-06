package dev.cauegallizzi.PostgresTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerDaoJpaTest.class,
        ProductDaoJpaTest.class,
        SaleDaoJpaTest.class,
        ProductQuantityDaoJpaTest.class
})
public class IntegrationTests {
}
