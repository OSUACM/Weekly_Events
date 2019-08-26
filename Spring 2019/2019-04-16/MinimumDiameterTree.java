package codeforces;

import java.io.*;

//https://codeforces.com/contest/1086/problem/B
public class MinimumDiameterTree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int n = Integer.parseInt(params[0]);
        int s = Integer.parseInt(params[1]);
        Node[] a = undirectedGraphFromEdgeList(br, n, n - 1);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].n_edge == 1) {
                count++;
            }
        }
        if (n == 2) {
            System.out.println(s);
        } else {
            System.out.println((2.0 * s) / count);
        }
        br.close();
    }

    private static class Node {
        // 0-indexed index of the node in the Node[] returned from undirectedGraphFromEdgeList
        int ind;
        // number of edges.
        int n_edge;
        // edges of the node
        Node[] edges;
    }

    /**
     * read an undirected graph in from br, from a list of it's edges.
     * Store the results as nodes with Node[] edges.
     * (Assumes edges from input are 1-indexed)
     */
    private static Node[] undirectedGraphFromEdgeList(BufferedReader br, int n, int m) throws IOException {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].ind = i;
            nodes[i].n_edge = 0;
        }
        int[] counts = new int[n];
        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int a = Integer.parseInt(edge[0]) - 1;
            int b = Integer.parseInt(edge[1]) - 1;
            edges[i][0] = a;
            edges[i][1] = b;
            counts[a]++;
            counts[b]++;
        }
        for (int i = 0; i < n; i++) {
            nodes[i].edges = new Node[counts[i]];
        }
        for (int i = 0; i < edges.length; i++) {
            Node a = nodes[edges[i][0]];
            Node b = nodes[edges[i][1]];
            a.edges[a.n_edge++] = b;
            b.edges[b.n_edge++] = a;
        }
        return nodes;
    }
}
