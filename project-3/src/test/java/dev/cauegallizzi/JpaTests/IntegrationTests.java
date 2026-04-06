package dev.cauegallizzi.JpaTests;

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
