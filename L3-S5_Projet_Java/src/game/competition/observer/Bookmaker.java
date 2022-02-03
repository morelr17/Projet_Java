package game.competition.observer;

import game.competition.*;
import game.competitor.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import game.match.Match;

public class Bookmaker implements Observer<CompetitionEvent> {

  private String name;
  private Map<Competition,Map<Competitor,Integer>> mapOdds;

  /**
   * Create a bookmaker with a Map of competition that he follow
   * @param name the name of the bookmaker
   */
  public Bookmaker(String name){
    this.name = name;
    this.mapOdds = new HashMap<Competition,Map<Competitor,Integer>>();
  }

  /**
   * return the name of the bookmaker
   * @return the name of the bookmaker
   */
  public String getName(){
    return this.name;
  }  

  private Map<Competitor, Integer> initMap(Competition competition){
    final Map<Competitor, Integer> competitorOdds = new HashMap<Competitor,Integer>(); 
    for (Competitor comp : competition.getCompetitors()){
      competitorOdds.put(comp, 1);
    }
    return competitorOdds; 
  }

  /**
   * Apply the odds to the participants of the match, according to the results of the match
   * @param competitionOdds a map
   * @param match a match
   */
  private void applyOdds(Map<Competitor, Integer> competitionOdds, Match match){
    Competitor comp1 = match.getWinner();
    Competitor comp2 = match.getLooser();
    
    if (competitionOdds.get(comp1) != 1){
      competitionOdds.put(comp1,competitionOdds.get(comp1)-1);
    }
    competitionOdds.put(comp2,competitionOdds.get(comp2)+1);
  }

  /**
   * Display the odds of all the competitors of the competition
   */
  private void displayOdds(){
    Iterator iterator = this.mapOdds.entrySet().iterator();
    System.out.println("---------- ODDS ----------");
    while (iterator.hasNext()){
      Map.Entry mapentry = (Map.Entry) iterator.next();
      System.out.println(mapentry.getValue());
    }
    System.out.println("\n");
  }

  /**
   * update according to the eventType send by the observable with data
   * @param eventType the event of the notification
   * @param observable the observable who send the notification
   * @param data a data useful for the observer
   */
  public void update(Observable<CompetitionEvent> observable, CompetitionEvent eventType, Object data) {
    final Competition competition = (Competition)observable;
    switch(eventType) {
      case GAME_FINISHED:
      if(data instanceof Match){
        applyOdds(this.mapOdds.get(competition), (Match)data);
        displayOdds();
      } else {
        throw new RuntimeException();
      }
      break;
      default:
      break;
    }  
  }

  /**
   * put the observer in observable state
   * @param observable the observable to observe
   */
  public void observe(Observable<CompetitionEvent> observable) {
    observable.addObserver(this);

    Competition competition = (Competition)observable;

    Map<Competitor, Integer> map = initMap(competition);
    this.mapOdds.put(competition, map);
    
  }
  
}
