interface Strategy {
    String travel();
}

class Bus implements Strategy {

    @Override
    public String travel() {
        return "Travelling by Bus";
    }
}

class Car implements Strategy {

    @Override
    public String travel() {
        return "Travelling by Car";
    }
}

class TravelPartner {

    private Strategy strategy;

    public TravelPartner(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void startJourney() {
        System.out.println(strategy.travel());
    }
}

public class StrategySample {

    public static void main(String[] args) {

        TravelPartner partner = new TravelPartner(new Bus());

        partner.startJourney();

        partner.setStrategy(new Car());

        partner.startJourney();
    }
}