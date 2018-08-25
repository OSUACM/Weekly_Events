#include <bits/stdc++.h>
using namespace std;

//source: made up
//problem: count number of elements a,b in an array s.t. a<b.
int main() {
	int n;
	cin >> n;
	int arr[n];
	for (int i = 0; i < n; i++){
		cin >> arr[i];
	}
	sort(arr,arr+n);
	int lastSameInd = 0;
	int count = 0;
	for (int i = 1; i < n; i++){
		if(arr[lastSameInd] != arr[i]){
			lastSameInd = i;
			count += i;
		}else{
			count += lastSameInd;
		}
	}		
	cout << count;
	return 0;
}
