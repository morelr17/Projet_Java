package game.match.strategy;

import game.competitor.*;
import java.util.Random;

public class RandomMatchStrategy implements MatchStrategy {

    /**
     *Play the random match with the same chance to win for the 2 competitors 
     *@param competitor1 a competitor
     *@param competitor2 a competitor
     *@return the winner of the match
     */
    public Competitor operate(Competitor competitor1, Competitor competitor2){
        Random rand = new Random();
        int luck = rand.nextInt(2);

        if(luck == 0){
            return competitor1;
        } else {
            return competitor2;
        }
    }
}