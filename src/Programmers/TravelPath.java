package Programmers;

import Test.OldestTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class TravelPath{
    public static void main(String[] args) {
        new TravelPath().test();
    }
    private void test(){
        OldestTest test = new OldestTest();

        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[] expect = {"ICN", "JFK", "HND", "IAD"};
        String[] result = solution(tickets);
        test.test(result, expect);

        tickets = new String[][]{
                {"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}
        };
        expect = new String[]{"ICN", "ATL", "ICN", "SFO", "ATL", "SFO"};
        result = solution(tickets);
        test.test(result, expect);

        tickets = new String[][]{
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "YVR"},
                {"ATL", "BNE"},
                {"YVR", "LAX"},
                {"LAX", "ICN"}
        };
        expect = new String[]{"ICN", "ATL", "YVR", "LAX", "ICN", "SFO", "ATL", "BNE"};
        result = solution(tickets);
        test.test(result, expect);

        tickets = new String[][]{
                {"ICN", "SFO"},
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"SFO", "ICN"},
                {"ATL", "YVR"},
                {"ATL", "BNE"},
                {"YVR", "LAX"},
                {"LAX", "ICN"}
        };
        expect = new String[]{"ICN", "ATL", "YVR", "LAX", "ICN", "SFO", "ICN", "SFO", "ATL", "BNE"};
        result = solution(tickets);
        test.test(result, expect);
    }
    public String[] solution(String[][] ticketsArray) {
        HashMap<String, ArrayList<String>> tickets = parseTicketsArrayToMap(ticketsArray);

        ArrayList<String> path = new ArrayList<>();
        String cur = "ICN";
        path.add(cur);
        ArrayList<String> list = traverseAllTicket(tickets, path, cur);
        String[] answer = list.toArray(new String[list.size()]);
        return answer;
    }

    private ArrayList<String> traverseAllTicket(HashMap<String, ArrayList<String>> tickets, ArrayList<String> path, String cur) {
        ArrayList<String> destinations = tickets.get(cur);
        if(destinations == null || destinations.size() == 0){
            if(!includeRemainTicket(tickets)){
                return path;
            }else{
                return null;
            }
        }else{
            for(String destination : destinations){
                ArrayList<String> removed = remove(destinations, destination);

                HashMap<String, ArrayList<String>> consumedTickets = new HashMap(tickets);
                consumedTickets.put(cur, removed);

                ArrayList<String> addedPath = new ArrayList(path);
                addedPath.add(destination);

                ArrayList<String> result = traverseAllTicket(consumedTickets, addedPath, destination);
                if(result != null){
                    return result;
                }
            }
        }

        return null;
    }

    private boolean includeRemainTicket(HashMap<String, ArrayList<String>> tickets) {
        Set<String> keys = tickets.keySet();

        for(String key : keys){
            if(tickets.get(key).size() > 0){
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> remove(ArrayList<String> destinations, String destination){
        int length = destination.length();
        ArrayList<String> list = new ArrayList(destinations);

        for(int i = 0; i < length; i++){
            if(list.get(i).equals(destination)){
                list.remove(i);
                break;
            }
        }

        return list;
    }

    private HashMap<String, ArrayList<String>> parseTicketsArrayToMap(String[][] tickets){
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for(String[] ticket : tickets){
            String depart = ticket[0];
            String destination = ticket[1];
            ArrayList<String> destinations = map.get(depart) == null ? new ArrayList<>() : map.get(depart);
            destinations.add(destination);
            map.put(depart, destinations);
        }

        map = sortValueOfMap(map);

        return map;
    }

    private HashMap<String, ArrayList<String>> sortValueOfMap(HashMap<String, ArrayList<String>> map) {
        Set<String> keys = map.keySet();

        for(String key : keys){
            ArrayList<String> value = map.get(key);
            Collections.sort(value);
            map.put(key, value);
        }

        return map;
    }
}