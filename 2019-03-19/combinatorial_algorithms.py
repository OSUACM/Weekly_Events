"""
Some combinatorial algorithms implemented as generators.

For a great reference on combinatorial algorithms:
Knuth: http://www.cs.utsa.edu/~wagner/knuth/
"""

from itertools import product


def mixed_radix(arr: list):
    """
    I can grab up to arr[i] balls from each urn.
    Generate all of the possible collections of balls I can get.
    (1, 5, 4) --> (0,0,0), (1,0,0), ..., (1,5,4)
    """

    # # Using more universal language features
    # result = [0] * len(arr)
    # while True:
    #     yield result[:]
    #     result[0] += 1
    #     i = 0
    #     while result[i] > arr[i]:
    #         result[i] = 0
    #         if i+1 < len(result):
    #             result[i+1] += 1
    #             i += 1
    #         else:
    #             return

    # Using python standard library
    yield from product(*(range(i + 1) for i in arr))



def permutations(arr):
    """Generate all permutations of arr."""
    if len(arr) <= 1:
        yield arr
        return
    first = arr[0:1]
    for p in permutations(arr[1:]):
        for i in range(len(arr)):
            yield p[:i] + first + p[i:]


def permutations_adjacent(arr):
    """
    Generates all permutations, each only an adjacent swap away
    from the last. Uses space complexity Theta(n^2)
    """
    if len(arr) <= 1:
        yield arr
        return
    direction = 1
    first = arr[0:1]
    for p in permutations(arr[1:]):
        insertion_range = range(len(arr))
        if direction == -1:
            insertion_range = reversed(insertion_range)
        direction *= -1
        for i in insertion_range:
            yield p[:i] + first + p[i:]


def permutations_adjacent_in_place(arr):
    """
    Generates all permutations, each only an adjacent swap away
    from the last. Uses space complexity Theta(n).
    """

    # p1
    n = len(arr)
    inversions = [0] * n
    directions = [1] * n

    while True:
        # p2
        yield arr[:]
        # p3
        j = n
        s = 0
        while True:
            # p4
            q = inversions[j - 1] + directions[j - 1]
            if q >= 0:
                if q != j:
                    # p5
                    i1 = j - inversions[j - 1] + s
                    i2 = j - q + s
                    arr[i1 - 1], arr[i2 - 1] = arr[i2 - 1], arr[i1 - 1]
                    inversions[j - 1] = q
                    break
                # p6
                if j == 1:
                    return
                s += 1
            # p7
            directions[j - 1] *= -1
            j -= 1


def permutations_lex(arr):
    """
    Generates all permutations of arr, in lexicographic order.
    Uses space complexity Theta(n)
    (1,2,3,4) --> 1234, 1243, 1324, 1342, 1423, 1432, 2134, ...
    """
    n = len(arr)
    perm = list(range(n))

    while True:
        yield tuple(arr[i] for i in perm)
        # find j, the last guy with a bigger right-side
        j = n - 2
        while perm[j] > perm[j + 1]:
            j -= 1
            if j == -1:
                return

        # find l, the last guy bigger than j
        l = n - 1
        while perm[j] > perm[l]:
            l -= 1

        # swap j with l, its value-successor
        perm[j], perm[l] = perm[l], perm[j]

        # reverse what follows
        perm[j + 1:] = reversed(perm[j + 1:])


def combinations(arr, k):
    """
    Get all possible subsequences of length k.
    combinations('ABCDE', 3) --> ABC, ABD, ACD, BCD, ABE, ACE, BCE, ADE, BDE, CDE
    """
    arr = list(arr)
    n = len(arr)
    comb = list(range(k)) + [n, 0]
    while True:
        yield tuple(arr[i] for i in comb[:k])
        # find j: drop the initial stairstep down to start at zero,
        # except for the top step, which we increase.
        # maintains increasing order of arr.
        # iterates in colex order
        j = 0
        while comb[j + 1] == comb[j] + 1:
            comb[j] = j
            j += 1
        if j >= k:
            return
        comb[j] += 1