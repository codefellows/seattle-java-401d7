package demo;

public class Vehicle {
    public int wheels;
    public int doors;
    public long mpg;
    public int max_speed;
    public String name;

    public Vehicle(int wheels, int doors, long mpg, int max_speed, String name) {
        this.wheels = wheels;
        this.doors = doors;
        this.mpg = mpg;
        this.max_speed = max_speed;
        this.name = name;
    }

    public String goMaxSpeed(int hours){
        // calculate gallons used and total distance
        float totalMiles = this.max_speed * hours;
        float totalGallons = totalMiles / this.mpg;


        return String.format(
                "We can zoom for %f miles but it will take this many gallons %f zoom zoom",
                totalMiles,
                totalGallons
        );
    }


}
