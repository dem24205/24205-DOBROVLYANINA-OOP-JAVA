package factory;

public interface Observable {
    void register(Observer o);
    void notifyObservers();
}
