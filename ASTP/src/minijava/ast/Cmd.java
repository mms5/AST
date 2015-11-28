package minijava.ast;

import minijava.TabSimb;

public interface Cmd {
	void tipos(Classe self, TabSimb<Classe> classes, TabSimb<String> vars);
}
