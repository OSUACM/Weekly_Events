#include <cstdlib>
#include <iostream>

using namespace std;

#define SIZE 1000

char maze[SIZE][SIZE];
int len[SIZE][SIZE];
int queue[SIZE * SIZE][2];
int queueFront;
int queueBack;
int best = SIZE * SIZE;

void visit(int i, int j, int l) {
    if (maze[i][j] == 0) {
        best = l;
    } else if (maze[i][j] == '.' && len[i][j] < 0 && l < best) {
        len[i][j] = l;

        queue[queueBack][0] = i;
        queue[queueBack][1] = j;

        queueBack += 1;
    }
}

void print() {
    for (int i = 1; i < SIZE - 1; ++i) {
        if (maze[i][1]) {
            cout << &maze[i][1] << endl;
        }
    }
}

void trace(int i, int j, int l) {
    if (maze[i][j] == 'U' && len[i][j] == 0) {
        print();
    } else if (maze[i][j] == '.' && len[i][j] == l) {
        maze[i][j] = 'U';

        trace(i - 1, j, l - 1);
        trace(i + 1, j, l - 1);
        trace(i, j - 1, l - 1);
        trace(i, j + 1, l - 1);

        maze[i][j] = '.';
    }
}

int main() {
    for (int i = 1; i < SIZE - 1 && !cin.eof(); ++i) {
        cin.getline(&maze[i][1], SIZE - 2);
    }

    for (int i = 0; i < SIZE; ++i) {
        for (int j = 0; j < SIZE; ++j) {
            if (maze[i][j] == 'U') {
                len[i][j] = 0;

                queue[queueBack][0] = i;
                queue[queueBack][1] = j;

                queueBack += 1;
            } else {
                len[i][j] = -1;
            }
        }
    }

    while (queueFront != queueBack) {
        int i = queue[queueFront][0];
        int j = queue[queueFront][1];

        visit(i - 1, j, len[i][j] + 1);
        visit(i + 1, j, len[i][j] + 1);
        visit(i, j - 1, len[i][j] + 1);
        visit(i, j + 1, len[i][j] + 1);

        queueFront += 1;
    }

    for (int i = 0; i < SIZE; ++i) {
        for (int j = 0; j < SIZE; ++j) {
            if (
                maze[i - 1][j] == 0
                    || maze[i + 1][j] == 0
                    || maze[i][j - 1] == 0
                    || maze[i][j + 1] == 0
            ) {
                trace(i, j, best - 1);
            }
        }
    }

    return 0;
}
