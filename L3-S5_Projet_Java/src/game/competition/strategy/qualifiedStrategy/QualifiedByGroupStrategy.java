package game.competition.strategy.qualifiedStrategy;

import game.competitor.*;
import game.competition.*;

import java.util.List;


public abstract class QualifiedByGroupStrategy {

    protected Master master;

    /**
     * Set the master
     * @param mast a master
     */
    public void setMaster(Master mast){
        this.master = mast;
    }

    public abstract List<Competitor> get();
}
