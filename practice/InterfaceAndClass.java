class InterfaceAndClass{
    interface userprofile{

        userprofile getUser();
        void setUser(int id, String name, int age, String address);
    }

    class Person implements userprofile{
        private int id;
        private String name;
        private int age;
        private String address;

        @Override
        public void setUser(int id, String name, int age, String address){
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
        }

        @Override
        public userprofile getUser(){
            return this;
        }


        public String print(){
            return "id: "+this.id+" name: "+this.name+" age: "+this.age+" address: "+this.address;
        }
    }

    public static void main(String[] args){
        InterfaceAndClass outer = new InterfaceAndClass();
        Person p = outer.new Person();

        p.setUser(1, "Naveen", 24, "Chennai");
        System.out.println(p.print());
    }
}