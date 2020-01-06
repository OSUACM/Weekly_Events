#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);

    int r[n];
    int g[n];
    int b[n];
    for (int i = 0; i < n; ++i) {
        scanf("%d%d%d", &r[i], &g[i], &b[i]);
    }

    static int buckets[256][256][256];

    for (int i = 0; i < n; ++i) {
        buckets[r[i]][g[i]][b[i]] += 1;
    }

    int count = 0;
    for (int i = 0; i < n; ++i) {
        count += buckets[255 - r[i]][255 - g[i]][255 - b[i]];
    }

    printf("%d\n", count / 2);

    return 0;
}
