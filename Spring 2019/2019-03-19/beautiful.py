"""
A MUCH better, lazy implementation for checking if a number is squarefree.
"""
def possible_primes():
    yield from [2,3,5,7,9,11,13]
    p = 17
    while True:
        # ensures that p is congruent to 1 or 5, mod 6.
        yield p
        p += 2
        yield p
        p += 4

def prime_factorization(n):
    for divisor in possible_primes():
        while n % divisor == 0:
            yield divisor
            n //= divisor
        if divisor ** 2 > n:
            break
    if n > 1:
        yield n

def is_squarefree(n):
    previous_p = None
    for p in prime_factorization(n):
        if p == previous_p:
            return False
        previous_p = p
    return True

if __name__ == '__main__':
    print(is_squarefree(983709847091873029875085984750983740960))