abstract class Beverage {

    // Template Method
    public final void prepare() {
        boilWater();
        addIngredient();
        serve();
    }

    private void boilWater() {
        System.out.println("Boiling water");
    }

    protected abstract void addIngredient();

    private void serve() {
        System.out.println("Serving");
    }
}

class Tea extends Beverage {

    @Override
    protected void addIngredient() {
        System.out.println("Adding tea leaves");
    }
}

class Coffee extends Beverage {

    @Override
    protected void addIngredient() {
        System.out.println("Adding coffee powder");
    }
}
public class TemplateMethodSample {

    public static void main(String[] args) {

        Beverage tea = new Tea();
        tea.prepare();

        System.out.println();

        Beverage coffee = new Coffee();
        coffee.prepare();
    }
}