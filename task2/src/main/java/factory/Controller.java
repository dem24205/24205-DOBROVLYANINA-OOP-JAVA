package factory;

import factory.staff.AssemblyPoint;

public class Controller implements StorageObserver {
    private final AssemblyPoint assemblyPoint;

    public Controller(AssemblyPoint assemblyPoint) {
        this.assemblyPoint = assemblyPoint;
    }

    @Override
    public void onCarRemoved() {
        assemblyPoint.assembleCar();
    }
}
