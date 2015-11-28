package minijava.ast;

import minijava.TabSimb;

public class New implements Exp {
	public String classe;
	public int lin;
	
	public New(String _classe, int _lin) {
		classe = _classe;
		lin = _lin;
	}

	public String toString() {
		return "new " + classe + "()";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		Classe cls = classes.procurar(classe);
		if(cls == null)
			throw new RuntimeException("classe " + classe + " nao declarada na linha " + lin);
		return classe;
	}
}
