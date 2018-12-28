#include <cstdlib>
#include <set>
#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;

    multiset<pair<double, bool>> cars;

    for (int i = 0; i < n; ++i) {
        double pos, len;
        cin >> pos >> len;

        cars.insert({pos - 0.5 * len, false});
        cars.insert({pos + 0.5 * len, true});
    }

    int curr = 315;
    int result = curr;

    for (const pair<double, bool> car: cars) {
        if (car.second) {
            // tail of the car

            curr += 1;
        } else {
            // head of the car

            curr -= 1;
        }

        result = min(result, curr);
    }

    cout << result << endl;

    return 0;
}
