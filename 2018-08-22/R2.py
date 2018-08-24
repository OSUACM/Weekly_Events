# Python 3.6
# input() -> returns a line of input
# input().split() -> splits a line of input by spaces. So "1 4" becomes the array ["1","4"]
# map(int,input().split) -> maps the string "1" to the number 1, and "4" to the number 4.
# it's a bit complex, but use it a couple times and it'll be ok
r1, s = map(int, input().split());
print(2*s-r1);