//include pretty much any library you will ever need to use
#include <bits/stdc++.h>

using namespace std;

int main() {
	// These lines are not neccessary unless you are dealing inputs
	// of about 10^5. They increase the speed of input signifigantly.
	// you can also increase input speed a bit by using scanf and printf
	ios::sync_with_stdio(false);
	cin.tie(0);
	//you need this stuff though
	int r1,r2,s;
	cin >> r1 >> s;
	r2 = s + s - r1;
	cout << r2;
	return 0;
}
