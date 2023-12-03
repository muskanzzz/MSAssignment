package designpattern.assg;

import java.util.Arrays;

public class All implements Operations{
    @Override
    public int performSum(int[] arr) {
        return Arrays.stream(arr).sum();
    }
}
