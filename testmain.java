package procom;

import procom.Enums.Team;

public class testmain {
	public static void main(String[] args) {
		Field[][] field=Func.makeField();
		Plate p=new Plate(field);
		Team cc=p.bf[0][0].c;
		System.out.println("c:"+cc);
		p.bf[0][0].c=Team.red;
		System.out.println(cc);
		cc=p.bf[0][0].c;
		System.out.println("c:"+cc);
	}
}
