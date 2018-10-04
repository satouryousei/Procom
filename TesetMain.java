package procom;

public class TesetMain {
	public static void main(String[] args){
		Field[][] field=Func.makeField();
		Frast f=new Frast(field);
		f.setVisible(true);
		
		Field.init(field);
//		Gene.assemble();
	}
}
