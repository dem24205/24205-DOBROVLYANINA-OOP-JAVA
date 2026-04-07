package factory;

import factory.staff.AssemblyPoint;

public class Controller implements Observer {
    private final AssemblyPoint assemblyPoint;
    private final ObservableStorage carStorage;
    private final int capacity;

    public Controller(AssemblyPoint assemblyPoint, ObservableStorage carStorage) {
        this.assemblyPoint = assemblyPoint;
        this.carStorage = carStorage;
        this.capacity = carStorage.getCapacity();
    }

    private int calculateOrder(int carsLeft, int pendingOrders) {
        int totalInProcess = carsLeft + pendingOrders;
        double fillPercent = (double) totalInProcess / capacity * 100;
        return (int) Math.max(1, Math.min(assemblyPoint.getWorkersNum(), (100 - fillPercent) / 10));
    }

    private void onCarDemand() {
        int carsLeft = carStorage.getItemsNum();
        int pendingOrders = assemblyPoint.getPendingOrders();  // ← переименовать метод
        int toProduce = calculateOrder(carsLeft, pendingOrders);
        for (int i = 0; i < toProduce; i++) {
            assemblyPoint.assembleCar();
        }
    }

    @Override
    public void update(Event e) {
        if (e == Event.CAR_DEMAND) {
            onCarDemand();
        }
    }
}