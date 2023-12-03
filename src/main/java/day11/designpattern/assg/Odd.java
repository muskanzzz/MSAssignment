package designpattern.assg;

import java.util.Arrays;

public class Odd implements Operations{


    @Override
    public int performSum(int[] arr) {
        return Arrays.stream(arr).filter(i->i%2!=0).sum();
    }
}
