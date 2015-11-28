package minijava.ast;

import minijava.TabSimb;

public class Campo implements Exp {
	public Exp obj;
	public String nome;
	public int lin;
	
	public Campo(Exp _obj, String _nome, int _lin) {
		obj = _obj;
		nome = _nome;
		lin = _lin;
	}

	public String toString() {
		return obj + "." + nome;
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		return null;
	}
}
