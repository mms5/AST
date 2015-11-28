package minijava.ast;

import minijava.TabSimb;

public class AtribVetor implements Cmd {
	public String nome;
	public Exp ind;
	public Exp rval;
	public int lin;
	
	public AtribVetor(String _nome, Exp _ind, Exp _rval, int _lin) {
		nome = _nome;
		ind = _ind;
		rval = _rval;
		lin = _lin;
	}

	public String toString() {
		return nome + "[" + ind + "]" + " = " + rval + ";";
	}

	@Override
	public void tipos(Classe self, TabSimb<Classe> classes, TabSimb<String> vars) {
		String tlval = vars.procurar(nome);
		if(tlval == null)
			throw new RuntimeException("variavel " + nome + " nao declarada na linha " + lin);
		Tipo.compativel(classes, tlval, "int[]", lin);
		String tind = ind.tipo(self, classes, vars);
		Tipo.compativel(classes, tind, "int", lin);
		String trval = rval.tipo(self, classes, vars);
		Tipo.compativel(classes, trval, "int", lin);
	}
	
}
