package BOJ;

import Test.OldestTest;

import java.util.*;

public class Bottles {
    private class Bottle{
        int capacity, amount;
        public Bottle(int capacity){
            this.capacity = capacity;
        }
        public Bottle(int capacity, int amount){
            this.capacity = capacity;
            this.amount = amount;
        }
        public void add(Bottle o){
            int available = this.capacity - this.amount;
            int amount = Math.min(o.amount, available);
            this.amount += amount;
            o.amount -= amount;
        }
        public Bottle clone(){
            return new Bottle(this.capacity, this.amount);
        }
        public boolean isFull(){
            return this.capacity == this.amount;
        }
        @Override
        public int hashCode() {
            String capacity = to3DigitString(this.capacity);
            String amount = to3DigitString(this.amount);
            return (capacity+amount).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Bottle o = (Bottle)obj;
            return ((this.capacity == o.capacity) && (this.amount == o.amount));
        }

        private String to3DigitString(int number){
            String toString = Integer.toString(number);
            int length = toString.length();
            int diff = 3 - length;
            for(int i = 0; i < diff; i++){
                toString = "0"+toString;
            }
            return toString;
        }
    }
    public static void main(String[] args) {
//        new Main().solve();
        new Bottles().test();
    }
    private void test(){
        OldestTest test = new OldestTest();
        int A, B, C;
        int[] result, expect;

        A = 8;
        B = 9;
        C = 10;
        result = getAllCombination(A, B, C);
        expect = new int[]{1,2,8,9,10};
        test.test(result, expect);

        A = 8;
        B = 8;
        C = 8;
        result = getAllCombination(A, B, C);
        expect = new int[]{0, 8};
        test.test(result, expect);

        A = 8;
        B = 8;
        C = 9;
        result = getAllCombination(A, B, C);
        expect = new int[]{1, 8, 9};
        test.test(result, expect);

        A = 3;
        B = 8;
        C = 10;
        result = getAllCombination(A, B, C);
        expect = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10};
        test.test(result, expect);

        A = 5;
        B = 10;
        C = 8;
        result = getAllCombination(A, B, C);
        expect = new int[]{0, 3, 5, 8};
        test.test(result, expect);
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);

        int A = kb.nextInt();
        int B = kb.nextInt();
        int C = kb.nextInt();
        int[] result = getAllCombination(A, B, C);
        for(int element : result){
            System.out.print(element + " ");
        }
        System.out.println();

        kb.close();
    }

    private int[] getAllCombination(int a, int b, int c) {
        ArrayList<Bottle> bottles = initBottles(a, b, c);
        ArrayDeque<ArrayList<Bottle>> queue = new ArrayDeque<>();
        HashSet<ArrayList<Bottle>> set = new HashSet<>();
        queue.add(bottles);
        set.add(bottles);

        while(!queue.isEmpty()){
            bottles = queue.pollFirst();

            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(i != j && !bottles.get(i).isFull() && bottles.get(j).amount > 0){
                        ArrayList<Bottle> list = clone(bottles);
                        list.get(i).add(list.get(j));
                        if(!set.contains(list)){
                            set.add(list);
                            queue.add(list);
                        }
                    }
                }
            }
        }

        int[] list = set.stream()
                .filter(element -> element.get(0).amount == 0)
                .map(element -> element.get(2).amount)
                .mapToInt(x -> x)
                .toArray();
        Arrays.sort(list);
        return list;
    }

    private ArrayList<Bottle> clone(ArrayList<Bottle> bottles){
        return new ArrayList<Bottle>(){
            {
                add(bottles.get(0).clone());
                add(bottles.get(1).clone());
                add(bottles.get(2).clone());
            }
        };
    }

    private ArrayList<Bottle> initBottles(int a, int b, int c) {
        return new ArrayList<Bottle>(){
            {
                add(new Bottle(a));
                add(new Bottle(b));
                add(new Bottle(c, c));
            }
        };
    }

}