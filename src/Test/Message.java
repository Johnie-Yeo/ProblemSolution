package Test;

import java.util.List;

public class Message<T>{
    private final String passMessage = "==========Pass==========";
    private final String failMessage = "==========Fail==========";
    private final String failMessageTemplate = "Expect %s but %s returned\n";

    public String getMessage(boolean pass, T result, T expect){
        if(pass){
            return getPassMessage();
        }else{
            return getFailMessage(result, expect);
        }
    }
    public String getMessage(boolean pass, T[] result, T[] expect){
        if(pass){
            return getPassMessage();
        }else{
            return getFailMessage(result, expect);
        }
    }
    public String getMessage(boolean pass, T[][] result, T[][] expect){
        if(pass){
            return getPassMessage();
        }else{
            return getFailMessage(result, expect);
        }
    }
    public String getMessage(boolean pass, List<T> result, List<T> expect) {
        if(pass) {
            return getPassMessage();
        } else {
            return getFailMessage(result, expect);
        }
    }

    private String getPassMessage(){ return passMessage; }
    private String getFailMessage(T result, T expect){
        String reason = String.format(failMessageTemplate, expect.toString(), result.toString());
        return reason + failMessage;
    }
    private String getFailMessage(T[] result, T[] expect){
        String reason = String.format(failMessageTemplate, toString(expect), toString(result));
        return reason + failMessage;
    }
    private String getFailMessage(T[][] result, T[][] expect){
        String reason = String.format(failMessageTemplate, toString(expect), toString(result));
        return reason + failMessage;
    }
    private String getFailMessage(List<T> result, List<T> expect) {
        String reason = String.format(failMessageTemplate, toString(expect), toString(result));
        return reason + failMessage;
    }
    private String toString(T elem){
        return elem.toString();
    }
    private String toString(T[] list){
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        for(T element : list){
            sb.append(toString(element));
            sb.append(", ");
        }
        int size = sb.length();
        sb.replace(size-2, size-1, "");
        sb.append("]");

        return sb.toString();
    }
    private String toString(T[][] list){
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        for(T[] element : list){
            sb.append(toString(element));
            sb.append(", ");
        }
        int size = sb.length();
        sb.replace(size-2, size-1, "");
        sb.append("]");

        return sb.toString();
    }
    private String toString(List<T> list) {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        for(T element : list){
            sb.append(toString(element));
            sb.append(", ");
        }
        int size = sb.length();
        sb.replace(size-2, size-1, "");
        sb.append("]");

        return sb.toString();
    }
}