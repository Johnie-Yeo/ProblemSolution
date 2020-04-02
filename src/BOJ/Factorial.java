package BOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factorial {
public static void main(String[] args) {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int N = 0;
	try {
		N = Integer.parseInt(br.readLine());
	} catch (NumberFormatException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	StringBuilder sb = new StringBuilder();
	long mod = 1000000000L;
	long result = 1;
	for(int i = 1; i<= N; i++) {
		result *= i;
		result %= mod;
		while(result % 10 == 0)
			result /= 10;
	}
	sb.append(Long.toString(result%100000));
	while(sb.length() < 5)
		sb.insert(0, 0);
	System.out.println(sb);
}
}
