package dev.cauegallizzi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CustomerDaoTest.class, ProductDaoTest.class})
public class IntegrationTests {}
