#include <cstdlib>
#include <cmath>
#include <iostream>

using namespace std;

const int LARGE_INT = 1 << 24;

int main() {
    int n;
    cin >> n;

    bool reached[n];
    int nearest[n];
    int distance[n][n];

    for (int i = 0; i < n; ++i) {
        int tmp;
        cin >> tmp; // ignore it

        reached[i] = false;
        nearest[i] = LARGE_INT;
        for (int j = 0; j < n; ++j) {
            distance[i][j] = LARGE_INT;
        }
    }

    int m;
    cin >> m;

    for (int i = 0; i < m; ++i) { // create an adjacency matrix
        int from, to, length;
        cin >> from >> to >> length;

        distance[from - 1][to - 1] = length;
        distance[to - 1][from - 1] = length;
    }

    // prim's algorithm

    int last = 0;
    int total = 0;
    for (int i = 1; i < n; ++i) { // find n - 1 edges
        reached[last] = true;

        int best = 0; // nearest[0] is always LARGE_INT

        for (int j = 0; j < n; ++j) {
            if (!reached[j]) {
                nearest[j] = min(nearest[j], distance[last][j]);

                if (nearest[best] > nearest[j]) {
                    best = j;
                }
            }
        }

        last = best;
        total += nearest[best];
    }

    if (total >= LARGE_INT) {
        cout << "impossible" << endl;
    } else {
        cout << total << endl;
    }

    return 0;
}
