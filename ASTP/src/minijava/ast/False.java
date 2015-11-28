package minijava.ast;

import minijava.TabSimb;

public class False implements Exp {
	public String toString() {
		return "false";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		return "boolean";
	}
}
