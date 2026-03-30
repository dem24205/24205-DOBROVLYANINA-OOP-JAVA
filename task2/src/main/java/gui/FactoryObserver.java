package gui;

import factory.FactoryStat;

public interface FactoryObserver {
    void start();
    FactoryStat getFactoryStat();
    void setDealerDelay(int delay);
    void setBodySupplierDelay(int delay);
    void setMotorSupplierDelay(int delay);
    void setAccessorySupplierDelay(int delay);
    void stop();
}
