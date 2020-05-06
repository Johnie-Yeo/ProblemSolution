package Test;

import java.util.Arrays;

public class Test {
    public <T> Result test(T result, T expect){
        Message<T> testMessage = new Message<T>();
        boolean pass = equals(result, expect);
        String message = testMessage.getMessage(pass, result, expect);
        return setResult(pass, message);
    }
    public <T> Result test(T[] result, T[] expect){
        Message<T> testMessage = new Message<T>();
        boolean pass = equals(result, expect);
        String message = testMessage.getMessage(pass, result, expect);
        return setResult(pass, message);
    }
    private <T> Result test(T[][] result, T[][] expect){
        Message<T> testMessage = new Message<T>();
        boolean pass = equals(result, expect);
        String message = testMessage.getMessage(pass, result, expect);
        return setResult(pass, message);
    }
    public Result test(int[] result, int[] expect){
        Integer[] boxedResult = box(result);
        Integer[] boxedExpect = box(expect);
        return this.test(boxedResult, boxedExpect);
    }
    public Result test(int[][] result, int[][] expect){
        Integer[][] boxedResult = box(result);
        Integer[][] boxedExpect = box(expect);
        return this.test(boxedResult, boxedExpect);
    }



    private <T> boolean equals(T a, T b){
        return a.equals(b);
    }
    private <T> boolean equals(T[] a, T[] b){
        int size = a.length;
        if(size != b.length){
            return false;
        }
        for(int index = 0; index < size; index++){
            if(!equals(a[index], b[index])) {
                return false;
            }
        }
        return true;
    }
    private <T> boolean equals(T[][] a, T[][] b){
        int size = a.length;
        if(size != b.length){
            return false;
        }
        for(int index = 0; index < size; index++){
            if(!equals(a[index], b[index])) {
                return false;
            }
        }
        return true;
    }

    private Result setResult(boolean pass, String message) {
        if(message == null){
            throw new NullPointerException();
        }
        return new Result(pass, message);
    }

    private Integer[] box(int[] arr){
        return Arrays.stream(arr).boxed().toArray(Integer[]::new);
    }
    private Integer[][] box(int[][] array){
        Integer[][] list =
                Arrays.stream(array)
                        .map(arr ->
                                Arrays.stream(arr)
                                        .boxed()
                                        .toArray(Integer[]::new)
                        )
                        .toArray(Integer[][]::new);
        return list;
    }
}