package dev.cauegallizzi;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        List<String> peoples = List.of("Maria - Feminino","Eduardo - Masculino","Fernanda - Feminino", "Jorge - Masculino");
        List<String> women = peoples.stream()
                .filter(people -> people.contains("Feminino"))
                .map(woman -> woman.split(" -")[0])
                .toList();

        List<String> men = peoples.stream()
                .filter(people -> people.contains("Masculino"))
                .map(man -> man.split(" -")[0])
                .toList();

        System.out.println("-----------Women--------------");
        System.out.println(women);
        System.out.println("-----------Men--------------");
        System.out.println(men);
    }
}
