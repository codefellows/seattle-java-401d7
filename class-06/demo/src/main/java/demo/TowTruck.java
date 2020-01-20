package demo;

public class TowTruck extends Vehicle {

    int towingCapacity;
    int winchSize;

    public TowTruck (int towingCapacity, int winchSize, int doors, long mpg, int max_speed, String name){
        // call the super class o parent class constructor
        super(4, doors, mpg, max_speed, name);
        this.towingCapacity = towingCapacity;
        this.winchSize = winchSize;
    }

    public boolean canIGetThisCar(int weight, int distance){
        if((weight < this.towingCapacity) && distance < winchSize){
            return true;
        }
        return false;
    }

}
