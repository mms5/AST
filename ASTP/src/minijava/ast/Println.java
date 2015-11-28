package minijava.ast;

import minijava.TabSimb;

public class Println implements Cmd {
	public Exp exp;
	public int lin;
	
	public Println(Exp _exp, int _lin) {
		exp = _exp;
		lin = _lin;
	}

	public String toString() {
		return "System.out.println(" + exp + ");";
	}

	@Override
	public void tipos(Classe self, TabSimb<Classe> classes, TabSimb<String> vars) {
		Tipo.compativel(classes, exp.tipo(self, classes, vars), "int", lin);
	}
}
