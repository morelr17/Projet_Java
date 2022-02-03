package game.competition;

import game.competitor.*;
import game.match.strategy.*;
import game.competition.observer.*;
import game.competition.strategy.generatesGroupStrategy.*;
import game.competition.strategy.qualifiedStrategy.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Master extends Competition{

  private MasterGroupStrategy groupStrategy;
  private QualifiedByGroupStrategy qualifiedStrategy;

  public List<Competition> groupsStages = new ArrayList<Competition>();
  public Competition knockoutStage;

  /**
   *Create a Master with list of competitors and default group strategy, match strategy and qualified strategy
   *@param competitors a list of competitor
   */
  public Master (List<Competitor> competitors){
    this(competitors, new RandomMatchStrategy(),new MinGroupStrategy(), new TakeNFirstStrategy(2));
  }

  /**
   *Create a Master with list of competitor, strategy for the match, strategy for the groups, and strategy for qualified groups 
   *@param competitors a list of competitor
   *@param strategy the strategy of the match
   *@param groupStrategy a group strategy
   *@param qualifiedStrategy a qualified strategy
   */
  public Master (List<Competitor> competitors,MatchStrategy strategy,MasterGroupStrategy groupStrategy,QualifiedByGroupStrategy qualifiedStrategy) {
    super(competitors,strategy);

    int nbOfCompetitors = this.competitors.size();
    if(nbOfCompetitors <= 3){
      throw new RuntimeException("Impossible to play a Master with less than 4 competitor");
    }

    this.groupStrategy = groupStrategy;
    this.groupStrategy.setMaster(this);

    this.qualifiedStrategy = qualifiedStrategy;
    this.qualifiedStrategy.setMaster(this);

  }

  /**
  *Play the master
  *create the group, play the group stage, knockout stage and display the ranking
  *@return the winner of the master 
  */
  public Competitor play(){
    //Creation of groups 
    groupsStages = this.groupStrategy.generatesGroups();
    for(Competition comp : groupsStages){
      for(Observer<CompetitionEvent> obs : this.getObservers()){
        obs.observe(comp);
      }
    }

    // Groups phase 
    System.out.println("______PHASE DE GROUPE______");
    groupsStages.forEach(Competition::play);

    System.out.println("______CLASSEMENT DE LA PHASE DE GROUPES______\n");
    groupsStages.forEach(Competition::rankingDisplay);

    System.out.println("______FIN DE LA PHASE DE GROUPE______\n");

    System.out.println("______PHASE FINALE______");

    //For each groups we take all qualified 
    final List<Competitor> knockoutStageCompetitor = this.qualifiedStrategy.get();

    // Final Phase
    knockoutStage = new Tournament(knockoutStageCompetitor);

    for(Observer<CompetitionEvent> obs: this.getObservers()) {
      obs.observe(knockoutStage);
    }
    Competitor winner = knockoutStage.play();
    System.out.println("The winner of the Master is " + winner + "\n");
    knockoutStage.rankingDisplay();
    return winner;
  }

  /**
  *return the groups stage 
  *@return the groups stage 
  */
  public List<Competition> getGroupStages(){
    return Collections.unmodifiableList(groupsStages);
  }

  /**
   *return the groups stage 
   *@return the groups stage 
   */
  public Competition getKnockoutStage(){
    return knockoutStage;
  }

  /**
   * return the group strategy
   * @return the group strategy
   */
  public MasterGroupStrategy getGroupStrategy() {
    return this.groupStrategy;
  }

  /**
   * return the qualified strategy
   * @return the qualified strategy
   */
  public QualifiedByGroupStrategy getQualifiedStrategy() {
    return this.qualifiedStrategy;
  }

  @Override
  public int getPoints(Competitor competitor){
    if(knockoutStage != null){
      return knockoutStage.getPoints(competitor);
    }

    throw new RuntimeException("The final stage didn't start");
  }

  @Override
  public Map<Competitor,Integer> ranking(){
    Map<Competitor,Integer> rankingAll = new HashMap<>(knockoutStage.ranking());

    for(Competition group : groupsStages){
      for(Competitor competitor : group.competitors){
          rankingAll.putIfAbsent(competitor, -1);
        }
    }
    
    return rankingAll;
  }

  /**
   * return true if the competition is finished
   * @return true if the competition is finished
   */
  public boolean isFinished(){
    return isGroupsStagesFinished() && knockoutStage.isFinished();
  }

  /**
   * return true if the groupsStages are finished
   * @return true if the groupsStages are finished
   */
  public boolean isGroupsStagesFinished(){
    return groupsStages.stream().allMatch(Competition::isFinished);
  }
}

