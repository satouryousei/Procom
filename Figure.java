package procom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Figure extends Frame{
	int[][] field;
	int rect;
	Figure(int[][] field){
		this.field=field;
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		int rect1=800/field.length,rect2=900/field[0].length;
		rect=(rect1<rect2)?rect1:rect2;
		setSize(field[0].length*rect+200,field.length*rect+200);
		setFont(new Font("Arial", Font.PLAIN, 35));
		repaint();
		setVisible(true);
	}
	public void paint(Graphics g){
		for(int i=0;i<field.length;i++){
			for(int j=0;j<field[0].length;j++){
				int x=field[i][j];
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
}
