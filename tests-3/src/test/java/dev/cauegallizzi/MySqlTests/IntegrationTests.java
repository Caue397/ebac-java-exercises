package dev.cauegallizzi.MySqlTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
        CustomerDaoJpaTest.class,
        ProductDaoJpaTest.class,
        SaleDaoJpaTest.class,
        ProductQuantityDaoJpaTest.class
})
@RunWith(Suite.class)
public class IntegrationTests {
}
