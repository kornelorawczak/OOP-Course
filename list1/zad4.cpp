#include <iostream>
#include <iomanip>
using namespace std;

void table(float x1, float x2, float y1, float y2, float jump){
    float *tab;
    int l = (y2-y1)/jump;
    tab = new float[l];
    cout<<fixed<<setprecision(2);
    cout<<"       ";
    for (int i=0; i<=l; i++){
        tab[i]=y1;
        y1+=jump;
        cout<<tab[i]<<" ";
    }
    cout<<endl;
    while (x1<=x2){
        cout<<x1<<":  ";
        for (int i=0; i<=l; i++){
            cout<<x1*tab[i]<<" ";
        }
        cout<<endl;
        x1+=jump;
    }

    delete [] tab;
}

int main(){
    table(0.2, 1.3, 0.2, 3.14, 0.3);
    return 0;
}