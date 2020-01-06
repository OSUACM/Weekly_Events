#include "stdio.h"

long factor_max[10000000];
long factor_remain[10000000];
long non_coprime_count[10000000];

long gcd(long y, long x) {
    while (x) {
        y %= x;
        y ^= x ^= y ^= x;
    }

    return y;
}

int main() {
    long lo;
    long hi;

    scanf("%ld%ld", &lo, &hi);

    factor_max[1] = 1;
    factor_remain[1] = 1;
    non_coprime_count[1] = 0;

    for (long i = 2; i <= hi; ++i) {
        if (factor_max[i]) continue;

        for (long j = i; j <= hi; j *= i) {
            for (long k = 1; k <= hi / j; ++k) {
                if (!factor_max[k]) continue;
                if (factor_max[k] == i) continue;

                factor_max[k * j] = i;
                factor_remain[k * j] = factor_remain[k] * factor_max[k];
            }
        }

        for (long j = i; j <= hi; j += i) {
            if (factor_remain[j] * factor_max[j] != j) continue;

            // when i < 6469693230, factor_tot < 10
            long factor_tot = 0;
            long factor[10];
            long mul[1 << 10];
            long sign[1 << 10];

            for (long k = j; k != 1; k = factor_remain[k]) {
                factor[factor_tot++] = factor_max[k];
            }

            mul[0] = 1;
            sign[0] = -1;

            for (long k = 0; k < factor_tot; ++k) {
                for (long l = 0; l < 1 << k; ++l) {
                    mul[(1 << k) | l] = mul[l] * factor[k];
                    sign[(1 << k) | l] = -sign[l];

                    non_coprime_count[j] += (
                        hi / mul[(1 << k) | l] - (lo - 1) / mul[(1 << k) | l]
                    ) * sign[(1 << k) | l];
                }
            }
        }
    }

    long result = 0;

    for (long i = lo; i <= hi; ++i) {
        result += hi - (lo - 1) - non_coprime_count[factor_remain[i] * factor_max[i]];

        // test

        // long n = 0;

        // for (long j = lo; j <= hi; ++j) {
        //     n += gcd(i, j) != 1;
        // }

        // if (n != non_coprime_count[factor_remain[i] * factor_max[i]]) {
        //     printf("Error: %ld, %ld %ld\n", i, n, non_coprime_count[factor_remain[i] * factor_max[i]]);
        // }
    }

    printf("%ld\n", result);

    return 0;
}
