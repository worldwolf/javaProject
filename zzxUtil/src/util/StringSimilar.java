package com.fid.util;

import java.util.Arrays;

public class StringSimilar {
	public static void main(String[] args) {
		System.out.println(getStringSimilar("保监会：原则同意中国人寿保险股份有限公司以受让方式增持广发银行股份至43.686%。","财联社4日讯，保监会表示，原则同意中国人寿保险股份有限公司以受让方式增持广发银行股份至43.686%。"));
		System.out.println(getStringSimilar("财联社4日讯，保监会表示，原则同意中国人寿保险股份有限公司以受让方式增持广发银行股份至43.686%。", "【快讯】财联社4日讯，临近午盘，金融股集体拉升，华泰证券走强涨逾8%，光大证券、国金证券、宁波银行涨4%，新华保险、中国人寿涨逾2%。"));
		System.out.println(getStringSimilar("【快讯】财联社4日讯，煤炭股盘中快速走强，兖州煤业封涨停，陕西煤业、潞安环能、盘江股份、昊华能源均涨逾5%。", ""));
		/*double d[][];
		d = new double[6][6];
		for (int i = 0; i <= 5; i++) {
			d[i][0] = i;
		}
		for (int j = 0; j <= 5; j++) {
			d[0][j] = j;
		}
		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d.length; j++) {
				System.out.print(d[i][j]+"  ");
			}
			System.out.println();
		}*/
		
	}

	// 编辑距离求串相似度
	public static double getStringSimilar(String s1, String s2) {
		double d[][];// matrix
		int n;// lengthofs

		int m;// lengthoft

		int i;// iteratesthroughs

		int j;// iteratesthrought

		char s_i;// ithcharacterofs

		char t_j;// jthcharacteroft

		double cost;// cost
		// Step1
		n = s1.length();
		m = s2.length();
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new double[n + 1][m + 1];
		// Step2
		for (i = 0; i <= n; i++) {
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) {
			d[0][j] = j;
		}
		// Step3
		for (i = 1; i <= n; i++) {
			s_i = s1.charAt(i - 1);
			// Step4
			for (j = 1; j <= m; j++) {
				t_j = s2.charAt(j - 1);
				// Step5
				if (s_i == t_j) {
					cost = 0;
				} else {
					cost = 1;
				}
				// Step6
				d[i][j] = Minimum(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
			}
		}
		// Step7
		return 1/d[n][m];
	}

	// 求最小值
	private static double Minimum(double a, double b, double c) {
		// TODOAuto-generatedmethodstub
		double mi;
		mi = a;
		if (b < mi) {
			mi = b;
		}
		if (c < mi) {
			mi = c;
		}
		return mi;
	}
}
