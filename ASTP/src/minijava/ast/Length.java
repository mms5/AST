package minijava.ast;

import minijava.TabSimb;

public class Length implements Exp {
	public Exp exp;
	public int lin;
	
	public Length(Exp _exp, int _lin) {
		exp = _exp;
		lin = _lin;
	}

	public String toString() {
		return exp + ".length";
	}

	@Override
	public String tipo(Classe self, TabSimb<Classe> classes,
			TabSimb<String> vars) {
		String tobj = exp.tipo(self, classes, vars);
		if(tobj.equals("int[]"))
			return "int";
		Classe classe = classes.procurar(tobj);
		if(classe == null)
			throw new RuntimeException(tobj + " nao eh uma classe declarada na linha " + lin);
		for(Var campo: classe.campos) {
			if(campo.nome.equals("length"))
				return campo.tipo;
		}
		throw new RuntimeException(tobj + " nao tem um campo length na linha " + lin);
	}
}
