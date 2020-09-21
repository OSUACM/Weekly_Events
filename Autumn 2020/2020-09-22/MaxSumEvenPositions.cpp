# include <bits/stdc++.h>
// https://codeforces.com/problemset/problem/1373/D
using namespace std;
typedef long long ll;

ll bestSum(vector<int> &arr) {
    vector<ll> best(arr.size() + 1, 0);
    vector<ll> bestEnding(arr.size() + 1, 0);
    for (int i = 0; i < arr.size(); i++) {
        bestEnding[i + 1] = max(bestEnding[i] + arr[i], (ll) arr[i]);
        best[i + 1] = max(best[i], arr[i] + max(0ll, bestEnding[i]));
    }
    return best[arr.size()];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int t;
    cin >> t;
    for (int tt = 0; tt < t; tt++) {
        int n;
        cin >> n;
        vector<int> a;
        vector<int> scoreEven;
        vector<int> scoreOdd;
        for (int i = 0; i < n; i++) {
            int x;
            cin >> x;
            a.push_back(x);
        }
        a.push_back(a[n - 1]);
        a.push_back(a[n - 1]);
        ll baseScore = 0;
        for (int i = 0; i < n; i += 2) {
            baseScore += a[i];
            scoreEven.push_back(a[i + 1] - a[i]);
            scoreOdd.push_back(a[i + 1] - a[i + 2]);
        }
        cout << baseScore + max(bestSum(scoreEven), bestSum(scoreOdd)) << "\n";
    }

    return 0;
}