package dev.cauegallizzi.factories;

import dev.cauegallizzi.products.engine.Engine;
import dev.cauegallizzi.products.interior.Interior;
import dev.cauegallizzi.products.wheel.Wheel;

public interface CarFactory {
    Engine createEngine();
    Interior createInterior();
    Wheel createWheel();
}
