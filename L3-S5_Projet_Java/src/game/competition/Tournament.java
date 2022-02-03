package game.competition;

import game.competitor.*;
import game.match.*;
import game.match.strategy.*;
import game.util.*;


import java.util.List;
import java.util.ArrayList;

public class Tournament extends Competition {

    /**
     *Create new tournament with list of Competitors and a strategy
     *@param competitors list of participants
     *@param strategy the strategy of the macth
     */
    public Tournament(List<Competitor> competitors,MatchStrategy strategy){
        super(competitors,strategy);
        if( !NumberUtils.isPowerof2(competitors.size()) ){
            throw new IllegalArgumentException("List of competitors size must be even");
        }
    }

    /**
     *Create new tournament with list of Competitors and the Random Match Strategy and verify if the number of Competitors is power of 2 
     *@param competitors list of participants
     */
    public Tournament(List<Competitor> competitors){
        super(competitors,new RandomMatchStrategy());
        if( !NumberUtils.isPowerof2(competitors.size()) ){
            throw new IllegalArgumentException("List of competitors size must be even");
        }
    }

    /**
     *Play one round of Tournament, take 2 competitor and put the winner in list of qualified, remove both of the list and when all match is finished return the list of qualified 
     *@param competitors a list of competitors
     *@return the list of the qualified players
     */
    public List<Competitor> playOneRound(List<Competitor> competitors){

        List<Competitor> qualified = new ArrayList<Competitor>();
        List<Competitor> copy = new ArrayList<Competitor>(competitors);

        while(!copy.isEmpty()){
            Competitor comp1 = copy.get(0);
            Competitor comp2 = copy.get(1);

            Match match = playMatch(comp1,comp2);
            
            qualified.add(match.getWinner());
            
            copy.remove(comp1);
            copy.remove(comp2);
        }
        return qualified;
    }

    /**
     *Play the Tournament, use Loop with the Play one Round fonction 
     *@return the winner of the tournament
     */
    public Competitor play(){
        if(isFinished()){
            throw new RuntimeException("Can't play 2 time a competition");
        }
        List<Competitor> qualified = new ArrayList<Competitor>(this.competitors); 

        while (qualified.size() != 1){
            qualified = playOneRound(qualified); 
        }

        competitionFinished = true;

        System.out.println("-----------FIN DU TOURNOI-------------");
        return qualified.get(0);
    }
}

