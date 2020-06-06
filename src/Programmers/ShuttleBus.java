package Programmers;

import Test.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ShuttleBus {
    public static void main(String[] args) {
        new ShuttleBus().test();
    }

    private void test() {
        Test test = new Test();

        int n, t, m;
        String[] timeTable;
        String result, expect;

        n = 1;
        t = 1;
        m = 5;
        timeTable = new String[]{"08:00", "08:01", "08:02", "08:03"};
        expect = "09:00";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 2;
        t = 10;
        m = 2;
        timeTable = new String[]{ "09:10", "09:09", "08:00" };
        expect = "09:09";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 2;
        t = 1;
        m =	2;
        timeTable = new String[]{"09:00", "09:00", "09:00", "09:00"};
        expect = "08:59";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 1;
        t =	1;
        m =	5;
        timeTable = new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"};
        expect = "00:00";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 1;
        t =	1;
        m =	1;
        timeTable = new String[]{"23:59"};
        expect = "09:00";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 10;
        t =	60;
        m =	45;
        timeTable = new String[]{
                "23:59", "23:59", "23:59", "23:59", "23:59",
                "23:59", "23:59", "23:59", "23:59", "23:59",
                "23:59", "23:59", "23:59", "23:59", "23:59",
                "23:59"
        };
        expect = "18:00";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 10;
        t =	60;
        m =	10;
        timeTable = new String[]{
                "23:59", "23:59", "23:59", "23:59", "23:59",
                "23:59", "23:59", "23:59", "23:59", "23:59"
        };
        expect = "18:00";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 2;
        t = 10;
        m = 3;
        timeTable = new String[]{"09:00", "09:00", "09:00", "09:10"};
        expect = "09:10";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 3;
        t = 1;
        m = 2;
        timeTable = new String[]{"09:00", "09:00", "09:00", "09:00", "09:01", "09:01"};
        expect = "09:00";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 3;
        t = 1;
        m = 2;
        timeTable = new String[]{"09:00", "09:00", "09:00", "09:00", "09:02", "09:02"};
        expect = "09:01";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 1;
        t = 1;
        m = 3;
        timeTable = new String[]{"09:00", "09:00", "09:00", "09:00", "08:02", "08:30"};
        expect = "08:59";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 2;
        t = 10;
        m = 3;
        timeTable = new String[]{"09:00", "09:00", "09:19", "09:05", "09:05", "09:05"};
        expect = "09:04";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 2;
        t = 10;
        m = 3;
        timeTable = new String[]{"09:00", "09:00", "09:09", "08:05", "09:05", "09:05"};
        expect = "09:08";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 2;
        t = 10;
        m = 3;
        timeTable = new String[]{"09:00", "09:02", "08:05", "09:05", "09:05"};
        expect = "09:04";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();

        n = 2;
        t = 10;
        m = 3;
        timeTable = new String[]{"09:10", "09:10","09:10","09:10","09:10","09:10"};
        expect = "09:09";
        result = solution(n, t, m, timeTable);
        test.test(result, expect).printResult();
    }

    private class Time implements Comparable<Time>{
        int hour, min;
        public Time(int hour, int min){
            this.hour = hour;
            this.min = min;
        }
        public Time add(int min){
            min += this.min;
            int hour = this.hour;
            if(min >= 60){
                min -= 60;
                hour++;
            }
            return new Time(hour, min);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.hour, this.min});
        }

        @Override
        public boolean equals(Object obj) {
            Time o = (Time)obj;
            return (this.hour == o.hour && this.min == o.min);
        }

        @Override
        public int compareTo(Time o) {
            if(this.hour == o.hour){
                return this.min - o.min;
            }else{
                return this.hour - o.hour;
            }
        }

        @Override
        public String toString() {
            String hour = this.parseNumberToString(this.hour);
            String min = this.parseNumberToString(this.min);
            String result = hour + ":" + min;
            return result;
        }

        private String parseNumberToString(int num) {
            if(num < 10){
                return "0"+num;
            }
            return Integer.toString(num);
        }

        public Time sub(int num) {
            int min = this.min - num;
            int hour = this.hour;
            if(min < 0){
                min += 60;
                hour--;
            }
            return new Time(hour, min);
        }
    }

    public String solution(int n, int t, int m, String[] timetable) {
        ArrayList<Time> timetableList = getTimetableList(timetable);
        ArrayList<Time> busTime = getBusTime(n, t);
        HashMap<Time, ArrayList<Time>> status = getCurStatus(timetableList, busTime, m);

        int index = busTime.size() - 1;
        Time time = getLatestAvailableTime(busTime, index, status, m);
        String result = time.toString();

        return result;
    }

    private Time getLatestAvailableTime(ArrayList<Time> busTime, int index, HashMap<Time, ArrayList<Time>> status, int max) {
        Time bus = busTime.get(index);
        ArrayList<Time> row = status.get(bus);
        Time nextFirst = getNextFirst(busTime, index+1, status);
        Time prevLast = getPrevLast(busTime, index-1, status);

        if(row == null || row.size() == 0 || row.size() < max){
            return bus;
        }else{
            Time time = getLatestAvailableTimeInRow(row);
            if(time == null){
                Time curLast = row.get(row.size()-1);
                Time curFirst = row.get(0);

                if(prevLast == null || prevLast.compareTo(curFirst) < 0){
                    return curFirst.sub(1);
                }else if(nextFirst != null && curLast.compareTo(nextFirst) < 0){
                    return nextFirst.sub(1);
                }else if(index > 0){
                    return getLatestAvailableTime(busTime, index-1, status, max);
                }else{
                    Time result = row.get(0).sub(1);
                    return result;
                }
            }
            return time;
        }
    }

    private Time getPrevLast(ArrayList<Time> busTime, int i, HashMap<Time, ArrayList<Time>> status) {
        try{
            Time bus = busTime.get(i);
            ArrayList<Time> row = status.get(bus);
            Time result = row.get(row.size()-1);
            return result;
        }catch (Exception e){
            return null;
        }
    }

    private Time getNextFirst(ArrayList<Time> busTime, int i, HashMap<Time, ArrayList<Time>> status) {
        try{
            Time bus = busTime.get(i);
            Time result = status.get(bus).get(0);
            return result;
        }catch (Exception e){
            return null;
        }
    }

    private Time getLatestAvailableTimeInRow(ArrayList<Time> row) {
        int size = row.size();
        Time last = row.get(size-1);
        for(int i = size-2; i >= 0; i--){
            if(!row.get(i).equals(last)){
                Time result = row.get(i+1).sub(1);
                return result;
            }
        }
        return null;
    }

    private HashMap<Time, ArrayList<Time>> getCurStatus(ArrayList<Time> timetable, ArrayList<Time> busTime, int max) {
        HashMap<Time, ArrayList<Time>> map = new HashMap<>();

        int index = 0;
        int crewSize = timetable.size();

        for(Time bus : busTime){
            int count = 0;
            ArrayList<Time> cur = new ArrayList<>();
            while(index < crewSize && count < max && timetable.get(index).compareTo(bus) <= 0){
                Time crew = timetable.get(index);
                cur.add(crew);
                count++;
                index++;
            }
            map.put(bus, cur);
        }

        return map;
    }

    private ArrayList<Time> getBusTime(int n, int t) {
        ArrayList<Time> list = new ArrayList<>();
        Time time = new Time(9,0);
        for(int i = 0; i < n; i++){
            list.add(time);
            time  = time.add(t);
        }
        return list;
    }

    private ArrayList<Time> getTimetableList(String[] timetable) {
        ArrayList<Time> list = Arrays.stream(timetable)
                                    .map(e -> parseStringToTime(e))
                                    .collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(list);
        return list;
    }

    private Time parseStringToTime(String time){
        String[] parsed = time.split(":");
        int hour = Integer.parseInt(parsed[0]);
        int min  = Integer.parseInt(parsed[1]);
        Time result = new Time(hour, min);
        return result;
    }
}
