#include "stdio.h"

int main() {
    int n, x;

    scanf("%d%d", &n, &x);

    int lucky_number[n];

    for (int i = 0; i < n; ++i) {
        scanf("%d", &lucky_number[i]);
    }

    int buffer[n + 1][2 * n + 1];
    #define result_arr(i) (buffer[i] - x + n)

    for (int i = x - n; i <= x + n; ++i) {
        result_arr(n)[i] = i;
    }

    for (int i = n - 1; i >= 0; --i) {
        for (int j = x - i; j <= x + i; ++j) {
            if (result_arr(i + 1)[j] == lucky_number[i]) {
                result_arr(i)[j] = result_arr(i + 1)[j];
            } else if (result_arr(i + 1)[j + 1] <= lucky_number[i]) {
                result_arr(i)[j] = result_arr(i + 1)[j + 1];
            } else if (result_arr(i + 1)[j - 1] >= lucky_number[i]) {
                result_arr(i)[j] = result_arr(i + 1)[j - 1];
            }
        }
    }

    printf("%d\n", result_arr(0)[x]);

    return 0;
}
