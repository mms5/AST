package minijava.ast;

import minijava.TabSimb;

public class Igual implements Exp {
	public Exp e1;
	public Exp e2;
	public int lin;
	
	public Igual(Exp _e1, Exp _e2, int _lin) {
		e1 = _e1;
		e2 = _e2;
		lin = _lin;
	}
	
	public String toString() {
		return "(" + e1 + " == " + e2 + ")";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		String t1 = e1.tipo(self, classes, vars);
		String t2 = e2.tipo(self, classes, vars);
		if((t1.equals("int") && !t2.equals("int")) ||
		   (!t1.equals("int") && t2.equals("int")))
		  throw new RuntimeException(t1 + " e " + t2 + " nao podem ser comparados na linha " + lin);
		return "boolean";
	}
}
