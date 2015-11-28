package minijava.ast;

import minijava.TabSimb;

public class This implements Exp {
	public int lin;
	
	public This(int _lin) {
		lin = _lin;
	}

	public String toString() {
		return "this";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		return self.nome;
	}
}
