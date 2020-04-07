package Test;

import java.util.*;

public class NewTest<T> {
    public Result test(T result, T expect){
        boolean pass;
        String message;

        if(equals(result, expect)){
            pass = true;
            message = getPassMessage();
        }else{
            pass = false;
            message = getFailMessage(result, expect);
        }
        return setResult(pass, message);
    }
    public Result test(int[] result, int[] expect){
        Integer[] result1 = Arrays.stream(result).boxed().toArray(Integer[]::new);
//        Integer
        return null;
    }
    public Result test(T[] result, T[] expect){
        List<T> resultList = toList(result);
        List<T> expectList = toList(expect);
        return test(resultList, expectList);
    }

    private Result test(List<T> result, List<T> expect){
        boolean pass;
        String message;

        if(equals(result, expect)){
            pass = true;
            message = getPassMessage();
        }else{
            pass = false;
            message = getFailMessage(result, expect);
        }
        return setResult(pass, message);
    }

    private List<T> toList(T[] result) {
        List<T> list = new ArrayList<>();
        for(T element : result){
            list.add(element);
        }
        return list;
    }

    private boolean equals(T a, T b){
        return a.equals(b);
    }
    private boolean equals(List<T> a, List<T> b){
        int size = a.size();
        if(size != b.size()){
            return false;
        }
        for(int index = 0; index < size; index++){
            if(a.get(index) != b.get(index)) {
                return false;
            }
        }
        return true;
    }

    private String getPassMessage(){
        return "==========Pass==========";
    }
    private String getFailMessage(T result, T expect){
        String template = "Expect %s but %s returned\n";
        String reason = String.format(template, expect.toString(), result.toString());
        String failMsg = "==========Fail==========";

        return reason + failMsg;
    }
    private String getFailMessage(List<T> result, List<T> expect){
        String template = "Expect %s but %s returned\n";
        String reason = String.format(template, toString(expect), toString(result));
        String failMsg = "==========Fail==========";

        return reason + failMsg;
    }

    private String toString(List<T> list){
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        for(T element : list){
            sb.append(element);
            sb.append(", ");
        }
        int size = sb.length();
        sb.replace(size-2, size-1, "");
        sb.append(" ]");

        return sb.toString();
    }

    private Result setResult(boolean pass, String message) {
        if(message == null){
            throw new NullPointerException();
        }
        return new Result(pass, message);
    }
}
