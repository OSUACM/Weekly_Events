#include <stdlib.h>
#include <stdio.h>

struct Tree {
    struct Tree *child[256];
    int value;
};

int main() {
    int n;
    scanf("%d", &n);

    int r[n];
    int g[n];
    int b[n];
    for (int i = 0; i < n; ++i) {
        scanf("%d%d%d", &r[i], &g[i], &b[i]);
    }

    #define NEW_TREE (struct Tree *) calloc(1, sizeof(struct Tree));

    struct Tree *root = NEW_TREE;
    for (int i = 0; i < n; ++i) {
        struct Tree *node = root;

        if (!node->child[r[i]]) node->child[r[i]] = NEW_TREE;
        node = node->child[r[i]];
        if (!node->child[g[i]]) node->child[g[i]] = NEW_TREE;
        node = node->child[g[i]];
        if (!node->child[b[i]]) node->child[b[i]] = NEW_TREE;
        node = node->child[b[i]];

        node->value += 1;
    }

    int count = 0;
    for (int i = 0; i < n; ++i) {
        struct Tree *node = root;

        if (!node->child[255 - r[i]]) continue;
        node = node->child[255 - r[i]];
        if (!node->child[255 - g[i]]) continue;
        node = node->child[255 - g[i]];
        if (!node->child[255 - b[i]]) continue;
        node = node->child[255 - b[i]];

        count += node->value;
    }

    printf("%d\n", count / 2);

    return 0;
}
