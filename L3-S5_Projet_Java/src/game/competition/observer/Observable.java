package game.competition.observer;

import java.util.*;
import java.util.concurrent.*;

public abstract class Observable<E> {
  
  private final List<Observer<E>> observers = new CopyOnWriteArrayList<Observer<E>>(); 

  /**
   * add an observer to the list of observers
   * @param o an observer
   */
  public void addObserver(Observer<E> o) {
    observers.add(o);
  }

  /**
   * add all observers 
   * @param obs a list of observers
   */
  public void addObservers(List<Observer<E>> obs){
    for (Observer<E> o : obs){
      observers.add(o);
    }
  }

  /**
   * delete an observer to the list of observers
   * @param o an observer
   */
  public void deleteObserver(Observer<E> o) {
    observers.remove(o);
  }

  /**
   * return the list of observers
   * @return the list of observers
   */
  public List<Observer<E>> getObservers() {
      return observers;
  }

  /**
   * notify the obervers of the event
   * @param eventType a type of event
   */
  public void notifyObservers(E eventType){
    notifyObservers(eventType, null);
  }

  /**
   * notify the obervers of the event and send an object with useful data
   * @param eventType a type of event
   * @param data data useful for the observer
   */
  public void notifyObservers(E eventType, Object data){
    for (Observer<E> o : observers){
      o.update(this, eventType, data);
    }
  }

}


