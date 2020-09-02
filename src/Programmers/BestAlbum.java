package Programmers;

import Test.OldTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class BestAlbum {
    public static void main(String[] args) {
        new BestAlbum().test();
    }

    private void test() {
        OldTest test = new OldTest();

        String[] genres;
        int[] plays, result, expect;

        genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
        plays  = new int[]{500, 600, 150, 800, 2500};
        expect = new int[] {4, 1, 3, 0};
        result = solution(genres, plays);
        test.test(result, expect).printResult();

        genres = new String[]{"classic", "pop", "classic", "classic", "pop", "rock"};
        plays  = new int[]{500, 600, 150, 800, 2500, 5000};
        expect = new int[] {5, 4, 1, 3, 0};
        result = solution(genres, plays);
        test.test(result, expect).printResult();
    }

    private class Album implements Comparable<Album>{
        int index;
        int play;
        String genre;
        public Album(int index, String genre, int play){
            this.index = index;
            this.genre = genre;
            this.play = play;
        }

        @Override
        public int compareTo(Album o) {
            return this.play - o.play;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Album> albums = getAlbums(genres, plays);
        HashMap<String, Integer> playTimeGroupByGenre = getPlayTimeGroupByGenre(genres, plays);
        ArrayList<Integer> result = new ArrayList<>();

        while(!playTimeGroupByGenre.isEmpty()){
            String highest = getHighestTime(playTimeGroupByGenre);
            playTimeGroupByGenre.remove(highest);
            ArrayList<Integer> cur = getHighestTwo(highest, albums);
            result.addAll(cur);
        }

        return result.stream().mapToInt(e->e).toArray();
    }

    private ArrayList<Integer> getHighestTwo(String target, ArrayList<Album> albums) {
        ArrayList<Album> filtered = filterBy(target, albums);
        int size = filtered.size();
        ArrayList<Integer> result = new ArrayList<>();
        if(size == 1){
            result.add(filtered.get(0).index);
        }else{
            int maxIndex = getMaxPlayIndex(filtered);
            result.add(filtered.get(maxIndex).index);
            filtered.remove(maxIndex);
            maxIndex = getMaxPlayIndex(filtered);
            result.add(filtered.get(maxIndex).index);
            filtered.remove(maxIndex);
        }
        return result;
    }

    private int getMaxPlayIndex(ArrayList<Album> list) {
        int maxPlay = list.get(0).play;
        int maxIndex = 0;
        int size = list.size();

        for(int i = 1; i < size; i++){
            if(list.get(i).play > maxPlay){
                maxIndex = i;
                maxPlay = list.get(i).play;
            }
        }

        return maxIndex;
    }

    private ArrayList<Album> filterBy(String genre, ArrayList<Album> albums){
        ArrayList<Album> result = albums.stream()
                                        .filter(e -> e.genre.equals(genre))
                                        .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

    private ArrayList<Album> getAlbums(String[] genres, int[] plays) {
        ArrayList<Album> result = new ArrayList<>();

        int length = genres.length;
        for(int i = 0; i < length; i++){
            Album album = new Album(i, genres[i], plays[i]);
            result.add(album);
        }

        return result;
    }

    private String getHighestTime(HashMap<String, Integer> map) {
        int max = -1;
        String result = "";

        for(String key : map.keySet()){
            if(map.get(key) > max){
                max = map.get(key);
                result = key;
            }
        }

        return result;
    }

    private HashMap<String,Integer> getPlayTimeGroupByGenre(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();

        int length = genres.length;
        for(int i = 0; i < length; i++){
            int time = map.get(genres[i]) == null ? 0 : map.get(genres[i]);
            map.put(genres[i], plays[i] + time);
        }

        return map;
    }

}
