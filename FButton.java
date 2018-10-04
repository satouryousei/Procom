package procom;

import static procom.Enums.Player.*;
import static procom.Enums.Team.*;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import procom.Enums.Player;
import procom.Enums.Team;

public class FButton extends JButton{
	Team c;
	Player p;
	Color bc;
	Team[] sur=new Team[2];
	int v;
	int i;
	int j;
	final Color r=new Color(255,100,100);
	final Color b=new Color(100,100,250);
	FButton(Field x,int i,int j){
		super(String.valueOf(x.v));
		this.v=x.v;
		this.c=x.c;
		this.p=x.p;
		this.i=i;
		this.j=j;
		this.bc=Color.lightGray;
		set();
	}
	void setValue(int x) {
		v=x;
		this.setText(String.valueOf(x));
	}
	void setColor(Team c) {
		this.c=c;
		if(c==red){
			this.setBackground(r);
		}
	}
	void set() {
		if(p==n)setBorder(new LineBorder(bc, 2, true));
		else setBorder(new LineBorder(Color.green, 2, true));
		if(p!=n)setText(String.valueOf(v)+"   "+p);
		else this.setText(String.valueOf(v));
		if(c==red)this.setBackground(new Color(255,100,100));
		else if(c==blue)this.setBackground(new Color(100,100,250));
		else if(sur[0]==red&&sur[1]==blue)this.setBackground(new Color(155,255,155));
		else if(sur[0]==red)this.setBackground(new Color(255,255,150));
		else if(sur[1]==blue)this.setBackground(new Color(150,255,255));
		else this.setBackground(new Color(255,255,255));
		this.paintImmediately(0,0,2000,2000);
	}
	void set(FButton x) {
		this.v=x.v;
		this.c=x.c;
		this.p=x.p;
		this.i=x.i;
		this.j=x.j;
		this.bc=Color.lightGray;
		if(x.p==r1){
			Plate.r1x=x.j;
			Plate.r1y=x.i;
		}
		else if(x.p==r2){
			Plate.r2x=x.j;
			Plate.r2y=x.i;
		}
		if(x.p==b1){
			Plate.b1x=x.j;
			Plate.b1y=x.i;
		}
		if(x.p==b2){
			Plate.b2x=x.j;
			Plate.b2y=x.i;
		}
		set();
	}
}
