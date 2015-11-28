package minijava.ast;

import java.util.List;

import minijava.TabSimb;

public class Bloco implements Cmd {
	public List<Cmd> cmds;
	
	public Bloco(List<Cmd> _cmds) {
		cmds = _cmds;
	}

	public String toString() {
		String res = "{\n ";
		for(Cmd cmd: cmds) {
			res += cmd + "\n";
		}
		res += "}";
		return res;
	}

	@Override
	public void tipos(Classe self, TabSimb<Classe> classes, TabSimb<String> vars) {
		for(Cmd cmd: cmds)
			cmd.tipos(self, classes, vars);
	}
}
