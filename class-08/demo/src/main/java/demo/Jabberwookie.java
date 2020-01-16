package demo;

public class Jabberwookie {
    String name;
    boolean sharpClaws;
    String color;
    String favoriteFood;
    String stinkiness;

    public String toString(){
        return String.format("%s is a %s jabberwookie that is stinky in this way: %s", this.name, this.color, this.stinkiness);
    }
}
