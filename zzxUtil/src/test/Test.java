package test;

public class Test {
	public void ss(String... cc){
		System.out.println(cc[0].equals("c"));
	}
	public static void main(String[] args) {
		new Test().ss("c");
	}
}
