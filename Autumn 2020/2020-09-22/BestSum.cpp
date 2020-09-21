// Compute the best subarray sum
ll bestSum(vector<int> &arr) {
    vector<ll> best(arr.size() + 1, 0);
    vector<ll> bestEnding(arr.size() + 1, 0);
    for (int i = 0; i < arr.size(); i++) {
        bestEnding[i + 1] = max(bestEnding[i] + arr[i], (ll) arr[i]);
        best[i + 1] = max(best[i], arr[i] + max(0ll, bestEnding[i]));
    }
    return best[arr.size()];
}

