package Test;

import java.util.*;

public class OldestTest {
	public final String ANSI_RESET = "\u001B[0m";	
	public final String ANSI_RED = "\u001B[31m";

	public void test(int result, int expect){
		if(result == expect){
			System.out.println("==========Pass==========");
		}else{
			System.out.println("Expect " + expect);
			System.out.println("But " + result + " returned");
			alert("==========Fail==========");
		}
	}

	public void test(double result, double expect){
		if(result == expect){
			System.out.println("==========Pass==========");
		}else{
			System.out.println("Expect " + expect);
			System.out.println("But " + result + " returned");
			alert("==========Fail==========");
		}
	}

	public void test(boolean result, boolean expect){
		if(result == expect){
			System.out.println("==========Pass==========");
		}else{
			System.out.println("Expect " + expect);
			System.out.println("But " + result + " returned");
			alert("==========Fail==========");
		}
	}
	public void test(String result, String expect){
		if(result.equals(expect)){
			System.out.println("==========Pass==========");
		}else{
			System.out.println("Expect " + expect);
			System.out.println("But " + result + " returned");
			alert("==========Fail==========");
		}
	}

	public void test(int[] result, int[] expect) {
		if(equals(result, expect)) {
			System.out.println("==========Pass==========");
		}else {
			System.out.println("Expect " + toString(expect));
			System.out.println("But " + toString(result) + " returned");
			alert("==========Fail==========");
		}
	}
	public void test(long[] result, long[] expect) {
		if(equals(result, expect)) {
			System.out.println("==========Pass==========");
		}else {
			System.out.println("Expect " + toString(expect));
			System.out.println("But " + toString(result) + " returned");
			alert("==========Fail==========");
		}
	}
	public void test(String[] result, String[] expect) {
		if(equals(result, expect)) {
			System.out.println("==========Pass==========");
		}else {
			System.out.println("Expect " + toString(expect));
			System.out.println("But    " + toString(result) + " returned");
			alert("==========Fail==========");
		}
	}
	public void test(int[][] result, int[][] expect) {
		if(equals(result, expect)) {
			System.out.println("==========Pass==========");
		}else {
			System.out.println("Expect " + toString(expect));
			System.out.println("But " + toString(result) + " returned");
			alert("==========Fail==========");
		}
	}
	public void test(String[][] result, String[][] expect) {
		if(equals(result, expect)) {
			System.out.println("==========Pass==========");
		}else {
			System.out.println("Expect " + toString(expect));
			System.out.println("But " + toString(result) + " returned");
			alert("==========Fail==========");
		}
	}

	private <T> boolean equals(List<T> a, List<T> b){
		int size = a.size();
		if(size != b.size()){
			return false;
		}
		for(int index = 0; index < size; index++){
			if(a.get(index) != b.get(index)) {
				return false;
			}
		}
		return true;
	}
	private boolean equals(int[] a, int[] b) {
		int length = a.length;
		if(length != b.length) {
			return false;
		}

		for(int index = 0; index < length; index++) {
			if(a[index] != b[index]) {
				return false;
			}
		}
		return true;
	}
	private boolean equals(long[] a, long[] b) {
		int length = a.length;
		if(length != b.length) {
			return false;
		}

		for(int index = 0; index < length; index++) {
			if(a[index] != b[index]) {
				return false;
			}
		}
		return true;
	}
	private boolean equals(String[] a, String[] b) {
		int length = a.length;
		if(length != b.length) {
			return false;
		}

		for(int index = 0; index < length; index++) {
			if(!a[index].equals(b[index])) {
				return false;
			}
		}
		return true;
	}
	private boolean equals(int[][] a, int[][] b) {
		int length = a.length;
		if(length != b.length) {
			return false;
		}

		for(int index = 0; index < length; index++) {
			if(!equals(a[index], b[index])) {
				return false;
			}
		}
		return true;
	}
	private boolean equals(String[][] a, String[][] b) {
		int length = a.length;
		if(length != b.length) {
			return false;
		}

		for(int index = 0; index < length; index++) {
			if(!equals(a[index], b[index])) {
				return false;
			}
		}
		return true;
	}

	private <T> String toString(List<T> list){
		StringBuilder sb = new StringBuilder();
		int size = list.size();
		sb.append("[ ");
		for(T element : list){
			sb.append(element);
			sb.append(", ");
		}
		sb.replace(sb.length()-2, sb.length(), " ]");
		return sb.toString();
	}
	private String toString(int[] arr) {
		StringBuilder sb = new StringBuilder();
		int size = arr.length;
		sb.append("[ ");
		for(int index = 0; index < size-1; index++) {
			sb.append(arr[index]);
			sb.append(", ");
		}
		sb.append(arr[size-1]);
		sb.append(" ]");
		return sb.toString();
	}
	private String toString(long[] arr) {
		StringBuilder sb = new StringBuilder();
		int size = arr.length;
		sb.append("[ ");
		for(int index = 0; index < size-1; index++) {
			sb.append(arr[index]);
			sb.append(", ");
		}
		sb.append(arr[size-1]);
		sb.append(" ]");
		return sb.toString();
	}
	private String toString(String[] arr) {
		StringBuilder sb = new StringBuilder();
		int size = arr.length;
		sb.append("[ ");
		for(int index = 0; index < size-1; index++) {
			sb.append(arr[index]);
			sb.append(", ");
		}
		sb.append(arr[size-1]);
		sb.append(" ]");
		return sb.toString();
	}
	private String toString(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		int size = arr.length;
		sb.append("[");
		for(int index = 0; index < size-1; index++) {
			sb.append(toString(arr[index]));
			sb.append(", ");
		}
		sb.append(toString(arr[size-1]));
		sb.append("]");
		return sb.toString();
	}
	private String toString(String[][] arr) {
		StringBuilder sb = new StringBuilder();
		int size = arr.length;
		sb.append("[");
		for(int index = 0; index < size-1; index++) {
			sb.append(toString(arr[index]));
			sb.append(", ");
		}
		sb.append(toString(arr[size-1]));
		sb.append("]");
		return sb.toString();
	}

	public void alert(String alert) {
		System.out.println(alert);
	}
}
