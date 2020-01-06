#include <stdbool.h>
#include <stdio.h>
#include <math.h>

const long comp_limit = 100000000;
const int comp_len = 8;

int main() {
    long target = 0;

    for (int i = 0; i < comp_len; ++i) {
        long c = getchar();

        if (c >= '0' && c <= '9') {
            target = target * 10 + (c - '0');
        } else {
            break;
        }
    }

    // f0 = 0, f1 = 1, f2 = 1

    long double curr = 0;
    long double curr2 = 1;

    for (long i = 2; true; ++i) {
        long double tmp = curr + curr2;

        curr = curr2;
        curr2 = tmp;

        while (curr2 >= comp_limit) {
            curr /= 10;
            curr2 /= 10;
        }

        if (((long) curr2) == target) {
            printf("%ld\n", i);

            break;
        }
    }

    return 0;
}
