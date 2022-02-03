package game.competition.strategy.qualifiedStrategy;

import game.competition.*;
import game.competitor.*;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map.Entry;
import java.util.Iterator;


public class TakeBestByRankedStrategy extends QualifiedByGroupStrategy{

    private int nBest;
    private int rank;

    /**
     * Create a TakeBestByRankedStrategy
     * @param nBest the number of competitors to take a the rank position
     * @param rank the rank
     */
    public TakeBestByRankedStrategy(int nBest,int rank){
        this.rank = rank;
        this.nBest = nBest;
    }

    /**
     * return the qualified for the next stage
     * @param iterator an iterator
     * @return the qualified for the next stage
     */
    public Entry<Competitor,Integer> takeAtRank(Iterator<Entry<Competitor,Integer>> iterator){
        int i = 0;
        Entry<Competitor,Integer> result;
        do {
            result = iterator.next();
        }while (i++ < this.rank);
        return result;
    }

    /**
     * return the list of competitor's qualified
     * @return a list of competitor's qualified
     */
    public List<Competitor> get(){
        List<Entry<Competitor,Integer>> listOfThirds = new ArrayList<Entry<Competitor,Integer>>();
        for(Competition competition:master.getGroupStages()){
            listOfThirds.add(takeAtRank(competition.ranking().entrySet().iterator())) ;
        }

        listOfThirds.sort((e1, e2) -> e2.getValue() - e1.getValue());

        return listOfThirds.subList(0, nBest).stream().map(entry -> entry.getKey()).collect(Collectors.toList());
    }
}
