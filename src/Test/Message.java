package Test;

import java.util.List;

public class Message<T>{
    public String getMessage(boolean pass, T result, T expect){
        if(pass){
            return getPassMessage();
        }else{
            return getFailMessage(result, expect);
        }
    }
    public String getMessage(boolean pass, List<T> result, List<T> expect){
        if(pass){
            return getPassMessage();
        }else{
            return getFailMessage(result, expect);
        }
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
}