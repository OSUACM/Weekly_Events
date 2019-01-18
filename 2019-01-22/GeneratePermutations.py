perm = [1, 2, 3, 4]


def permutations(n):
    if n == 0:
        print(perm)
    else:
        for i in range(n):
            perm[i], perm[n - 1] = perm[n - 1], perm[i]
            permutations(n-1)
            perm[i], perm[n - 1] = perm[n - 1], perm[i]


if __name__ == "__main__":
    permutations(4)
