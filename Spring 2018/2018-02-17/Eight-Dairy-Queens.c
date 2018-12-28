#include <stdbool.h>
#include <stdio.h>
#include <math.h>

const int count = 8;
const int size = 8;
// const int pattern[] = {
//     0b100000010000001,
//     0b010000010000010,
//     0b001000010000100,
//     0b000100010001000,
//     0b000010010010000,
//     0b000001010100000,
//     0b000000111000000,
//     0b111111111111111,
//     0b000000111000000,
//     0b000001010100000,
//     0b000010010010000,
//     0b000100010001000,
//     0b001000010000100,
//     0b010000010000010,
//     0b100000010000001,
// };
const int pattern[] = {
    0b000000010000000,
    0b000000010000000,
    0b000000010000000,
    0b000000010000000,
    0b000000010000000,
    0b000000111000000,
    0b000001111100000,
    0b111111111111111,
    0b000001111100000,
    0b000000111000000,
    0b000000010000000,
    0b000000010000000,
    0b000000010000000,
    0b000000010000000,
    0b000000010000000,
};

int mask[count + 1][size];

int put(int c, int x, int y) {
    if (c == count) {
        return 1;
    }

    int result = 0;

    for (int i = 0; i < size; ++i) {
        if (i < x) continue;

        for (int j = 0; j < size; ++j) {
            if (i == x && j < y) continue;
            if ((mask[c][i] >> j) & 1) continue;

            for (int k = 0; k < size; ++k) {
                mask[c + 1][k] = mask[c][k] | (pattern[k + size - 1 - i] >> (size - 1 - j));
            }

            result += put(c + 1, i, j + 1);
        }
    }

    return result;
}

int main() {
    printf("%d\n", put(0, 0, 0));

    return 0;
}
