package factory;

public record FactoryStat(int queueSize,
        int bodyOnStorage,
        long bodyProduced,
        int motorOnStorage,
        long motorProduced,
        int accessoryOnStorage,
        long accessoryProduced,
        int carOnStorage,
        long carProduced) {}
