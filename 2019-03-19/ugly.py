"""
A horrible implementation for checking if a number is squarefree.
"""
def prime_factorization_ugly(n):
    """For each potential prime, as long as it divides, add it to the result list."""
    results = []

    for divisor in [2,3,5,7,11,13]:

        while n % divisor == 0:
            n //= divisor
            results.append(divisor)

        if divisor ** 2 > n:
            if n > 1:
                results.append(n)
            return results

    divisor = 17
    while True:
        # ensures that divisor is always congruent to 1 or 5, mod 6
        if divisor % 6 == 1:
            divisor += 4
        else:
            divisor += 2

        while n % divisor == 0:
            n //= divisor
            results.append(divisor)

        if divisor ** 2 > n:
            if n > 1:
                results.append(n)
            return results

def is_squarefree(n):
    # True iff there does not exist d s.t. d^2 divides n
    previous_p = None
    for p in prime_factorization_ugly(n):
        if p == previous_p:
            return False
        previous_p = p
    return True

if __name__ == '__main__':
    print(is_squarefree(983709847091873029875085984750983740960))