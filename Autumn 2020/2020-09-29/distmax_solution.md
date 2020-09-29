Hello, interviewer! We expect that a qualified applicant can solve this problem
in O(N^2) time without any trouble, even in the short 25 minute interval.
To do so:
iterate over all pairs of points, and find the two most distant. Done. They should
code a working solution to this first.

```c++
int[] xCoords = int[n];
int[] yCoords = int[n];
// Assume the two above arrays are initalized with the input values
int maxD = 0;
for(int i = 0; i < n; i++){
    for(int j = i + 1; j < n; j++){
        maxD = max(maxD, abs(xCoords[i]-xCoords[j]) + abs(yCoords[i]-yCoords[j]));
    }
}
return maxD;
```

-----------------------------------

If the applicant finishes quickly, you can ask them to try to solve it with a
faster algorithm. The following algorithm works:
rotate the plane by 45 degrees: (x, y) -> (x+y, x-y)
Then, the distance between any two points is the max of the distance between
their first and second coordinate. Thus we can solve in O(n) time by just subtracting
the min value from the max.

A formal proof can be found at:
https://img.atcoder.jp/abc178/editorial-E-en.pdf

I found that it was easier for me to come up with the following solution, but it's way (WAY) harder
to code: The max distance pair will surely have both points on the convex hull of the n points.
So, find the convex hull. Then we can use the 2-pointers technique while going around the points
in a clockwise order to solve in O(n) time.

