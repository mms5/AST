package minijava.ast;

import minijava.TabSimb;

public interface Exp {
	String tipo(Classe self, TabSimb<Classe> classes, TabSimb<String> vars);
}
