package minijava.ast;

import minijava.TabSimb;

public class If implements Cmd {
	public Exp cond;
	public Cmd cthen;
	public Cmd celse;
	public int lin;
	
	public If(Exp _cond, Cmd _cthen, Cmd _celse, int _lin) {
		cond = _cond;
		cthen = _cthen;
		celse = _celse;
		lin = _lin;
	}

	public If(Exp _cond, Cmd _cthen, int _lin) {
		cond = _cond;
		cthen = _cthen;
		celse = null;
		lin = _lin;
	}

	public String toString() {
		return "if(" + cond + ") " + cthen +
				(celse == null? "" : " else " + celse);
	}

	@Override
	public void tipos(Classe self, TabSimb<Classe> classes, TabSimb<String> vars) {
		Tipo.compativel(classes, cond.tipo(self, classes, vars), "boolean", lin);
		cthen.tipos(self, classes, vars);
		celse.tipos(self, classes, vars);
	}
}
