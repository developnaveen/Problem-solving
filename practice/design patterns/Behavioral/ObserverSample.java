interface Observer{
    String sendNotification(Post post);
}

class Post{
    private String postName;
    private String image;

    public  Post(String postName, String image){
        this.image = image;
        this.postName = postName;
    }

    public String getPost(){
        return postName+image;
    }
}

class Notification implements Observer{
    private String userName;

    public Notification(String userName){
        this.userName = userName;
    }

    public String sendNotification(Post post){
        return "Hi " + userName + " new post is uploaded" + post.getPost();
    }
}

class ObserverSample{
    public static void main(String[] args){
        Post post = new Post("java topics", "java.png");

        Notification user1 =  new Notification("Naveen");
        Notification user2 = new Notification("varasan");

        System.out.println(user1.sendNotification(post));
    }
}