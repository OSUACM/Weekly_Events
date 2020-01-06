from os import listdir
from os.path import isfile
import re

def directory_files():
    for filename in listdir():
        if isfile(filename):
            yield filename

def lines(filename):
    with open(filename) as f:
        yield from f

def grep(filename, regex):
    pattern = re.compile(regex)
    for line in lines(filename):
        if re.match(pattern, line):
            yield line

def folder_grep(regex):
    for filename in directory_files():
        yield from grep(filename, regex)

def accumulate_pairs(iterable):
    pair = []
    for x in iterable:
        pair.append(x)
        if len(pair) == 2:
            yield pair
            pair = []

if __name__ == '__main__':
    for line in accumulate_pairs(folder_grep("#.*")):
        print(line)