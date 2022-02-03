package game.competition;

import game.competitor.*;
import game.match.strategy.*;

import java.util.List;

public class League extends Competition {


    /**
     *Create new league with list of Competitors and a strategy
     *@param players list of participants
     *@param strategy the strategy of the match
     */
    public League(List<Competitor> players,MatchStrategy strategy){
        super(players,strategy);
    }

    /**
     *Create new league with list of Competitors with initial strategy : Random Match Strategy 
     *@param players list of participants
     */
    public League(List<Competitor> players){
        super(players,new RandomMatchStrategy());
    }

    /**
     *Play the league, if this league is already played throw an exception
     *@return the winner of the league
     */
    public Competitor play(){
        if(isFinished()){
            throw new RuntimeException("Can't play 2 time a competition");
        }
        for(Competitor comp1 : this.competitors){
            for(Competitor comp2 : this.competitors){
                if(!(comp1.equals(comp2))){
                    playMatch(comp1, comp2);
                }
            }
        }
        competitionFinished = true;
        return this.competitors.stream().max((c1,c2) -> this.getPoints(c1) - this.getPoints(c2)).orElse(null);
    }

}

