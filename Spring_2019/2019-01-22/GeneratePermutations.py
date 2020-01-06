max = 4
perm = [i+1 for i in range(max)]


def permutations(n):
    if n == 0:
        print(perm)
    else:
        # logic to generate permutations: move each element to the end
        # of the list, and recursively generate all permutations of the rest
        for i in range(n):
            perm[i], perm[n - 1] = perm[n - 1], perm[i]
            permutations(n - 1)
            perm[i], perm[n - 1] = perm[n - 1], perm[i]


if __name__ == "__main__":
    permutations(max)
