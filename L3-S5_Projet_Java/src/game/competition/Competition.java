package game.competition;

import game.competition.observer.*;
import game.competitor.*;
import game.match.*;
import game.match.strategy.*;
import game.util.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;

public abstract class Competition extends Observable<CompetitionEvent>{

  protected List<Competitor> competitors;
  private MatchStrategy strategy;
  private final Map<Competitor, Integer> results =  new HashMap<> ();
  protected boolean competitionFinished = false;

  /**
   *Create a new competition, with a specifiate strategy
   *if the list of competitors or the strategy is NULL an exception is throw
   *@param competitors a list of competitor
   *@param strategy the strategy of the match
   */
  public Competition (List<Competitor> competitors,MatchStrategy strategy) {
    
    if(competitors == null){
      throw new IllegalArgumentException("competitors must not be null");
    }
    
    if(strategy == null){
      throw new IllegalArgumentException("strategy must not be null");
    }

    this.competitors = competitors;
    this.strategy = strategy;

    for(final Competitor competitor : competitors){
      results.put(competitor, 0);
    }
  }

  /**
   *Create a new competition, in this constructor you have random match strategy 
   *@param competitors a list of competitor
   */
  public Competition (List<Competitor> competitors) {
    this(competitors,new RandomMatchStrategy());
  }
  
  /**
   *return the competitors of the competition
   *@return the competitors of the competition
   */
  public List<Competitor> getCompetitors() {
    return this.competitors;
  }

  /**
   *add points to the winner in Map results 
   *@param competitor a competitor
   *@param numberOfPoints the number of points to add to the competitor
   */
  public void addPoints(Competitor competitor, int numberOfPoints){
    int previousPoints = results.get(competitor);
    results.put(competitor, previousPoints + numberOfPoints);
  }

  /**
   *return the number of points of the player
   *@param competitor a competitor
   *@return the number of points of the player
   */
  public int getPoints(Competitor competitor){
    return results.get(competitor);
  }

  /**
   *Play one match, take 2 competitors and use matchConsequence but matchConcequences is different with different strategy 
   *@param c1 a competitor
   *@param c2 a competitor
   *@return the match played
   */
  protected Match playMatch(Competitor c1, Competitor c2){
    Match match = new Match(this,c1,c2,this.strategy);
    match.matchConsequence();
    notifyObservers(CompetitionEvent.GAME_FINISHED,match);
    
    return match;
  }

  /**
   *take map results and sort it 
   *@return a map of the ranking competitor
   */
  public Map<Competitor,Integer> ranking(){
    return Collections.unmodifiableMap(MapUtil.sortByDescendingValue(results));
  }

  /**
   *Display the map sorted 
   */
  public void rankingDisplay(){
    Map score = ranking();
    Iterator iterator = score.entrySet().iterator();
    System.out.println("---------- CLASSEMENT ----------");
    while (iterator.hasNext()){
      Map.Entry mapentry = (Map.Entry) iterator.next();
      System.out.println("Competitor : " + mapentry.getKey()+" || score : " + mapentry.getValue());
    }
    System.out.println("\n");
  }

  /**
    *return true if the league is finished
    *@return true if the league is finished
    */
    public boolean isFinished(){
      return competitionFinished;
  }
  
  public abstract Competitor play();

}

