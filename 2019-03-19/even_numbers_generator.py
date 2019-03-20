# python also offers an easier way:

def even_numbers_generator(maximum):
    current = 0
    while current <= maximum:
        yield current
        current += 2


print(even_numbers_generator(4), even_numbers_generator(4), even_numbers_generator(4))

e0 = even_numbers_generator(4)
for x in e0:
    print(x)

e1 = even_numbers_generator(10)
print(next(e1), next(e1), next(e1))

e2 = even_numbers_generator(10)
it = zip(e1, e2)
print(next(it), next(it), next(it))





def my_chain(iter_a, iter_b):
    for x in iter_a:
        yield x
    for x in iter_b:
        yield x


def my_product(iter_a, iter_b):
    for x in iter_a:
        for y in iter_b:
            yield (x, y)


def my_zip(iter_a, iter_b):
    while True:
        try:
            a = next(iter_a)
            b = next(iter_b)
        except(StopIteration):
            return
        yield (a,b)




if __name__ == '__main__':
    arr = []
    total = 0
    for x in even_numbers_generator(10):
        arr.append(x)
        total += x
    print(arr, total)