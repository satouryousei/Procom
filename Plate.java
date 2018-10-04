package procom;

import static procom.Enums.Player.*;
import static procom.Enums.Team.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import procom.Enums.Player;
import procom.Enums.Team;

public class Plate extends JFrame implements ActionListener{
	Player[] pa= {r1,r2,b1,b2};
	Dest[] dest=new Dest[4];
	int[] surP=new int[2];
	boolean pf=false;
	int pi,pj;
	FButton f;
	Team ourColor=red;
	GridLayout grid = new GridLayout();
	Field[][] field;
	FButton[][] bf;
	static int r1x,r2x,r1y,r2y,b1x,b1y,b2x,b2y;
	Plate(Field[][] f){
		super();
		field=f;
		//System.out.println(f.length+" "+f[0].length);
		for(int a=0;a<f.length;a++) {
			for(int b=0;b<f[0].length;b++)System.out.print(f[a][b].v+" ");
			//System.out.println();
		}
		setBounds(0,0,field[0].length*80,(field.length+1)*80);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		setVisible(true);
//		grid.setColumns(field[0].length);
		grid.setRows(field.length+1);
		c.setLayout(grid);
		bf=new FButton[field.length][field[0].length];
		for(int i=0;i<field.length;i++){
			for(int j=0;j<field[i].length;j++){
				Field x=field[i][j];
				FButton b=new FButton(x,i,j);
				b.addActionListener(this);
				c.add(b);
				bf[i][j]=b;
			}
			//System.out.println();
		}
		JButton wb=new JButton("walk");
		wb.addActionListener(this);
		c.add(wb);
		setContentPane(c);
//		renew();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("walk")) {
			boolean flag=false;
			for(int i=0;i<4;i++) {
				if(dest[i]==null)flag=true;
			}
			if(!flag) {
				cast();
				sum();
			}
		}
		else if(!pf) {
			f=(FButton)e.getSource();
			pf=true;
			f.bc=Color.RED;
			f.set();
		}
		else {
			pf=false;
			f.bc=Color.lightGray;
			f.set();
			FButton t=(FButton)e.getSource();
			if(Math.abs(f.i-t.i)<=1&&Math.abs(f.j-t.j)<=1) {
				switch(f.p) {
				case r1:walk(0,t.i,t.j,f.i,f.j);
//				bf[f.i][f.j].setBorder(new LineBorder(Color.yellow, 2, true));
				break;
				case r2:walk(1,t.i,t.j,f.i,f.j);
//				bf[f.i][f.j].setBorder(new LineBorder(Color.yellow, 2, true));
				break;
				case b1:walk(2,t.i,t.j,f.i,f.j);
//				bf[f.i][f.j].setBorder(new LineBorder(Color.yellow, 2, true));
				break;
				case b2:walk(3,t.i,t.j,f.i,f.j);
//				bf[f.i][f.j].setBorder(new LineBorder(Color.yellow, 2, true));
				break;
				}
			}
		}
	}
	void walk(int ind,int i,int j,int k, int l) {
		if(dest[ind]!=null){
			bf[dest[ind].ty][dest[ind].tx].bc=Color.lightGray;
			bf[dest[ind].ty][dest[ind].tx].set();
		}
		dest[ind]=new Dest(i,j,k,l);
		bf[i][j].bc=Color.yellow;
		bf[i][j].set();
	}
	void cast() {
		Bin[] aa=new Bin[4];
		Team[] ca=new Team[4];
		Team[] c2a=new Team[4];
		for(int i=0;i<4;i++) {
			aa[i]=new Bin(999,999);
			if(bf[dest[i].ty][dest[i].tx].c!=bf[dest[i].fy][dest[i].fx].c&&bf[dest[i].ty][dest[i].tx].c!=none) {
				aa[i].n1=dest[i].fy;
				aa[i].n2=dest[i].fx;
				//System.out.println("remover");
			}
			else {
				aa[i].n1=dest[i].ty;
				aa[i].n2=dest[i].tx;
				//System.out.println("walker");
			}
		}
		boolean ff=true;
		while(ff){
//			for(int i=0;i<4;i++) {
//				//System.out.println(dest[i].ty+" "+dest[i].tx);
//			}
			ff=false;
			for(int i=0;i<4;i++) {
				//System.out.println(aa[i]);
				for(int j=0;j<4;j++) {
					if(i!=j&&dest[i].to().equals(dest[j].from())&&dest[j].to().equals(dest[i].from())) {
						//System.out.println("change");
						aa[i].set(dest[i].from());
						aa[j].set(dest[j].from());
						dest[i].ts(aa[i]);
						dest[j].ts(aa[j]);
						ff=true;
					}
					if(i!=j&&dest[i].to().equals(aa[j])){
						//System.out.println("change");
						aa[i].set(dest[i].from());
						aa[j].set(dest[j].from());
						dest[i].ts(aa[i]);
						dest[j].ts(aa[j]);
						ff=true;
					}
				}
			}
		}
		for(int i=0;i<4;i++) {
			ca[i]=bf[dest[i].fy][dest[i].fx].c;
			c2a[i]=bf[dest[i].ty][dest[i].tx].c;
		}
		for(int i=0;i<4;i++) {
			Field ttf=field[dest[i].ty][dest[i].tx];
			Field fff=field[dest[i].fy][dest[i].fx];
			if(c2a[i]==none) {
				ttf.c=ca[i];
				if(fff.p==pa[i])fff.p=n;
				ttf.p=pa[i];
			}
			else if(c2a[i]!=ca[i])ttf.c=none;
			else {
				if(fff.p==pa[i])fff.p=n;
				ttf.p=pa[i];
			}
		}
		renew();

		for(int i=0;i<4;i++)dest[i]=null;
	}
	public void renew() {
		for(int i=0;i<field.length;i++) {
			for(int j=0;j<field[0].length;j++) {
				bf[i][j].set(new FButton(field[i][j],i,j));
			}
		}
	}
	public void sum() {
		int bp=0,rp=0;
		for(int i=0;i<bf.length;i++) {
			for(int j=0;j<bf[i].length;j++) {
				if(bf[i][j].c==red)rp+=bf[i][j].v;
				else if(bf[i][j].c==blue)bp+=bf[i][j].v;
			}
		}
		System.out.println("r="+rp+" b="+bp);
		area();
		as();
	}
	public void area() {
		Team c=red;
		for(int a=0;a<2;a++) {
			if(a==1)c=blue;
			for(int i=0;i<bf.length;i++) {
				for(int j=0;j<bf[0].length;j++) {
					bf[i][j].sur[a]=c;
					bf[i][j].set();
				}
			}
			for(int i=0;i<bf.length;i++) {
				if(bf[i][0].c!=c)surround(i,0,a);
				if(bf[i][bf[0].length-1].c!=c)surround(i,bf[0].length-1,a);
			}
			for(int j=0;j<bf[0].length;j++) {
				if(bf[0][j].c!=c)surround(0,j,a);
				if(bf[bf.length-1][j].c!=c)surround(bf.length-1,j,a);
			}
		}
		for(int i=0;i<bf.length;i++) {
			for(int j=0;j<bf[i].length;j++) {
				bf[i][j].set();
			}
		}
	}
	public void as(){
		int r=0,b=0;
		for(int i=0;i<bf.length;i++){
			for(int j=0;j<bf[0].length;j++){
				if(bf[i][j].sur[0]==red)r+=bf[i][j].v;
				if(bf[i][j].sur[1]==blue)b+=bf[i][j].v;
			}
		}
		System.out.println("rs="+r+" bs="+b);
		arrow(6,6);
	}
	public void surround(int y,int x,int a) {
		try {
			Team c;
			if(a==0)c=red;
			else c=blue;
			if(bf[y][x].c!=c) {
				bf[y][x].sur[a]=none;
				bf[y][x].set();
//				Thread.sleep(10);
			}
			for(int i=-1;i<=1;i++) {
				for(int j=-1;j<=1;j++) {
					if(i!=j&&Math.abs(i)+Math.abs(j)==1&&y+i<field.length&&y+i>=0&&x+j<field[0].length&&x+j>=0){
						if(bf[y+i][x+j].sur[a]==c) {
							if(bf[y+i][x+j].c!=c)surround(y+i,x+j,a);
							else bf[y+i][x+j].sur[a]=none;
						}
					}
				}
			}
		}catch(Exception e) {}
	}
	public void arrow(int vec1,int vec2){
		if(ourColor==red){
			bf[r1y+(vec1-1)/3-1][r1x+((vec1-1)%3)-1].bc=Color.CYAN;
			bf[r1y+(vec1-1)/3-1][r1x+((vec1-1)%3)-1].set();

			bf[r2y+(vec2-1)/3-1][r2x+((vec2-1)%3)-1].bc=Color.CYAN;
			bf[r2y+(vec2-1)/3-1][r2x+((vec2-1)%3)-1].set();
//			System.out.println("flag");
		}
	}
	public void arrow(int vec1,int vec2, Color c){
		if(ourColor==red){
			bf[r1y+(vec1-1)/3-1][r1x+((vec1-1)%3)-1].bc=c;
			bf[r1y+(vec1-1)/3-1][r1x+((vec1-1)%3)-1].set();

			bf[r2y+(vec2-1)/3-1][r2x+((vec2-1)%3)-1].bc=c;
			bf[r2y+(vec2-1)/3-1][r2x+((vec2-1)%3)-1].set();
//			System.out.println("flag");
		}
	}
}
class Dest{
	int tx,ty,fx,fy;
	Dest(int i,int j,int k,int l){
		tx=j; ty=i; fx=l; fy=k;
	}
	public boolean equals(Dest o) {
		if(ty==o.ty&tx==o.tx)return true;
		else return false;
	}
	Bin to() {
		return new Bin(ty,tx);
	}

	Bin from() {
		return new Bin(fy,fx);
	}
	void ts(int y,int x) {
		this.ty=y;
		this.tx=x;
	}
	void fs(int y,int x) {
		this.fy=y;
		this.fx=x;
	}
	void ts(Bin b) {
		this.ty=b.n1;
		this.tx=b.n2;
	}
	void fs(Bin b) {
		this.fy=b.n1;
		this.fx=b.n2;
	}
}
