package dev.cauegallizzi;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        List<String> people = People.generatePeopleList();
        List<String> women = People.filterWomen(people);
        System.out.println("-----------Women------------");
        System.out.println(women);
    }
}
