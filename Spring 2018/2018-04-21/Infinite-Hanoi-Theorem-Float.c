#include <stdio.h>

//       aa = 0
//     ba  ca = 1, 1
//   bc      cb = 2, 2
// cc  ac  ab  bb = 4, 3, 3, 4

const double k = 1. / 3;

const double trans[5][5] = {
    {0, 1, 0, 0, 0},
    {k, k, k, 0, 0},
    {0, k, 0, k, k},
    {0, 0, k, k, k},
    {0, 0, 0, 0, 1},
};

int main() {
    double p[5] = {1, 0, 0, 0, 0};

    long n;
    scanf("%ld", &n);

    for (long i = 0; i < n; ++i) {
        double p2[5] = {0, 0, 0, 0, 0};

        for (int j = 0; j < 5; ++j) {
            for (int k = 0; k < 5; ++k) {
                p2[k] += trans[j][k] * p[j];
            }
        }

        for (int j = 0; j < 5; ++j) {
            p[j] = p2[j];
        }
    }

    printf("%f\n", p[4]);

    return 0;
}
