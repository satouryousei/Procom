package procom;
import static procom.Enums.Player.*;
import static procom.Enums.Team.*;

import java.util.Random;

public class Func {
	public static Field[][] makeField(){
		Random rnd=new Random();
		int len=rnd.nextInt(6)+6,wid=rnd.nextInt(6)+6;
		int sym=rnd.nextInt(3);
		int sx=rnd.nextInt(wid),sy=rnd.nextInt(len);
		int sx2=rnd.nextInt(wid),sy2=rnd.nextInt(len);
		Field[][] field=new Field[len][wid];
		for(int i=0;i<len;i++){
			for(int j=0;j<wid;j++){
				field[i][j]=new Field();
			}
		}
		if(sym==0){
			for(int i=0;i<len/2+1;i++){
				for(int j=0;j<wid/2+1;j++){
					field[i][j].v=rnd.nextInt(32)-16;
					field[field.length-i-1][field[0].length-j-1].v=field[i][j].v;
					field[i][field[0].length-j-1].v=field[i][j].v;
					field[field.length-i-1][j].v=field[i][j].v;
				}
			}
			if(rnd.nextInt(2)==0){
				while(sx==(field.length-1)/2)sx=rnd.nextInt(wid);
				while(sx2==(field.length-1)/2)sx2=rnd.nextInt(wid);
				while((sy==sy2||sy==sy2)&&sx==sx2) {
					sy2=rnd.nextInt(len);
				}
				field[sy][sx].c=blue;
				field[sy2][sx2].c=blue;
				field[sy][field[0].length-1-sx].c=red;
				field[sy2][field[0].length-1-sx2].c=red;
				field[sy][sx].p=b1;
				field[sy2][sx2].p=b2;
				field[sy][field[0].length-1-sx].p=r1;
				field[sy2][field[0].length-1-sx2].p=r2;
			}
			else{
				while(sy==field[0].length/2)sy=rnd.nextInt(len);
				while(sy2==field[0].length/2)sy2=rnd.nextInt(len);
				while(sy==sy2&&sx==sx2) {
					sx2=rnd.nextInt(len);
				}
				field[sy][sx].c=blue;
				field[sy2][sx2].c=blue;
				field[field.length-sy-1][sx].c=red;
				field[field.length-sy2-1][sx2].c=red;
				field[sy][sx].p=b1;
				field[sy2][sx2].p=b2;
				field[field.length-sy-1][sx].p=r1;
				field[field.length-sy2-1][sx2].p=r2;
			}
		}
		else if(sym==1){
			for(int i=0;i<len;i++){
				for(int j=0;j<wid;j++){
					if(j>field[0].length/2-1){
						field[i][j].v=field[i][field[0].length-j-1].v;
					}
					else field[i][j].v=rnd.nextInt(32)-16;
				}
				if(wid%2==1)field[i][wid/2].v=rnd.nextInt(32)-16;
			}
			while(sx==field[0].length/2){
				sx=rnd.nextInt(wid);
			}
			while(sx2==field[0].length/2){
				sx2=rnd.nextInt(wid);
			}
			while(sy==sy2&&sx==sx2) {
				sy2=rnd.nextInt(len);
			}
			field[sy][sx].c=blue;
			field[sy2][sx2].c=blue;
			field[sy][field[0].length-1-sx].c=red;
			field[sy2][field[0].length-1-sx2].c=red;
			field[sy][sx].p=b1;
			field[sy2][sx2].p=b2;
			field[sy][field[0].length-1-sx].p=r1;
			field[sy2][field[0].length-1-sx2].p=r2;
		}
		else if(sym==2){
			for(int i=0;i<len;i++){
				for(int j=0;j<wid;j++){
					if(i>field.length/2){
						field[i][j].v=field[field.length-i-1][j].v;
					}
					else field[i][j].v=rnd.nextInt(32)-16;
				}
			}
			while(sy==field.length/2){
				sy=rnd.nextInt(len);
			}
			while(sy2==field.length/2){
				sy2=rnd.nextInt(len);
			}
			while(sy==sy2&&sx==sx2) {
				sx2=rnd.nextInt(len);
			}
			field[sy][sx].c=blue;
			field[sy2][sx2].c=blue;
			field[field.length-sy-1][sx].c=red;
			field[field.length-sy2-1][sx2].c=red;
			field[sy][sx].p=b1;
			field[sy2][sx2].p=b2;
			field[field.length-sy-1][sx].p=r1;
			field[field.length-sy2-1][sx2].p=r2;
		}
//		else if(sym==3){
//			for(int i=0;i<len/2;i++){
//				for(int j=0;j<wid;j++){
//					field[i][j].v=rnd.nextInt(32)-16;
//					field[field.length-i-1][field[0].length-j-1].v=field[i][j].v;
//				}
//			}
//			if(len%2==1){
//				for(int j=0;j<field[0].length/2+1;j++){
//					field[len/2][j].v=rnd.nextInt(32)-16;
//				}
//				for(int j=field[0].length/2;j<field[0].length+1;j++){
//					field[len/2][j-1].v=field[len/2][field[0].length-j].v;
//				}
//			}
//		}
		return field;
	}
}
