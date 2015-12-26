package database;

import java.io.IOException;
import java.util.Scanner;

public class ComputerKey {
	public static void main(String[] args) throws IOException {
		Process process = Runtime.getRuntime().exec(
		        new String[] { "wmic", "cpu", "get", "ProcessorId" });
		process.getOutputStream().close();
		Scanner sc = new Scanner(process.getInputStream());
		String property = sc.next();
		String serial = sc.next();
		System.out.println(property + ": " + serial);
		System.out.println(Long.MAX_VALUE);
	}
}
