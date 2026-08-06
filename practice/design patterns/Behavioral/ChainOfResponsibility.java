abstract class Approver {

    protected Approver next;

    public void setNext(Approver next) {
        this.next = next;
    }

    public abstract void approve(String stage);
}

class Maker extends Approver {

    @Override
    public void approve(String stage) {
        System.out.println("Maker completed");

        if (next != null) {
            next.approve(stage);
        }
    }
}

class Verifier extends Approver {

    @Override
    public void approve(String stage) {
        System.out.println("Verifier completed");

        if (next != null) {
            next.approve(stage);
        }
    }
}

class Checker extends Approver {

    @Override
    public void approve(String stage) {
        System.out.println("Checker completed");
    }
}

public class ChainOfResponsibility {

    public static void main(String[] args) {

        Maker maker = new Maker();
        Verifier verifier = new Verifier();
        Checker checker = new Checker();

        maker.setNext(checker);
        verifier.setNext(checker);

        maker.approve("verifier");
        verifier.approve("checker");
    }
}