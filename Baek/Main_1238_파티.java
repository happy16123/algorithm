package baek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1238_파티 {

	public static int N;
	public static List<List<Edge>> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		list = new ArrayList<List<Edge>>();

		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list.get(v1).add(new Edge(v2, w));
		}

		int max = 0;
		for (int i = 1; i < N + 1; i++) {
			if(X != i) {
				int go = dijkstra(i, X);
				int back = dijkstra(X, i);
//				System.out.println(i + "에서 출발 : " + go + " " + back);
				max = go + back > max ? go + back : max;
			}
		}
		
		System.out.println(max);
	}

	public static int dijkstra(int start, int end) {
		int[] D = new int[N + 1];
		Arrays.fill(D, Integer.MAX_VALUE);
		Queue<Edge> pq = new PriorityQueue<Edge>();
		boolean[] visited = new boolean[N + 1];

		D[start] = 0;
		pq.offer(new Edge(start, 0));
		while (!pq.isEmpty()) {
			int cur = pq.poll().v;
			if (!visited[cur]) {
				visited[cur] = true;

				for (Edge next : list.get(cur)) {
					if (D[next.v] > D[cur] + next.w) {
						D[next.v] = D[cur] + next.w;
						pq.offer(new Edge(next.v, D[next.v]));
					}
				}
			}
		}
		return D[end];
	}

	static class Edge implements Comparable<Edge> {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(w, o.w);

		}
	}

}
