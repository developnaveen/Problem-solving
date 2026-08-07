interface Vehicle {
    void drive();
}
class Car implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Driving Car");
    }
}

class Bus implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Driving Bus");
    }
}
class VehicleFactory {

    public static Vehicle getVehicle(String type) {

        if (type.equalsIgnoreCase("car")) {
            return new Car();
        }

        if (type.equalsIgnoreCase("bus")) {
            return new Bus();
        }

        throw new IllegalArgumentException("Unknown vehicle");
    }
}
public class SimpleFactory {

    public static void main(String[] args) {

        Vehicle vehicle = VehicleFactory.getVehicle("car");

    }        vehicle.drive();

}