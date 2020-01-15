package demo;

import org.junit.Test;

public class VehicleTest {
    @Test public void vehicleTest(){
        Vehicle camry = new Vehicle(4, 4, 32, 212, "Camry");
        System.out.println(camry.goMaxSpeed(200));
        System.out.println("max speed " + camry.max_speed);

        TowTruck mater = new TowTruck(2000, 3, 2, 3, 99, "To-Mater");
        if(mater.canIGetThisCar(1000, 2)){
            System.out.println("yep, I got it");
        }
        if (mater.canIGetThisCar(3000, 3)) {
            System.out.println("that was heave");
        } else {
            System.out.println(("couldnt do it"));
        }

        System.out.println(mater.goMaxSpeed(10));

//        TowTruck a = new Vehicle();
        Vehicle b = new TowTruck(5000, 7, 99, 2, 20, "craney");
        System.out.println(b.goMaxSpeed(99));
        boolean cannotGetInCrane = ((TowTruck) b).canIGetThisCar(9000, 3)
        System.out.println(cannotGetInCrane);
    }
}
