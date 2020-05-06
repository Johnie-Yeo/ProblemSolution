package Test;

import java.util.ArrayList;

public class TestTest{
    private class TestResult{
        boolean pass;
        String test;
        public TestResult(boolean pass, String test){
            this.pass = pass;
            this.test = test;
        }
    }
    public static void main(String[] args) {
        new TestTest().test();
    }
    private void test(){
        ArrayList<TestResult> passList = new ArrayList<>(){{
            add(new TestResult(intPassTest(), "Int Pass test"));
            add(new TestResult(stringPassTest(), "String Pass test"));
            add(new TestResult(intArrPassTest(), "Int array Pass test"));
            add(new TestResult(int2DArrPassTest(), "Int 2D array Pass test"));
        }};
        ArrayList<TestResult> failList = new ArrayList<>(){{
            add(new TestResult(intFailTest(), "Int Fail test"));
            add(new TestResult(stringFailTest(), "String Fail test"));
            add(new TestResult(intArrFailTest(), "Int array Fail test"));
            add(new TestResult(int2DArrFailTest(), "Int 2D array Fail test"));
        }};

        for(TestResult passTest : passList){
            printPassResult(passTest.pass, passTest.test);
        }
        for(TestResult failTest : failList){
            printFailResult(failTest.pass, failTest.test);
        }
    }
    private void printPassResult(boolean pass, String test){
        if(pass){
            System.out.println("==========PASS==========");
        }else{
            System.out.println("==="+test+" "+"FAIL"+"===");
        }
    }
    private void printFailResult(boolean pass, String test){
        if(!pass){
            System.out.println("==========PASS==========");
        }else{
            System.out.println("==="+test+" "+"FAIL"+"===");
        }
    }
    private boolean intPassTest(){
        Test test = new Test();
        int a = 1;
        int b = 1;
        return test.test(a, b).getResult();
    }
    private boolean intFailTest(){
        Test test = new Test();
        int a = 1;
        int b = 2;
        return test.test(a, b).getResult();
    }
    private boolean stringPassTest(){
        Test test = new Test();
        String a = "1";
        String b = "1";
        return test.test(a, b).getResult();
    }
    private boolean stringFailTest(){
        Test test = new Test();
        String a = "1";
        String b = "2";
        return test.test(a, b).getResult();
    }
    private boolean intArrPassTest(){
        Test test = new Test();
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        test.test(a, b);
        return test.test(a, b).getResult();
    }
    private boolean intArrFailTest(){
        Test test = new Test();
        int[] a = {1, 2, 3};
        int[] b = {1, 2};
        return test.test(a, b).getResult();
    }
    private boolean int2DArrPassTest(){
        Test test = new Test();
        int[][] a = {{1, 2, 3}, {1, 2}};
        int[][] b = {{1, 2, 3}, {1, 2}};
        return test.test(a, b).getResult();
    }
    private boolean int2DArrFailTest(){
        Test test = new Test();
        int[][] a = {{1, 2}, {1, 2}};
        int[][] b = {{1, 2, 3}, {1, 2}};
        return test.test(a, b).getResult();
    }
}