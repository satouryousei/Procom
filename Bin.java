package procom;

public class Bin {
	//Blue -> Red
	int n1,n2;
	Bin(int n1,int n2){
		this.n1=n1;
		this.n2=n2;
	}
	public String toString(){
		return String.valueOf(n1)+" "+String.valueOf(n2);
	}
	public boolean equals(Bin o) {
		return this.n1==o.n1&&this.n2==o.n2;
	}
	void set(int x,int y) {
		this.n1=y;
		this.n2=x;
	}
	void set(Bin b) {
		this.n1=b.n1;
		this.n2=b.n2;
	}
}
