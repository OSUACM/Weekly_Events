#include <stdio.h>

const long mod = 1000000007;

//       aa = 0
//     ba  ca = 1, 1
//   bc      cb = 2, 2
// cc  ac  ab  bb = 4, 3, 3, 4

long trans[64][5][5] = {{
    {0, 3, 0, 0, 0},
    {1, 1, 1, 0, 0},
    {0, 1, 0, 1, 1},
    {0, 0, 1, 1, 1},
    {0, 0, 0, 0, 3},
}};

long p[64][5] = {{
    1, 0, 0, 0, 0,
}};

int main() {
    long n;
    scanf("%ld", &n);

    for (int i = 0; i < 63; ++i) {
        for (int j = 0; j < 5; ++j) {
            for (int k = 0; k < 5; ++k) {
                for (int l = 0; l < 5; ++l) {
                    trans[i + 1][j][l] += trans[i][j][k] * trans[i][k][l];
                }
            }
        }

        for (int j = 0; j < 5; ++j) {
            for (int k = 0; k < 5; ++k) {
                trans[i + 1][j][k] %= mod;
            }
        }
    }

    for (int i = 0; i < 63; ++i) {
        if (n & (1l << i)) {
            for (int j = 0; j < 5; ++j) {
                for (int k = 0; k < 5; ++k) {
                    p[i + 1][k] += p[i][j] * trans[i][j][k];
                }
            }

            for (int j = 0; j < 5; ++j) {
                p[i + 1][j] %= mod;
            }
        } else {
            for (int j = 0; j < 5; ++j) {
                p[i + 1][j] = p[i][j];
            }
        }
    }

    printf("%ld\n", p[63][4]);

    return 0;
}
