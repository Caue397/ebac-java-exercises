package dev.cauegallizzi.factories;

import dev.cauegallizzi.products.engine.Engine;
import dev.cauegallizzi.products.engine.PopularEngine;
import dev.cauegallizzi.products.interior.Interior;
import dev.cauegallizzi.products.interior.PopularInterior;
import dev.cauegallizzi.products.wheel.PopularWheel;
import dev.cauegallizzi.products.wheel.Wheel;

public class PopularCarFactory implements CarFactory {
    @Override
    public Engine createEngine() {
        return new PopularEngine();
    }

    @Override
    public Interior createInterior() {
        return new PopularInterior();
    }

    @Override
    public Wheel createWheel() {
        return new PopularWheel();
    }
}
