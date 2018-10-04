package procom;

import static procom.Enums.Player.*;
import static procom.Enums.Team.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import procom.Enums.Player;

public class Frast extends Frame implements MouseListener{
	int rect;
	Field[][] field;
	Bin[] step= {null,null,null,null};
	int px=0,py=0;
	int rx=0,ry=0;
	boolean pflag=false,rflag=false;
	Frast(Field[][] field){
		this.field=field;
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		addMouseListener(this);
		int rect1=800/field.length,rect2=900/field[0].length;
		rect=(rect1<rect2)?rect1:rect2;
		setSize(field[0].length*rect+200,field.length*rect+200);
		setFont(new Font("Arial", Font.PLAIN, 35));
	}
	public void paint(Graphics g){
		for(int i=0;i<field.length;i++){
			for(int j=0;j<field[0].length;j++){
				int x=field[i][j].v;
				g.setColor(Color.black);
				if(field[i][j].c==blue){
					g.setColor(Color.blue);
					g.fillRect(rect*j+100,rect*i+100,rect,rect);
					g.setColor(Color.white);
					if(field[i][j].p==Player.b1)g.drawString("b1",rect*j+140,rect*i+170);
					else if(field[i][j].p==Player.b2)g.drawString("b2",rect*j+140,rect*i+170);
				}
				else if(field[i][j].c==red){
					g.setColor(Color.red);
					g.fillRect(rect*j+100,rect*i+100,rect,rect);
					g.setColor(Color.white);
					if(field[i][j].p==Player.r1)g.drawString("r1",rect*j+140,rect*i+170);
					else if(field[i][j].p==Player.r2)g.drawString("r2",rect*j+140,rect*i+170);
				}
				if(x>=10||(x<0&&x>-10)){
					g.drawString(" "+String.valueOf(x),rect*j+100,rect*i+140);
				}
				else if(x<10&&x>=0)g.drawString("  "+String.valueOf(x),rect*j+100,rect*i+140);
				else g.drawString(String.valueOf(x),rect*j+100,rect*i+140);
				g.setColor(Color.black);
				g.drawRect(rect*j+100,rect*i+100,rect,rect);
			}
		}
	}
	public void mousePressed(MouseEvent e){
		if(e.getX()-100>=0&&e.getY()-100>=0&&e.getX()<rect*field[0].length+100&&e.getY()<field.length*rect+100){
			px=(e.getX()-100)/rect;
			py=(e.getY()-100)/rect;
			pflag=true;
			System.out.println(field[py][px].p);
		}
		else {
			pflag=false;
		}
	}
	public void mouseReleased(MouseEvent e){
		if(e.getX()-100>=0&&e.getY()-100>=0&&e.getX()<rect*field[0].length+100&&e.getY()<field.length*rect+100){
			rx=(e.getX()-100)/rect;
			ry=(e.getY()-100)/rect;
			rflag=true;
		}
		else{
			System.out.println("範囲外");
			rflag=false;
		}
//		if(pflag&&rflag&&field[py][px].c!=none&&Math.abs(rx-px)<=1&&Math.abs(ry-py)<=1){
//			if(field[ry][rx].c==none){
//				field[ry][rx].c=field[py][px].c;
//				repaint();
//			}
//			else if(field[ry][rx].c!=field[py][px].c){
//				field[ry][rx].c=none;
//				repaint();
//			}
//		}
		if(pflag&&rflag&&(py!=ry||px!=rx)){
			if(field[py][px].p!=n) {
				if(field[py][px].p==b1) {
					step[0]=new Bin(rx,ry);
				}
				else if(field[py][px].p==b2) {
					step[1]=new Bin(rx,ry);
				}
				else if(field[py][px].p==r1) {
					step[2]=new Bin(rx,ry);
				}
				else if(field[py][px].p==r2) {
					step[3]=new Bin(rx,ry);
				}
				if(step[0]!=null&&step[1]!=null&&step[2]!=null&&step[3]!=null) {
					for(int i=0;i<step.length;i++) {
						System.out.println(step[i]);
					}
					this.field=act(step);
				}
			}
		}
	}
	public void mouseExited(MouseEvent e){
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseClicked(MouseEvent e){
	}
	public Field[][] act(Bin[] step){
		Field[][] field=this.field;
		for(int i=0;i<step.length;i++) {
			for(int j=0;j<step.length;j++) {
				if(i!=j&&step[i].equals(step[j]))break;
			}
			field=walk(i,step[i],field);
			repaint();
		}
		return field;
	}
	public Field[][] walk(int i,Bin s,Field[][] field){
		int tx=s.n1,ty=s.n2;
		if(i==0) {
			if(field[ty][tx].p==n) {
				field[Field.b1y][Field.b1x].p=n;
				field[ty][tx].p=b1;
				field[ty][tx].c=blue;
				Field.b1x=tx;
				Field.b1y=ty;
			}
			else {
				field[ty][tx].p=n;
			}
		}
		return field;
	}
}
