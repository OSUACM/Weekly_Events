import itertools
import collections







# for-each loops
arr = [2,3,5,7,11]

i = 0
while i < len(arr):
    print(i)
    i += 1

for i in range(len(arr)):
    print(arr[i])

for p in arr:
    print(p)









# iterate over a dictionary
fruit_quantities = {'apple': 2, 'banana':75, 'cherry': 0}

for fruit in fruit_quantities:
    print(fruit_quantities[fruit])

for fruit, n in fruit_quantities.items():
    print(n)









# useful python builtin
for i, x in enumerate(['apple', 'banana', 'cherry']):
    print(i, x)

chars = ['a', 'b', 'c']
nums = [10, 20, 30]
for x in zip(chars, nums):
    print(x)

for x in itertools.chain(chars, nums):
    print(x)

for x in itertools.product(chars, nums):
    print(x)

for x in itertools.combinations('ABCD', 2):
    print(x)









# one way to make a custom iterator

class even_numbers_iterator(collections.Iterator):
    # iterates over even numbers up to maximum
    def __init__(self, maximum):
        self.maximum = maximum
        self.current = 0

    def __next__(self):
        result = self.current
        if result > self.maximum:
            raise StopIteration
        else:
            self.current += 2
            return result

# How can we use this?

print(even_numbers_iterator(4), even_numbers_iterator(4), even_numbers_iterator(4))

e0 = even_numbers_iterator(4)
for x in e0:
    print(x)

e1 = even_numbers_iterator(10)
print(next(e1), next(e1), next(e1))

e2 = even_numbers_iterator(10)
it = zip(e1, e2)
print(next(it), next(it), next(it))
