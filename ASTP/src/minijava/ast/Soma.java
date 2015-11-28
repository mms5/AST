package minijava.ast;

import minijava.TabSimb;

public class Soma implements Exp {
	public Exp e1;
	public Exp e2;
	public int lin;
	
	public Soma(Exp _e1, Exp _e2, int _lin) {
		e1 = _e1;
		e2 = _e2;
		lin = _lin;
	}
	
	public String toString() {
		return "(" + e1 + " + " + e2 + ")";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		String t1 = e1.tipo(self, classes, vars);
		Tipo.compativel(classes, t1, "int", lin);
		String t2 = e1.tipo(self, classes, vars);
		Tipo.compativel(classes, t2, "int", lin);
		return "int";
	}
}
