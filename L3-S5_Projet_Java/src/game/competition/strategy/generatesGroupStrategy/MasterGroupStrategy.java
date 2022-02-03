package game.competition.strategy.generatesGroupStrategy;

import java.util.List;
import game.competition.*;


public abstract class MasterGroupStrategy {

    protected Master master;

    /**
     * Set the master
     * @param mast a master
     */
    public void setMaster(Master mast){
        this.master = mast;
    }
    public abstract List<Competition> generatesGroups();
    
}