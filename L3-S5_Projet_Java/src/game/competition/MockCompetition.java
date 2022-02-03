package game.competition;

import game.competitor.*;
import game.match.strategy.*;

import java.util.List;

public class MockCompetition extends Competition {

    /**
    *Create a MockCompetition
    *@param competitors the participants of the competition
    */
    public MockCompetition(List<Competitor> competitors){
        super(competitors);
    }

    /**
    *Create a MockCompetition
    *@param competitors the participants of the competition
    *@param strategy a match strategy
    */
    public MockCompetition(List<Competitor> competitors,MatchStrategy strategy){
        super(competitors,strategy);
    }

    /**
     *Play the Competition
     *@return the winner of the competition
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
        this.rankingDisplay();
        competitionFinished = true;
        return this.competitors.stream().max((c1,c2) -> this.getPoints(c1) - this.getPoints(c2)).orElse(null);
    }

    /**
     * return true if the league is finished
     * @return true if the league is finished
     */
    public boolean isFinished(){
        return competitionFinished;
    }
}