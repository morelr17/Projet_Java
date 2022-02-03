package game.competition.observer;


public interface Observer<E> {

  public void update(Observable<E> observable, E eventType, Object data);

  public void observe(Observable<E> observable);

}


