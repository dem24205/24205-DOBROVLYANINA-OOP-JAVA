package factory;

public record FactoryStat(int queueSize,
        int bodiesOnStorage,
        long bodiesProduced,
        int motorsOnStorage,
        long motorsProduced,
        int accessoriesOnStorage,
        long accessoriesProduced,
        int carsOnStorage,
        long carsProduced) {}
