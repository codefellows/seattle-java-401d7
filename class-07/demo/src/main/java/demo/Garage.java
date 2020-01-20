package demo;

import java.util.ArrayList;

public class Garage {
    public static void main(String[] args){
        TowTruck mater = new TowTruck(77, 9, 2, 12, 24, "To-Mater");
        mater.canIChangeAirFilters(4);
        mater.changeOil();

        PodRacer annies = new PodRacer(2, 12);

        if(annies.canIChangeAirFilters(12)){
            System.out.println("luckily I had enough air filters");
        } else {
            System.out.println("not enough filters");
        }

        MaintainableVehicle materAsInterface = mater;
        MaintainableVehicle aniAsInterface = annies;

        materAsInterface.changeOil();
        aniAsInterface.changeOil();
        System.out.println(aniAsInterface.canIChangeAirFilters(30));

        ArrayList<MaintainableVehicle> maintainableThings = new ArrayList<>();
        maintainableThings.add(annies);
        maintainableThings.add(mater);
        maintainableThings.add(new TowTruck(90, 90, 88, 2, 300, "Supertruck"));

        // I can use these things inside maintainableThings as MaintainableVehicles
        // this means all I can do is call the two methods on them that come from MaintainableVehicles

        for(MaintainableVehicle v : maintainableThings){
            v.changeOil();
        }

    }
}
