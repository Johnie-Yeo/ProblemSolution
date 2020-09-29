package BOJ;

import Test.Test;

import java.util.*;

public class TwoCoins {
    public static void main(String[] args) {
        new TwoCoins().test();
//        new Main().solve();
    }

    private void test() {
        String input;
        int expect;

        input = "1 2\n" +
                "oo";
        expect = 1;
        testCase(input, expect);

        input = "6 2\n" +
                ".#\n" +
                ".#\n" +
                ".#\n" +
                "o#\n" +
                "o#\n" +
                "##";
        expect = 4;
        testCase(input, expect);

        input = "6 2\n" +
                "..\n" +
                "..\n" +
                "..\n" +
                "o#\n" +
                "o#\n" +
                "##";
        expect = 3;
        testCase(input, expect);

        input = "5 3\n" +
                "###\n" +
                ".o.\n" +
                "###\n" +
                ".o.\n" +
                "###";
        expect = -1;
        testCase(input, expect);

        input = "5 3\n" +
                "###\n" +
                ".o.\n" +
                "#.#\n" +
                ".o.\n" +
                "###";
        expect = 3;
        testCase(input, expect);

        input = "5 3\n" +
                "###\n" +
                ".o.\n" +
                "#.#\n" +
                ".o.\n" +
                "#.#";
        expect = 2;
        testCase(input, expect);

        input = "5 3\n" +
                "###\n" +
                ".o.\n" +
                "#.#\n" +
                ".o#\n" +
                "#.#";
        expect = 2;
        testCase(input, expect);

        input = "5 10\n" +
                "##########\n" +
                "#.........\n" +
                "#o.#######\n" +
                "#o........\n" +
                "##########";
        expect = 9;
        testCase(input, expect);
    }

    private void testCase(String input, int expect) {
        String[] split = input.split("\n",2);
        int N = Integer.parseInt(split[0].split(" ")[0]);
        int M = Integer.parseInt(split[0].split(" ")[1]);
        String[] tmp = split[1].split("\n");
        char[][] map = Arrays.stream(tmp).map(e -> e.toCharArray()).toArray(char[][]::new);

        int result = getMinMoveToDropCoin(N, M, map);
        Test.test(result, expect).printResult();
    }

    private final int MAX = 10;
    private final char COIN = 'o';
    private final char EMPTY = '.';
    private final char WALL = '#';

    private void solve() {
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int M = kb.nextInt();

        char[][] map = new char[N][M];

        for(int i = 0; i < N; i++){
            String tmp = kb.next();
            for(int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        int result = getMinMoveToDropCoin(N, M, map);
        kb.close();

        System.out.println(result);
    }

    private class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Point o = (Point) obj;
            return (this.x == o.x && this.y == o.y);
        }
    }
    private class Status {
        Point p1;
        Point p2;
        int move;

        public Status(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.move = 0;
        }

        public Status(Point p1, Point p2, int move) {
            this.p1 = p1;
            this.p2 = p2;
            this.move = move;
        }

        @Override
        public boolean equals(Object obj) {
            Status o = (Status)obj;

            return (this.p1.equals(o.p1) && this.p2.equals(o.p2));
        }
    }

    private final int[] dirX = {-1, 0, 1, 0};
    private final int[] dirY = {0, -1, 0, 1};

    private int getMinMoveToDropCoin(int N, int M, char[][] map) {
        Status status = getInitStatus(N, M, map);
        Queue<Status> queue = new LinkedList<>();
        queue.add(status);

        while(!queue.isEmpty() && queue.peek().move <= MAX) {
            Status cur = queue.poll();

            for(int d = 0; d < 4; d++) {
                Status next = move(cur, map, d);
                if(next != null) {
                    if(next.p1 == null || next.p2 == null) {
                        return next.move;
                    }
                    queue.add(next);
                }
            }
        }

        return -1;
    }

    private Status move(Status status, char[][] map, int dir) {
        Point p1 = move(status.p1, dir, map);
        Point p2 = move(status.p2, dir, map);
        int move = status.move + 1;

        if(p1 == null && p2 == null) {
            return null;
        } else if((p1 != null && p2 != null) && p1.equals(p2)) {
            return null;
        }

        return new Status(p1, p2, move);
    }

    private Point move(Point p, int dir, char[][]map) {
        int x = p.x + dirX[dir];
        int y = p.y + dirY[dir];

        try{
            if(map[x][y] == WALL) {
                return p;
            }
        }catch (Exception e){
            return null;
        }

        return new Point(x, y);
    }

    private Status getInitStatus(int n, int m, char[][] map) {
        Point p1 = null, p2 = null;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == COIN) {
                    if(p1 == null) {
                        p1 = new Point(i, j);
                        map[i][j] = EMPTY;
                    }else {
                        p2 = new Point(i, j);
                        map[i][j] = EMPTY;
                        break;
                    }
                }
            }
        }

        return new Status(p1, p2);
    }
}
