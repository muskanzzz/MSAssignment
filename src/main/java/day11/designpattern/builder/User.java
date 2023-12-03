package designpattern.builder;

public class User {
    private final String userId;
    private final String userName;
    private final String emailId;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    private User(Userbuilder userbuilder){
            this.userId=userbuilder.userId;
            this.userName=userbuilder.userName;
            this.emailId=userbuilder.emailId;
    }

    static class Userbuilder{
        private  String userId;
        private  String userName;
        private  String emailId;



        public Userbuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Userbuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Userbuilder setEmailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public User build(){
            User user = new User(this);
            return user;
        }
    }

}
