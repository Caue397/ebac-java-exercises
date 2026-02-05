package dev.cauegallizzi.products.engine;

public class SportEngine implements Engine {
    @Override
    public void horsepower() {
        System.out.println("350hp");
    }

    @Override
    public void engineSize() {
        System.out.println("3.0l");
    }

    @Override
    public void hasTurbo() {
        System.out.println("With turbo");
    }
}
