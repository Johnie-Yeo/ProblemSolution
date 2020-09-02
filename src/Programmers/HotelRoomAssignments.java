package Programmers;

import Test.OldTest;

import java.util.*;

public class HotelRoomAssignments{
    public static void main(String[] args) {
        new HotelRoomAssignments().test();
    }
    public void test(){
        OldTest test = new OldTest();

        long k = 10;
        long[] room_number = {1, 3, 4, 1, 3, 1};
        long[] result = solution(k, room_number);
        long[] expect = {1, 3, 4, 2, 5, 6};
        test.test(result, expect).printResult();

        k = 3000000000L;
        room_number = new long[200000];
        result = solution(k, room_number);
        expect = new long[200000];
        for(int i = 0; i < 200000; i++){
            expect[i] = i;
        }
        test.test(result, expect).printResult();
    }
    public long[] solution(long k, long[] room_number) {
        HashMap<Long, Long> map = new HashMap<>();
        ArrayList<Long> list = new ArrayList<>();
        for(long room : room_number){
            if(!map.containsKey(room)){
                list.add(room);
                long next = getNextRoom(room, map);
                map.put(room, next);
            }else{
                long next = getNextRoom(room, map);
                list.add(next);
                long nextNext = getNextRoom(next, map);
                map.put(next, nextNext);
            }
        }
        return list.stream().mapToLong(e->e).toArray();
    }

    private long getNextRoom(long room, HashMap<Long, Long> map) {
        if(!map.containsKey(room)){
            map.put(room, room+1);
            return room+1;
        }
        long next = room;
        while(map.containsKey(next)){
            long tmp = next;
            next = map.get(next);
            map.put(room, next);
            room = tmp;
        }
        return next;
    }
}