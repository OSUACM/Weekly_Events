2N+1 lowercase letters are arranged in a circle.
Check if there is an axis of symmetry for the circle.

Example:
```
   b  b
a        c
---------- c
a        c
   b  b
```
These letters have an axis of symmetry. This is
because flipping the figure over the dotted line does not change any of the letters.

The input is a string. For example, the input for the last example could be the string
bcccbbaab. It could also be a cyclic rotation of the string such as abbcccbba

other inputs to consider:
aaaaaaa (has an axis of symmetry, actually several)
abcabca (has no axis of symmetry)

Implement the method below (change the template for your language)
```c++
bool hasAxis(string str){

}
```
