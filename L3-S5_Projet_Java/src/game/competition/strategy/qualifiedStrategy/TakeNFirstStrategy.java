package game.competition.strategy.qualifiedStrategy;

import game.competition.*;
import game.competitor.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;


public class TakeNFirstStrategy extends QualifiedByGroupStrategy{
    
    private int nbOfQualfied;

    /**
     * create a TakeNFirstStrategy
     * @param nbOfQualfied an int
     */
    public TakeNFirstStrategy(int nbOfQualfied){
        this.nbOfQualfied = nbOfQualfied;
    }

    /**
     * return the list of competitor's qualified
     * @return a list of competitor's qualified
     */
    public List<Competitor> get(){
        if(!master.isGroupsStagesFinished()){
            throw new RuntimeException("the groups stage are not finished");
        }

        List<Competitor> qualified = new ArrayList<Competitor>();
        
        for(Competition competition:master.getGroupStages()){
            int i=0;
            Iterator<Entry<Competitor,Integer>> iterator = competition.ranking().entrySet().iterator();
            while(iterator.hasNext() && i++ < nbOfQualfied){
                qualified.add(iterator.next().getKey());
            }
        }
        return qualified;
    }
}
