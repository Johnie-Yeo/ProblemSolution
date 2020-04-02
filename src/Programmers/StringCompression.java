package Programmers;

public class StringCompression {

	public static void main(String[] args) {
		new StringCompression().solve();
	}
	public void solve() {
		String[] data = {
				"aabbaccc", "ababcdcdababcdcd","abcabcdede",
				"abcabcabcabcdededededede","xababcdcdababcdcd"		
		};
		int[] result = {7,9,8,14,17};
		
		for(int i = 0; i < data.length; i++) {
			int solved = solution(data[i]);
			if(solved == result[i]) {
				System.out.println("pass");
			}else {
				System.out.println("fail");
			}
		}
	}
	public int solution(String data) {
		int length = data.length();
		int result = length;
		for(int size = length/2; size > 0; size--) {
			String compressedData = compress(data, size);
			if(compressedData.length() < result) {
				result = compressedData.length();
			}
		}
		return result;
	}
	public String compress(String data, int size) {
		String result = "";
		int index = 0;
		int count = 1;
		String start = data.substring(size*index, size*(++index));
		while(true) {
			try {
				String cur = data.substring(size*index, size*(++index));	
				if(start.equals(cur)) {
					count++;
				}else {
					if(count > 1) {
						result += (count+start);
						count = 1;
					}else {
						result += start;
					}
					start = cur;
				}
			}catch(Exception e) {
				if(count > 1) {
					result += count;
				}
				result += data.substring(size*(index-2));
				break;
			}
		}
		
		return result;
	}
}
