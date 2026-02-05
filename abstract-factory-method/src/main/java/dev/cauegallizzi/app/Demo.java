package dev.cauegallizzi.app;

import dev.cauegallizzi.factories.CarFactory;
import dev.cauegallizzi.factories.PopularCarFactory;
import dev.cauegallizzi.factories.SportCarFactory;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Demo {
    private static Application configureApplication() {
        CarFactory carFactory = null;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Qual tipo de carro deseja criar?");
            System.out.println("1 - Popular");
            System.out.println("2 - Sport");
            System.out.print("Opcao: ");

            String carType = scanner.nextLine().toLowerCase();

            if (carType.contains("popular") || carType.equals("1")) {
                carFactory = new PopularCarFactory();
            } else if (carType.contains("sport") || carType.equals("2")) {
                carFactory = new SportCarFactory();
            } else {
                System.out.println("Tipo inválido! Tente novamente.\n");
            }

        } while (carFactory == null);

        scanner.close();

        Application app = new Application(carFactory);
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.engine();
        app.interior();
        app.wheel();
    }
}