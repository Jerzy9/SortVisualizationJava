package components.observer_pattern;

public interface Observable {
    void add(Observer observer);
    void remove(Observer observer);
    void inform();
}
