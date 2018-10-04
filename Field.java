package procom;

import static procom.Enums.Player.*;
import static procom.Enums.Team.*;

import procom.Enums.Player;
import procom.Enums.Team;

public class Field {
	int v;
	Team c;
	Player p;
	Team sur=none;
	static int[][] cp=new int[4][2];
	static void init(Field[][] f){
		for(int i=0;i<f.length;i++){
			for(int j=0;j<f[0].length;j++){
				if(f[i][j].p==r1){
					cp[0][2]=j;cp[0][1]=i;
				}
				else if(f[i][j].p==r2){
					cp[1][2]=j;cp[1][1]=i;
				}
				else if(f[i][j].p==b1){
					cp[2][2]=j;cp[2][1]=i;
				}
				else if(f[i][j].p==b2){
					cp[3][2]=j;cp[3][1]=i;
				}
			}
		}
		Gene.sfield=f;
	}
	Field(){
		c=none;
		v=0;
		p=n;
	}
}
