package procom;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class Gene {
	static Field[][] sfield=new Field[0][0];
	Field[][] field=new Field[0][0];
	int[][] point=new int[0][0];
	Gene(){
		field=Gene.sfield;
		point=new int[field.length][field[0].length];
		Random rnd=new Random();
		for(int i=0;i<point.length;i++){
			for(int j=0;j<point[0].length;j++){
				point[i][j]=rnd.nextInt(32)-16;
			}
		}
	}
	public String toString(){
		for(int i=0;i<point.length;i++){
			for(int j=0;j<point[i].length;j++){
				System.out.print(" "+point[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		return "";
	}
	static void assemble(){
		ArrayList<Gene> glist=new ArrayList<Gene>();
		for(int i=0;i<10;i++){
			glist.add(new Gene());
		}
		Stream<Gene> s=glist.stream();
		s.forEach(g->System.out.println(g.act(g.point,Field.b1x,Field.b1y,Field.b2x,Field.b2y)));
	}
	Bin act(int[][] point,int x1,int y1,int x2,int y2){
		int max=0,maxs=0,maxs2=0,tx1=0,ty1=0,tx2=0,ty2=0,tx1s=0,ty1s=0,tx2s=0,ty2s=0;
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				if((i!=0||j!=0)&&(y1+i)<point.length&&(x1+j)<point[0].length&&y1+i>0&&x1+j>0){
					if(point[y1+i][x1+j]>max){
						tx1=j;ty1=i;max=point[y1+i][x1+j];
					}
					else if(point[y1+i][x1+j]>maxs){
						tx1s=j;ty1s=i;maxs=point[y1+i][x1+j];
					}
				}
			}
		}
		max=0;
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				if((i!=0||j!=0)&&(y2+i)<point.length&&(x2+j)<point[0].length&&y2+i>0&&x2+j>0){
					if(point[y2+i][x2+j]>max){
						tx2=j;ty2=i;max=point[y2+i][x2+j];
					}
					else if(point[y2+i][x2+j]>maxs2){
						tx2s=j;ty2s=i;maxs2=point[y2+i][x2+j];
					}
				}
			}
		}
		if(tx1==tx2&&ty1==ty2){
			if(maxs>maxs2){
				tx1=tx1s;ty1=ty1s;
			}
			else{
				tx2=tx2s;ty2=ty2s;
			}
		}
		int n1=5-3*ty1+tx1;
		int n2=5-3*ty2+tx2;
		return new Bin(n1,n2);
	}
}
