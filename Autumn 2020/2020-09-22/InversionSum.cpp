# include <bits/stdc++.h>
// A pretty cool dp problem
// https://atcoder.jp/contests/agc030/tasks/agc030_d
using namespace std;
const int MOD = 1'000'000'007;
const long long HALF = 500'000'004LL;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, q;
    cin >> n >> q;
    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    // pInverted[i][j] = The probability that a_i < a_j.
    vector<vector<int>> pLess(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            pLess[i][j] = a[i] < a[j];
        }
    }
    long long ways = 1;
    for (int i = 0; i < q; i++) {
        ways = (ways * 2) % MOD;
        int x, y;
        cin >> x >> y;
        x--;
        y--;
        for (int j = 0; j < n; j++) {
            if (j != x && j != y) {
                pLess[j][y] = pLess[j][x] =
                        ((pLess[j][y] + pLess[j][x]) * HALF) % MOD;
                pLess[x][j] = pLess[y][j] =
                        ((pLess[x][j] + pLess[y][j]) * HALF) % MOD;
            }
        }
        pLess[x][y] = pLess[y][x] =
                ((pLess[x][y] + pLess[y][x]) * HALF) % MOD;
    }
    long long expectedWays = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            // In this loop, j < i. Now we add if a[i] < a[j]
            expectedWays += pLess[i][j];
        }
    }
    expectedWays = expectedWays % MOD;
    int totalWays = (expectedWays * ways) % MOD;
    cout << totalWays << "\n";
    return 0;
}