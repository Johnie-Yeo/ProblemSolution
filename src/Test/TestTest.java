package Test;

public class TestTest{
    public static void main(String[] args) {
        new TestTest().tmp();

//        TestTest test = new TestTest();
//        test.intTest();
//        test.StringTest();
//        test.intListTest();
//        test.intListListTest();
    }
    private void tmp(){
        int[] a = {1,2,3};
//        p(a);
        int b = 1;
        q(b);
    }
    private void q(Object b){

    }
    private void p(Object[] a){

    }
//    private void intTest(){
//        Test<Integer> intTest = new Test<Integer>();
//        Result result = intTest.test(1, 1);
//        System.out.println(result.getPassOrFail() == true);
//
//        result = intTest.test(1, 2);
//        System.out.println(result.getPassOrFail() == false);
//    }
//
//    private void StringTest() {
//        Test<String> stringTest = new Test<String>();
//        Result result = stringTest.test("hello", "hello");
//        System.out.println(result.getPassOrFail() == true);
//
//        result = stringTest.test("hello", "hi");
//        System.out.println(result.getPassOrFail() == false);
//    }
//
//    private void intListTest(){
//
//    }
//
//    private void intListListTest(){
//
//    }
}