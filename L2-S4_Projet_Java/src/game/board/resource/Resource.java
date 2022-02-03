package game.board.resource;

public enum Resource{
    Sand(5,0),Wood(2,1),Wheat(2,5),Stone(8,0);

    private int price;
    private int food;

    private Resource(int price,int food){
        this.price = price;
        this.food = food;
    }

    /**
    *@return the price of ressource 
    */
    public int getPrice(){
        return this.price;
    }

    /**
    *@return the number of food when we trade 1 resource 
    */
    public int getFood(){
        return this.food;
    }
}


