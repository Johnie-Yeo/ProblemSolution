package Test;

public class Result{
    private boolean pass;
    private String message;

    public Result(boolean pass, String message){
        this.pass = pass;
        this.message = message;
    }

    public boolean getPassOrFail(){
        return this.pass;
    }
    public String getMessage(){
        return this.message;
    }
    public void print(){
        System.out.println(getMessage());
    }
}