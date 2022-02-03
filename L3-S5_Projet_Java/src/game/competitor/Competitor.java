package game.competitor;

public class Competitor{

    private static int competitorIdSequence = 0;
    /**
     *Generate an unique id
     *@return an unique id
     */
    private static int generateUniqueId () {
        return competitorIdSequence++;
    }

    private String name;
    private int id;

    /**
     *Create a competitor with a unique id
     *@param name the name of the competitor
     */
    public Competitor(String name){
        this.name = name;
        this.id = generateUniqueId ();
    }

    /**
     *return the name of the competitor
     *@return the name of the competitor
     */
    public String getName(){
        return this.name;
    }
    
    /**
     *return True if the 2 competitors are equals 
     *@param o an object
     *@return True if the competitors are equals     
     */
    public boolean equals(Object o){
        if(o instanceof Competitor) {
            Competitor that = (Competitor) o;
            return this.id == that.id;
        }
        return false;
    }

    /**
     *return the description of the competitor
     *@return the description of the competitor
     */
    public String toString(){
        return this.name;
    }
}