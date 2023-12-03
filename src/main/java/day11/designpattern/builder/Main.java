package designpattern.builder;

public class Main {
    public static void main(String[] args) {
        User user = new User.Userbuilder().setUserId("123").setUserName("muskz").setEmailId("musk@gmail.com").build();
        System.out.println(user);
    }
}
