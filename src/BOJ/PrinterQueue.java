package BOJ;

import Test.OldTest;

import java.util.*;

public class PrinterQueue {
    public static void main(String[] args) {
        new PrinterQueue().solve();
//        new PrinterQueue().test();
    }
    private void test(){
        OldTest test = new OldTest();

        int T, N, M;
        String input;
        int[] documents;
        int result, expect;
        final String DELIM = " ";
        T = 3;

        N = 1;
        M = 0;
        input = "5";
        documents = Arrays.stream(input.split(DELIM)).mapToInt(e -> Integer.parseInt(e)).toArray();
        result = gerOrder(N, M, documents);
        expect = 1;
        test.test(result, expect).printResult();

        N = 4;
        M = 2;
        input = "1 2 3 4";
        documents = Arrays.stream(input.split(DELIM)).mapToInt(e -> Integer.parseInt(e)).toArray();
        result = gerOrder(N, M, documents);
        expect = 2;
        test.test(result, expect).printResult();

        N = 4;
        M = 0;
        input = "2 1 3 4";
        documents = Arrays.stream(input.split(DELIM)).mapToInt(e -> Integer.parseInt(e)).toArray();
        result = gerOrder(N, M, documents);
        expect = 3;
        test.test(result, expect).printResult();

        N = 6;
        M = 0;
        input = "1 1 9 1 1 1";
        documents = Arrays.stream(input.split(DELIM)).mapToInt(e -> Integer.parseInt(e)).toArray();
        result = gerOrder(N, M, documents);
        expect = 5;
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);
        String input;
        final String DELIM = " ";

        input = kb.nextLine();
        int T = Integer.parseInt(input);
        for(int t = 0; t < T; t++){
            input = kb.nextLine();
            String[] tmp = input.split(DELIM);
            int N = Integer.parseInt(tmp[0]);
            int M = Integer.parseInt(tmp[1]);
            input = kb.nextLine();
            int[] documents = Arrays.stream(input.split(DELIM))
                                    .mapToInt(e -> Integer.parseInt(e))
                                    .toArray();
            int result = gerOrder(N, M, documents);
            System.out.println(result);
        }
        kb.close();
    }
    private class Document{
        int index, priority;
        public Document(int index, int priority){
            this.index = index;
            this.priority = priority;
        }
    }
    private int gerOrder(int n, int m, int[] documents) {
        Queue<Document> queue = initQueue(documents);
        int order = 1;
        while(true){
            Document cur = queue.poll();
            if(!isHighest(cur, queue)){
                queue.add(cur);
            }else{
                if(cur.index == m){
                    return order;
                }
                order++;
            }
        }
    }

    private boolean isHighest(Document cur, Queue<Document> queue) {
        for(Document doc : queue){
            if(doc.priority > cur.priority){
                return false;
            }
        }
        return true;
    }

    private Queue<Document> initQueue(int[] documents) {
        LinkedList<Document> queue = new LinkedList<>();
        int index = 0;
        for(int document : documents){
            Document doc = new Document(index++, document);
            queue.add(doc);
        }
        return queue;
    }
}
