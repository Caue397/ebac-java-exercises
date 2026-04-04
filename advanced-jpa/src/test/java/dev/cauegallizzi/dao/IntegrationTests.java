package dev.cauegallizzi.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BrandDaoTest.class, CarDaoTest.class, AccessoryDaoTest.class })
public class IntegrationTests {
}
