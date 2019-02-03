#include <bits/stdc++.h>
using namespace std;


int main() {
	int n;
	int K;
	cin >> n >> K;
	/**
	* input array. Since we know the maximum size, we can allocate
	* this much space to make the program easier to write
	**/
	int a[200000];

	// read array
	for (int i = 0; i < n; ++i){
		cin >> a[i];
	}
	// sort first n elements of array in ascending order
	sort(a, a+n, less<int>());

	int remaining = n;
	// for each bacteria
	for(int i = 0; i < n;){
		// Find the index of the smallest bacteria STRICTLY larger than it
		int eaterInd = i+1;
		while(eaterInd<n && a[i]==a[eaterInd]){
			eaterInd++;
		}
		// See if the larger bacteria can eat the bacteria (and all others of the same mass)
		if(eaterInd<n && a[i]+K>=a[eaterInd]){
			remaining -= (eaterInd-i);
		}
		i = eaterInd;
	}
	cout << remaining;
}
