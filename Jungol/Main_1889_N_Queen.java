package jungol.bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1889_N_Queen {

	public static int[] col;
	public static int N;
	public static int ans = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new int[N];
		dfs(0);
		System.out.println(ans);
	}

	public static void dfs(int cnt) {

		if (cnt == N) {
			ans++;
			return;
		} else {
			for (int i = 0; i < N; i++) {
				col[cnt] = i;
				if (isPossible(cnt)) {
					dfs(cnt + 1);
				}
			}
		}
	}

	public static boolean isPossible(int cnt) {
		for (int i = 0; i < cnt; i++) {
			if (col[i] == col[cnt] || Math.abs(cnt - i) == Math.abs(col[cnt] - col[i])) {
				return false;
			}
		}
		return true;
	}
}
