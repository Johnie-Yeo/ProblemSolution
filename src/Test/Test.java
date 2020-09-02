package Test;

import java.util.Arrays;

public class Test {
    public static <T> Result test(T result, T expect){
        Message<T> testMessage = new Message<T>();
        boolean pass = equals(result, expect);
        String message = testMessage.getMessage(pass, result, expect);
        return setResult(pass, message);
    }
    public static <T> Result test(T[] result, T[] expect){
        Message<T> testMessage = new Message<T>();
        boolean pass = equals(result, expect);
        String message = testMessage.getMessage(pass, result, expect);
        return setResult(pass, message);
    }
    private static <T> Result test(T[][] result, T[][] expect){
        Message<T> testMessage = new Message<T>();
        boolean pass = equals(result, expect);
        String message = testMessage.getMessage(pass, result, expect);
        return setResult(pass, message);
    }
    public static Result test(int[] result, int[] expect){
        Integer[] boxedResult = box(result);
        Integer[] boxedExpect = box(expect);
        return test(boxedResult, boxedExpect);
    }
    public static Result test(long[] result, long[] expect){
        Long[] boxedResult = box(result);
        Long[] boxedExpect = box(expect);
        return test(boxedResult, boxedExpect);
    }
    public static Result test(int[][] result, int[][] expect){
        Integer[][] boxedResult = box(result);
        Integer[][] boxedExpect = box(expect);
        return test(boxedResult, boxedExpect);
    }



    private static <T> boolean equals(T a, T b){
        return a.equals(b);
    }
    private static <T> boolean equals(T[] a, T[] b){
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
    private static <T> boolean equals(T[][] a, T[][] b){
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

    private static Result setResult(boolean pass, String message) {
        if(message == null){
            throw new NullPointerException();
        }
        return new Result(pass, message);
    }

    private static Integer[] box(int[] arr){
        return Arrays.stream(arr).boxed().toArray(Integer[]::new);
    }
    private static Long[] box(long[] arr){
        return Arrays.stream(arr).boxed().toArray(Long[]::new);
    }
    private static Integer[][] box(int[][] array){
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
