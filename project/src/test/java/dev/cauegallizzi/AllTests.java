package dev.cauegallizzi;

import dev.cauegallizzi.dao.ClientDaoTest;
import dev.cauegallizzi.dao.ProductDaoTest;
import dev.cauegallizzi.service.ClientServiceTest;
import dev.cauegallizzi.service.ProductServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClientDaoTest.class, ProductDaoTest.class, ClientServiceTest.class, ProductServiceTest.class})
public class AllTests {}
