package game.competition.strategy.generatesGroupStrategy;

import game.competition.*;
import game.competitor.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class MinGroupStrategy extends MasterGroupStrategy {
    
    /**
     * return the list of groups
     * @return the list of groups
     */
    public List<Competition> generatesGroups(){

        int nbOfCompetitors = this.master.getCompetitors().size();
        if(nbOfCompetitors <= 3){
            throw new RuntimeException("Impossible to play a Master with 3 competitors");
        }
        //On va créer une copie des competitors pour ne pas la toucher directement
        final List<Competitor> competitorsCopy = new ArrayList<>(this.master.getCompetitors());

        //On mélange la liste pour avoir une liste aléatoire
        Collections.shuffle(competitorsCopy);

        final List<Competition> groups = new ArrayList<>();

        while (!competitorsCopy.isEmpty()) {
            final List<Competitor> competitorsForGroup = new ArrayList<>(competitorsCopy.subList(0, nbOfCompetitors / 2));
            groups.add(new League(competitorsForGroup));
            competitorsCopy.removeIf(competitorsForGroup::contains);
        }

        return groups;
    }
}