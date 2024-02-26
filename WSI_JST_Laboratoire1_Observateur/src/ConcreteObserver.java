public class ConcreteObserver extends AbstractObserver {
    private long time;
    private ConcreteSubject chrono;

    public ConcreteObserver(ConcreteSubject subject)
    {
        chrono = subject;
        update();
    }

    @Override
    public void update() {
        time = chrono.getTime();
    }
}
