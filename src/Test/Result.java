package Test;

public class Result{
    private boolean pass;
    private String message;

    public Result(boolean pass, String message){
        this.pass = pass;
        this.message = message;
    }

    public boolean getResult(){
        return this.pass;
    }
    public String getMessage(){
        return this.message;
    }
    public void printResult(){
        System.out.println(getMessage());
    }
}