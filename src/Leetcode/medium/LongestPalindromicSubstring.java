package Leetcode.medium;

import Test.Test;

import java.util.*;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        new LongestPalindromicSubstring().test();
    }

    private void test() {
        String input;
        String expect;
        String result;

        input = "babad";
        expect = "bab";
        result = longestPalindrome(input);
        Test.test(result, expect).printResult();

        input  = "cbbd";
        expect = "bb";
        result = longestPalindrome(input);
        Test.test(result, expect).printResult();

        input  = "a";
        expect ="a";
        result = longestPalindrome(input);
        Test.test(result, expect).printResult();

        input  = "ac";
        expect = "a";
        result = longestPalindrome(input);
        Test.test(result, expect).printResult();

        input  = "aa";
        expect = "aa";
        result = longestPalindrome(input);
        Test.test(result, expect).printResult();

        input  = "xdxtfdaarvvznrptwicdldmrmwbdrmyppvamdvofujthfcmkcugvodmlvubgvodectwzparprifwgwfvddlrfdnrpspirtyvxqvbqiglugbmzoyzcfkvbjdrdqqxxzutebgoacczuhopvzjfrnfsylgfgkbmbjqqyggtdjcvjbvpspkjcezanajjzabfidndfdpkuamwvxrbpxzoivlnkwdxedtfnmvicmzebwktpktokibeycbpqzejddwnvimmbzupyxwmrgdbmcujadfexcchdkfvkxsdwkuwuxzhpnjgmqbmidcwywjgcsbydixyxcclcbrzjvrmlrzgmbviifllouykovscaufvxovwmmgubshtoizbwtcpqzwchtkmkjfneuybfglywfrorhmfdgvjdsmegtoytsivnuaceszpfsxgddbweckgziahkslykgdkztmpapnoyawqtyrdcuzaxcohohapektyfbfhrsdnjbgjvwvqpcikdnlkdogsinkfpymkkdburnbksnqfjgjlacqpfqlhsjhhoccdkrjipqwzsxmpjughaqchzlrqkogkryqkuuxhzchovebzgeekuflcgvxugnxcvugqlstmnljlvxonkybmzjmnsvvwfztcplgikptnppbzeygbmdsyimsntveojwsejmastiovbctdkdlfvpyzihhxishtveflnmamlnzqroxknrrkkfpveyzvvasdznykygrpbfkbinrrvheekeumlvlgalqelspvpiydqkwduckimyhpzsxlcpkbvgwmwnasdxuupdhcmxjoushcvcnjyrmuemuydyywpvzhkxsqszaqhnbhjwsokkpployomoawtr";
        expect = "fwgwf";
        result = longestPalindrome(input);
        Test.test(result, expect).printResult();
    }

    public String longestPalindrome(String s) {
        String longestPalindrome = "";
        for(int i = 0; i < s.length(); i++) {
            String current = s.substring(i);
            String palindrome = getLongestPalindromeString(current);

            if(longestPalindrome.length() < palindrome.length()) {
                longestPalindrome = palindrome;
            }
            if(longestPalindrome.length() >= s.length() - i + 1) {
                break;
            }
        }
        return longestPalindrome;
    }

    private String getLongestPalindromeString(String s) {
        List<Integer> list = getFirstCharIndexList(s);

        for(int index : list) {
            String current = s.substring(0, index+1);
            if(isPalindromic(current)) {
                return current;
            }
        }

        return "";
    }

    private List<Integer> getFirstCharIndexList(String s) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(0) == s.charAt(i)) {
                list.add(i);
            }
        }
        Collections.reverse(list);
        return list;
    }

    private boolean isPalindromic(String target) {
        for(int i = 0; i <= target.length()/2; i++) {
            if(target.charAt(i) != target.charAt(target.length()-i-1)) {
                return false;
            }
        }
        return true;
    }
}
