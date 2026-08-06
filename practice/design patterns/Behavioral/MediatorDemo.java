interface WhatsApp {
    void sendMessage(String message, User sender);
}

abstract class User {

    protected WhatsApp whatsApp;

    public User(WhatsApp whatsApp) {
        this.whatsApp = whatsApp;
    }

    public abstract void receive(String message);
}

class NaveenKumar extends User {

    public NaveenKumar(WhatsApp whatsApp) {
        super(whatsApp);
    }

    public void send(String message) {
        System.out.println("NaveenKumar sent: " + message);
        whatsApp.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println("NaveenKumar received: " + message);
    }
}

class UdhayaKumar extends User {

    public UdhayaKumar(WhatsApp whatsApp) {
        super(whatsApp);
    }

    public void send(String message) {
        System.out.println("UdhayaKumar sent: " + message);
        whatsApp.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println("UdhayaKumar received: " + message);
    }
}

class Chat implements WhatsApp {

    private NaveenKumar naveenKumar;
    private UdhayaKumar udhayaKumar;

    public void setNaveenKumar(NaveenKumar naveenKumar) {
        this.naveenKumar = naveenKumar;
    }

    public void setUdhayaKumar(UdhayaKumar udhayaKumar) {
        this.udhayaKumar = udhayaKumar;
    }

    @Override
    public void sendMessage(String message, User sender) {

        if (sender == naveenKumar) {
            udhayaKumar.receive(message);
        } else if (sender == udhayaKumar) {
            naveenKumar.receive(message);
        }
    }
}

class MediatorDemo{
    public static void main(String[] args){
        Chat chat = new Chat();

        NaveenKumar naveenKumar = new NaveenKumar(chat);
        UdhayaKumar udhayaKumar = new UdhayaKumar(chat);

        chat.setNaveenKumar(naveenKumar);
        chat.setUdhayaKumar(udhayaKumar);

        naveenKumar.send("Hi pattabiraman");
        udhayaKumar.send("Hi Matti");
    }
}
