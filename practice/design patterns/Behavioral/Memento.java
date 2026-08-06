class Memory{
    private String content;

    public Memory(String content){
        this.content=content;
    }

    public String getContent() {
        return content;
    }
}

class NotePad{
    private String content;

    public String getContent() {
        return content;
    }

    public Memory save() {
        return new Memory(content);
    }

    public void restore(Memory memory) {
        this.content = memory.getContent();
    }

    public void type(String content){
        this.content=content;
    }
}


public class Memento {

    public static void main(String[] args) {

        NotePad notePad = new NotePad();

        notePad.type("First typed");
        System.out.println(notePad.getContent());

        Memory memory = notePad.save();

        notePad.type("Second typed");
        System.out.println(notePad.getContent());

        notePad.restore(memory);

        System.out.println(notePad.getContent());
    }
}