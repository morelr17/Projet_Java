package game;

import game.competitor.*;
import game.competition.*;
import game.match.strategy.*;
import game.util.*;
import game.competition.observer.*;

import game.competition.strategy.generatesGroupStrategy.*;
import game.competition.strategy.qualifiedStrategy.*;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

  static final int NUMBER_OF_AVAILABLE_COMPETITION = 3;
  
  private static int readFromScanner(Scanner scanner, String display){
    System.out.println(display);
    int val = scanner.nextInt();
    return val;
  }

  public static void main(String[] args){

    final int nbCompetitor = args.length;

    if(nbCompetitor <= 1){
      System.out.println("Impossible to play with 0 or 1 competitor");
      return;
    } 

    List<Competitor> competitors = new ArrayList<Competitor>();

    for (int i = 0;i < nbCompetitor;i++){
      String name = args[i];
      Competitor competitor = new Competitor(name);
      competitors.add(competitor);
    }

    Scanner scanner = new Scanner(System.in);
    int choice = readFromScanner(scanner, "Press 1 to play a League, 2 to play a Tournament or 3 to play a Master");

    while(choice > NUMBER_OF_AVAILABLE_COMPETITION){
      choice = readFromScanner(scanner, "Press 1 to play a League, 2 to play a Tournament or 3 to play a Master");
    }

    Journalist journalist = new Journalist("Nawfel");
    Bookmaker bookmaker = new Bookmaker("Romain");

    if(choice == 1){
      // Create the league
      League league = new League(Collections.unmodifiableList(competitors),new RandomMatchStrategy());

      // Observe the competition
      journalist.observe(league);
      bookmaker.observe(league);

      // Play the league
      league.play();
      league.rankingDisplay();
    }
    else if(choice == 2){
      //Play a Tournament

      Tournament tournament = new Tournament(Collections.unmodifiableList(competitors),new RandomMatchStrategy());
      // Observe the competition
      journalist.observe(tournament);
      bookmaker.observe(tournament);

      // Play the tournament
      tournament.play();
      tournament.rankingDisplay();

    }
    else if(choice == 3){
      //Play a Master
      Master master = configureMaster(competitors);
      // Observe the master
      journalist.observe(master);
      bookmaker.observe(master);
      
      master.play();
    }
  }

  public static Master configureMaster(List<Competitor> competitors){
      Scanner scannerNbOfGroup = new Scanner(System.in);
      int choiceNbOfGroup = readFromScanner(scannerNbOfGroup, "Number of groups: ");

      Scanner scannerFirstQualified = new Scanner(System.in);
      int choiceFirstQualified = readFromScanner(scannerFirstQualified, "Number of first qualified by group: ");

      Scanner scannerFishOutQualified = new Scanner(System.in);
      int choiceFishOutQualified = readFromScanner(scannerFishOutQualified, "How many competitor you want to Fish Out ?: ");

      if(!NumberUtils.isPowerof2(choiceNbOfGroup * choiceFirstQualified + choiceFishOutQualified)){
        System.out.println("wrong configuration, please try again");
        return configureMaster(competitors);
      } 

      QualifiedByGroupStrategy strategy = new CompositeQualifiedStrategy(new TakeNFirstStrategy(choiceFirstQualified),new TakeBestByRankedStrategy(choiceFishOutQualified,choiceFirstQualified + 1));
      return new Master(Collections.unmodifiableList(competitors),new RandomMatchStrategy(),new NGroupStrategy(choiceNbOfGroup),strategy);
  }
}