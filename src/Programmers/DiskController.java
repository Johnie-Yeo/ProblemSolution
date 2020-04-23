package Programmers;

import Test.OldTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DiskController {
    public static void main(String[] args) {
        new DiskController().test();
    }
    private void test() {
        OldTest test = new OldTest();

        int[][] jobs = {{0, 3}, {2, 6}, {1, 9}};
        int expect = 9;
        int result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{1, 9}, {3, 5}};
        expect = 10;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{3, 5}, {1, 9}};
        expect = 10;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{0, 2}, {1, 9}, {3, 5}};
        expect = 8;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{0, 5}, {1, 2}, {5, 5}};
        expect = 6;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{0, 6}, {100, 2}};
        expect = 4;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{0, 1}, {0, 2}, {2, 1}};
        expect = 2;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{0, 6}, {1, 1}};
        expect = 6;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{0, 3}, {3, 5}, {1, 9}};
        expect = 8;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{100, 900}, {300, 500}, {0, 300}};
        expect = 800;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{0, 3}, {1, 9}, {500, 6}};
        expect = 6;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{0, 9}, {0, 4}, {0, 5}, {0, 7}, {0, 3}};
        expect = 13;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{26, 1}, {24, 10}, {18, 39}, {34, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 2}, {15, 34}, {35, 43}};
        expect = 74;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{24, 10}, {18, 39}, {34, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 2}, {15, 34}, {35, 43}, {26, 1}};
        expect = 74;
        result = solution(jobs);
        test.test(result, expect);

        jobs = new int[][]{{24, 10}, {18, 39}, {34, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 34}, {15, 2}, {35, 43}, {26, 1}};
        expect = 72;
        result = solution(jobs);
        test.test(result, expect);
    }
    private class Job implements Comparable<Job>{
        private int start;
        private int duration;
        public Job(int start, int duration){
            this.start = start;
            this.duration = duration;
        }
        public int getStart(){
            return this.start;
        }
        public int getDuration(){
            return this.duration;
        }
        public int getEnd(){
            return this.start + this.duration;
        }
        public int getLatency(int start){
            return Math.max(start - this.start, 0);
        }
        @Override
        public int compareTo(Job job) {
            return this.duration - job.duration;
        }
    }
    public int solution(int[][] jobs){
        ArrayList<Job> jobList = buildJobList(jobs);
        int sum = 0;
        int size = jobList.size();
        int time = 0;//가장 최근 작업 종료 시

        PriorityQueue<Job> pq = new PriorityQueue<>();

        for(Job job : jobList){
            if(isAvailableToDo(job, time, pq)){
                sum += job.getDuration();
                time = job.getEnd();
            }else if(time < job.getStart()){
                while(!pq.isEmpty() && time < job.getStart()){
                    Job cur = pq.poll();
                    int latency = cur.getLatency(time);
                    sum += (latency + cur.getDuration());
                    time = latency + cur.getEnd();
                }
                pq.add(job);
            }else{
                pq.add(job);
            }
        }

        while(!pq.isEmpty()){
            Job cur = pq.poll();
            int latency = Math.max(time - cur.getStart(), 0);
            sum += (latency + cur.getDuration());
            time = latency + cur.getStart() + cur.getDuration();
        }

        return sum / size;
    }

    private boolean isAvailableToDo(Job job, int time, PriorityQueue<Job> pq) {
        if(pq.isEmpty() && job.start >= time){
            return true;
        }
        return false;
    }

    private ArrayList<Job> buildJobList(int[][] jobs){
        ArrayList<Job> list = new ArrayList<>();
        for(int[] job : jobs){
            list.add(new Job(job[0], job[1]));
        }
        Collections.sort(list, new Comparator<Job>() {
            @Override
            public int compare(Job job1, Job job2) {
                if(job1.start == job2.start){
                    return job1.duration - job2.duration;
                }
                return job1.start - job2.start;
            }
        });
        return list;
    }
}
