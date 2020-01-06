#include <stdlib.h>
#include <stdio.h>

int comp(const void *a, const void *b) {
    if (*(int *) a < *(int *) b) return -1;
    if (*(int *) a > *(int *) b) return 1;
    return 0;
}

int main() {
    int n;
    scanf("%d", &n);

    int r[n];
    int g[n];
    int b[n];
    for (int i = 0; i < n; ++i) {
        scanf("%d%d%d", &r[i], &g[i], &b[i]);
    }

    int v[n];

    for (int i = 0; i < n; ++i) {
        v[i] = r[i] * 1000000 + g[i] * 1000 + b[i];
    }

    qsort(v, n, sizeof(int), comp);

    int count = 0;
    int pl = 0;
    int pr = n - 1;
    while (pl < pr) {
        if (v[pl] + v[pr] < 255255255) {
            pl += 1;
        } else if (v[pl] + v[pr] > 255255255) {
            pr -= 1;
        } else {
            int vl = v[pl];
            int vr = v[pr];
            int count_l = 0;
            int count_r = 0;

            while (v[pl] == vl) {
                count_l += 1;
                pl += 1;
            }

            while (v[pr] == vr) {
                count_r += 1;
                pr -= 1;
            }

            count += count_l * count_r;
        }
    }

    printf("%d\n", count);

    return 0;
}
