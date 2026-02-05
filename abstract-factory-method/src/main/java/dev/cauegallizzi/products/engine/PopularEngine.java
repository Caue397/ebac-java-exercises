package dev.cauegallizzi.products.engine;

public class PopularEngine implements Engine {
    @Override
    public void horsepower() {
        System.out.println("110hp");
    }

    @Override
    public void engineSize() {
        System.out.println("1.6l");
    }

    @Override
    public void hasTurbo() {
        System.out.println("No turbo");
    }
}
