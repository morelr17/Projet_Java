package game.competition.observer;


import game.match.*;

public class Journalist implements Observer<CompetitionEvent> {

  private String name;

  /**
   * Create a journalist with a name
   * @param name the name of the journalist
   */
  public Journalist(String name){
    this.name = name;
  }

  /**
   * return the name of the journalist
   * @return the name of the journalist
   */
  public String getName(){
    return this.name;
  }

  /**
   * put the observer in observable state
   * @param observable the observable to observe
   */
  public void observe(Observable<CompetitionEvent> observable) {
    observable.addObserver(this);
  }

  /**
   * update according to the eventType send by the observable with data
   * @param eventType the event of the notification
   * @param observable the observable who send the notification
   * @param data a data useful for the observer
   */
  public void update(Observable<CompetitionEvent> observable, CompetitionEvent eventType, Object data) {
    switch(eventType) {
      case GAME_FINISHED:
      if(data instanceof Match){
        System.out.println(data.toString());
      } else {
        throw new RuntimeException();
      }
      break;
      default:
      break;
    }    
  }
}