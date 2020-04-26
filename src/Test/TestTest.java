package Test;

public class TestTest{
    public static void main(String[] args) {
        new TestTest().test();
    }
    private void test(){
        intPassTest();
        intFailTest();
    }
    private boolean intPassTest(){
        Test<Integer> test = new Test<Integer>();
        int a = 1;
        int b = 1;
        return test.test(a, b).getResult();
    }
    private boolean intFailTest(){
        Test<Integer> test = new Test<Integer>();
        int a = 1;
        int b = 2;
        return test.test(a, b).getResult();
    }
    private boolean StringPassTest(){
        Test<String> test = new Test<String>();
        String a = "1";
        String b = "1";
        return test.test(a, b).getResult();
    }
    private boolean StringFailTest(){
        Test<String> test = new Test<String>();
        String a = "1";
        String b = "2";
        return test.test(a, b).getResult();
    }
}