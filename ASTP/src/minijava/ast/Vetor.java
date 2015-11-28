package minijava.ast;

import minijava.TabSimb;

public class Vetor implements Exp {
	public Exp tam;
	public int lin;
	
	public Vetor(Exp _tam, int _lin) {
		tam = _tam;
		lin = _lin;
	}

	public String toString() {
		return "new int[" + tam + "]";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		String ttam = tam.tipo(self, classes, vars);
		Tipo.compativel(classes, ttam, "int", lin);
		return "int[]";
	}
}
