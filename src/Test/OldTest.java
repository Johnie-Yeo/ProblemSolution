package Test;

import java.util.*;

public class OldTest<T> {
    public Result test(T result, T expect){
        Message<T> testMessage = new Message<T>();
        boolean pass = equals(result, expect);
        String message = testMessage.getMessage(pass, result, expect);
        return setResult(pass, message);
    }
    public Result test(T[] result, T[] expect){
        return listTest(toList(result), toList(expect));
    }

    private Result listTest(List<T> result, List<T> expect){
        boolean pass = equals(result, expect);
        String message = null;//this.message.getMessage(pass, result, expect);
        return setResult(pass, message);
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

    private List<T> toList(T[] result) {
        List<T> list = new ArrayList<>();
        for(T element : result){
            list.add(element);
        }
        return list;
    }
    private Result setResult(boolean pass, String message) {
        if(message == null){
            throw new NullPointerException();
        }
        return new Result(pass, message);
    }
}
