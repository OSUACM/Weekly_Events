Hello, interviewer! We expect that a qualified applicant can solve this problem
in O(N^2) time. You should try to help the interviee realize such a solution with
minimal hints.

Here is one possible solution in this time range:

For each point, suppose that it is the center of the string. Then check that the
letter i positions to the right and i positions to the left are the same. 

```c++
for(int center = 0; center < str.size(); center++){
    bool ok = true;
    for(int dist = 0; dist < str.size()/2; dist++){
        if(str[(center + dist)%str.size()] != str[(center - dist + str.size())%str.size()]){
            ok = false;
        }
    }
    if(ok){ // The string with this center works
        return true;    
    }
}
// no string works
return false;
```
-----------------------------------

If the interviewee succeeds within the time interview, you can challenge them to come up with a faster
solution. This is harder, though. Some our our smartest minds have been working on this problem, here
are a few interesting ideas:

Hashing: You can only check the strings where a hash of the next N characters equals a hash of the last N characters. A simple
but ineffective hash could be the sum of the char codes. More effective would be a polynomial rolling hash computed in each direction. The
latter method will almost always give O(N) runtime.

Shrinking the problem: Notice that, if a hash exists, exactly 1 letter appears an odd number of times, and that letter is the axis
of the reflection. Then, we only need to consider what happens when the axis of reflection goes through that letter. This could be
helpful if the single letter doesn't appear too many times.


