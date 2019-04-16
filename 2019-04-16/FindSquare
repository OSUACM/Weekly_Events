from sys import stdin

if __name__=="__main__":
    n, s = [int(x) for x in next(stdin).split()]
    neighbors = [[] for _ in range(n)]
    for _ in range(n-1):
        a, b = [int(x) for x in next(stdin).split()]
        neighbors[a-1].append(b-1)
        neighbors[b-1].append(a-1)

    leaves = 0
    for i, neighbor_list in enumerate(neighbors):
        if len(neighbor_list)==1:
            leaves += 1

    print(2 * s / leaves)
