// Count all possibles solutions for N queens puzzle

#include <iostream>
#include <ctime>

using namespace std;

uint64_t COUNT = 0;

void search(uint64_t N, uint64_t col, uint64_t R, uint64_t D1, uint64_t D2) {
    uint64_t rows = ~(R | D1 | D2) & ((1 << N) - 1); // all available rows
    while (rows) {
        uint64_t row = rows & -rows;
        rows ^= row;
        if (col == N - 1)
            COUNT++;
        else
            search(N, col + 1, R | row, (D1 | row) >> 1, (D2 | row) << 1);
    }
}

int main() {
    cout.precision(10);

    cout << "Input size of chessboard: ";

    uint64_t N;
    cin >> N;

    if (N < 32) {
        clock_t begin = clock();
        search(N, 0, 0, 0, 0);
        clock_t end = clock();
        cout << endl << COUNT << " solutions found (" << (double)(end - begin) * 1000 / CLOCKS_PER_SEC << " ms)" << endl;
    } else {
        cout << "N should less than 32" << endl;
    }

    return 0;
}
