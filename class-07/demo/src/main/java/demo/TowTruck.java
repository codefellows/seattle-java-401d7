package demo;

// I want all tow trucks to be able to get maintenance
// I would like to run a mechanic shop
// I honestly don't care about how a car needs its oil changed
// I want this care to have a method changeOil, I don't care how it gets implemented
    // for a truck, changing oil involves loosening the thingy and swapping the other thingy

// PodRacer -they need their oil changed too
    // it involves taking apart the thrusters, replacing the gizmos, and filling the flux capacitor to 87.7

public class TowTruck extends Vehicle implements MaintainableVehicle{

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

    // Implemented methods from the interface here

    @Override
    public void changeOil() {
        System.out.println(this.name + " unscrew the thingy and replace another thingy with an oil pain bolt");
    }

    @Override
    public boolean canIChangeAirFilters(int countOfAirFiltersAvailable) {
        if(countOfAirFiltersAvailable > 0){
            System.out.println("Luckily there were enough filters for the truck");
            return true;
        }
        System.out.println("not enough filters for the truck");
        return false;


    }
}
