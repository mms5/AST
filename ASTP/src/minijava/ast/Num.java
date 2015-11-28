package minijava.ast;

import minijava.TabSimb;

public class Num implements Exp {
	public int val;
	
	public Num(String lexeme) {
		val = Integer.parseInt(lexeme);
	}

	public String toString() {
		return "" + val;
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		return "int";
	}
}

