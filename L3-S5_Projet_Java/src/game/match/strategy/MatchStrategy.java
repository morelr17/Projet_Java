package game.match.strategy;

import game.competitor.*;

public interface MatchStrategy {

    public Competitor operate(Competitor competitor1, Competitor competitor2);
}