package dev.cauegallizzi.factories;

import dev.cauegallizzi.products.engine.Engine;
import dev.cauegallizzi.products.engine.SportEngine;
import dev.cauegallizzi.products.interior.Interior;
import dev.cauegallizzi.products.interior.SportInterior;
import dev.cauegallizzi.products.wheel.SportWheel;
import dev.cauegallizzi.products.wheel.Wheel;

public class SportCarFactory implements CarFactory {
    @Override
    public Engine createEngine() {
        return new SportEngine();
    }

    @Override
    public Interior createInterior() {
        return new SportInterior();
    }

    @Override
    public Wheel createWheel() {
        return new SportWheel();
    }
}
