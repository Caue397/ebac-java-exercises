package dev.cauegallizzi;

import java.util.List;

public class People {
    static List<String> filterWomen(List<String> people) {
        return people.stream()
                .filter(currentPeople -> currentPeople.contains("Feminino"))
                .toList();
    }

    static List<String> generatePeopleList() {
        return List.of("Maria - Feminino","Eduardo - Masculino","Fernanda - Feminino", "Jorge - Masculino");
    }
}
