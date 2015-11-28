package minijava.ast;

import minijava.TabSimb;

public class Nao implements Exp {
	public Exp e;
	public int lin;
	
	public Nao(Exp _e, int _lin) {
		e = _e;
		lin = _lin;
	}
	
	public String toString() {
		return "(!" + e + ")";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		String te = e.tipo(self, classes, vars);
		Tipo.compativel(classes, te, "boolean", lin);
		return "boolean";
	}
}
