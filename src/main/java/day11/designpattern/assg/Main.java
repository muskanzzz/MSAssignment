package designpattern.assg;

public class Main {
    public static void main(String[] args) {
       Operations o1 = FactoryClass.getOperation("even");
        int arr[] = {1,2,3,4,5,6,7,8,9,10};

        System.out.println(o1.performSum(arr));

        Operations o2 = FactoryClass.getOperation("odd");

        System.out.println(o2.performSum(arr));

        Operations o3 = FactoryClass.getOperation("all");
        System.out.println(o3.performSum(arr));
    }


}
