package minijava.ast;

import minijava.TabSimb;

public class True implements Exp{
	public String toString() {
		return "true";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		return "boolean";
	}
}
