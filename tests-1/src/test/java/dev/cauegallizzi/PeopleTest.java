package dev.cauegallizzi;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PeopleTest {

    @Test
    public void shouldGeneratePeopleList() {
        List<String> people = People.generatePeopleList();
        List<String> expected = List.of("Maria - Feminino","Eduardo - Masculino","Fernanda - Feminino", "Jorge - Masculino");

        Assert.assertEquals(expected, people);
    }

    @Test
    public void shouldFilterWomen() {
        List<String> people = People.generatePeopleList();
        List<String> women = People.filterWomen(people);
        boolean condition = women.stream()
                .noneMatch(currentPeople -> currentPeople.contains("Masculino"))
                &&
                women.stream().anyMatch(currentPeople -> currentPeople.contains("Feminino"));

        Assert.assertTrue(condition);
    }
}
