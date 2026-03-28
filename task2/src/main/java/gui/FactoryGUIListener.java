package gui;

import factory.FactoryStat;

public interface FactoryGUIListener {
    void start();
    FactoryStat getFactoryStat();
    void setDealerSpeed(int delay);
    void setBodySupplierSpeed(int delay);
    void setMotorSupplierSpeed(int delay);
    void setAccessorySupplierSpeed(int delay);
    void GUIWindowExit();
}
