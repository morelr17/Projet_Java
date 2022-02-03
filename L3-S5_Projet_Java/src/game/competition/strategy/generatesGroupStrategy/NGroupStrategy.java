package game.competition.strategy.generatesGroupStrategy;

import game.competition.*;
import game.competitor.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class NGroupStrategy extends MasterGroupStrategy{
    
    private int nbOfGroups;

    public NGroupStrategy(int nbOfGroups) {
        this.nbOfGroups = nbOfGroups;
    }

    /**
     * return the list of groups
     * @return the list of groups
     */
    public List<Competition> generatesGroups(){
        int nbOfCompetitors = this.master.getCompetitors().size();
        //On va créer une copie des competitors pour ne pas la toucher directement
        final List<Competitor> competitorsCopy = new ArrayList<>(this.master.getCompetitors());

        //On mélange la liste pour avoir une liste aléatoire
        Collections.shuffle(competitorsCopy);

        final List<Competition> groups = new ArrayList<>();

        while (!competitorsCopy.isEmpty()) {
            final List<Competitor> competitorsForGroup = new ArrayList<>(competitorsCopy.subList(0, nbOfCompetitors / this.nbOfGroups));
            groups.add(new League(competitorsForGroup));
            competitorsCopy.removeIf(competitorsForGroup::contains);
        }

        return groups;
    }
}
