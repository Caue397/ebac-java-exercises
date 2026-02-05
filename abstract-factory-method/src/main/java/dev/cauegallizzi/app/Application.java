package dev.cauegallizzi.app;

import dev.cauegallizzi.factories.CarFactory;
import dev.cauegallizzi.products.engine.Engine;
import dev.cauegallizzi.products.interior.Interior;
import dev.cauegallizzi.products.wheel.Wheel;

public class Application {
    private Engine engine;
    private Interior interior;
    private Wheel wheel;

    public Application(CarFactory carFactory) {
        engine = carFactory.createEngine();
        interior = carFactory.createInterior();
        wheel = carFactory.createWheel();
    }

    public void engine() {
        engine.engineSize();
        engine.horsepower();
        engine.hasTurbo();
    }

    public void interior() {
        interior.details();
    }

    public void wheel() {
        wheel.rim();
    }
}
