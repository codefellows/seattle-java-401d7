package demo;

public class PodRacer implements MaintainableVehicle{
    int turbineCount;
    int filtersPerTurbine;

    public PodRacer(int tCount, int filtersPerTurbine){
        this.turbineCount = tCount;
        this.filtersPerTurbine = filtersPerTurbine;
    }

    @Override
    public void changeOil() {
        System.out.println("I have no idea where the oil pan nugget is");
    }

    @Override
    public boolean canIChangeAirFilters(int countOfAirFiltersAvailable) {
        if(countOfAirFiltersAvailable > this.turbineCount * this.filtersPerTurbine){
            return true;
        } else {
            return false;
        }
    }
}
