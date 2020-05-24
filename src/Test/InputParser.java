package Test;

import java.util.Arrays;

public class InputParser {
    public static int[][] parseStringTo2DIntArray(String input){
        final String DELIM = "\n";
        String[] parseByNewLine = input.split(DELIM);
        int[][] result = Arrays.stream(parseByNewLine)
                            .map(e -> parseStringToIntArray(e))
                            .toArray(int[][]::new);
        return result;
    }
    public static int[] parseStringToIntArray(String input){
        final String DELIM = " ";
        String[] parseBySpace = input.split(DELIM);
        int[] result = Arrays.stream(parseBySpace)
                            .mapToInt(e -> Integer.parseInt(e))
                            .toArray();
        return result;
    }
}
