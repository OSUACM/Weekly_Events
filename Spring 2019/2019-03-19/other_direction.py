from random import random

def human_simulator(mood_quality=0.5):
    print('Having free will...')
    while True:
        topic = (yield)
        if random() >= mood_quality:
            print(f'{topic} is good.')
        else:
            print(f'{topic} is bad.')

h1 = human_simulator()
next(h1)
h1.send('ACM')
h1.send('Programming with coroutines')
h1.send('Python')
h1.send('Alex Li')
h1.send('The Ohio State University')
h1.send('2019 as a whole')
h1.close()


def human_simulator_2():
    print('Having free will 2...')
    food = None
    stomach = None
    while food != 'poison':
        poop = 'No poop' if stomach is None else 'poop from {}'.format(stomach)
        stomach = food
        food = (yield poop)
    print(f'died with {stomach} in stomach')

h2 = human_simulator_2()
next(h2)
print(h2.send('apple'))
print(h2.send('banana'))
print(h2.send('cherry'))
try:
    print(h2.send('poison'))
except StopIteration:
    pass