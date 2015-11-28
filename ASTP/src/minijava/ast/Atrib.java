package minijava.ast;

import minijava.TabSimb;

public class Atrib implements Cmd {
	public String nome;
	public Exp exp;
	public int lin;
	
	public Atrib(String _nome, Exp _exp, int _lin) {
		nome = _nome;
		exp = _exp;
		lin = _lin;
	}

	public String toString() {
		return nome + " = " + exp + ";";
	}

	@Override
	public void tipos(Classe self, TabSimb<Classe> classes, TabSimb<String> vars) {
		String tlval = vars.procurar(nome);
		if(tlval == null)
			throw new RuntimeException("variavel " + nome + " nao declarada na linha " + lin);
		String trval = exp.tipo(self, classes, vars);
		Tipo.compativel(classes, trval, tlval, lin);
	}
}
