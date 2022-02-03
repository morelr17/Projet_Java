package game.competition.strategy.qualifiedStrategy;

import java.util.ArrayList;
import java.util.List;
import game.competitor.*;
import game.competition.*;


public class CompositeQualifiedStrategy extends QualifiedByGroupStrategy{
    
    private final List<QualifiedByGroupStrategy> strategies;

    /**
     * Create a CompositeQualifiedStrategy
     * @param strategy1 a QualifiedByGroupStrategy
     * @param strategy2 a QualifiedByGroupStrategy
     */
    public CompositeQualifiedStrategy(QualifiedByGroupStrategy strategy1, QualifiedByGroupStrategy strategy2) {
        this.strategies = new ArrayList<QualifiedByGroupStrategy>();
        this.strategies.add(strategy1);
        this.strategies.add(strategy2);
    }

    /**
     * Create a CompositeQualifiedStrategy
     * @param strategies a List of QualifiedByGroupStrategy
     */
    public CompositeQualifiedStrategy(List<QualifiedByGroupStrategy> strategies) {
        this.strategies = strategies;
    }

    /**
     * return the list of competitor's qualified
     * @return a list of competitor's qualified
     */
    public List<Competitor> get(){
        List<Competitor> qualified = new ArrayList<Competitor>();
        for(QualifiedByGroupStrategy strategy : strategies){
            qualified.addAll(strategy.get());
        }
        return qualified;
    }

    @Override
    public void setMaster(Master master) {
        super.setMaster(master);
        for(QualifiedByGroupStrategy strategy: this.strategies){
            strategy.setMaster(master);
        }
    }
}
