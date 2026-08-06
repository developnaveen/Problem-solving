interface State {
    void write(String words);
}

class UpperLetters implements State {

    @Override
    public void write(String words) {
        System.out.println(words.toUpperCase());
    }
}

class LowerLetters implements State {

    @Override
    public void write(String words) {
        System.out.println(words.toLowerCase());
    }
}

class DefaultState implements State {

    @Override
    public void write(String words) {
        System.out.println(words);
    }
}

class Editor {

    private State state;

    public Editor(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void print(String words) {
        state.write(words);
    }
}

public class StateSample {

    public static void main(String[] args) {

        Editor editor = new Editor(new DefaultState());

        editor.print("Hello World");

        editor.setState(new UpperLetters());
        editor.print("Hello World");

        editor.setState(new LowerLetters());
        editor.print("Hello World");
    }
}