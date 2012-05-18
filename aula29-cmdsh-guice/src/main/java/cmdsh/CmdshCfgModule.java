package cmdsh;

import java.util.HashSet;
import java.util.Set;

import javax.swing.text.AbstractDocument.Content;

import calc.IntExpressionFactory;
import calc.OpAddFactory;
import calc.OpDivFactory;
import calc.OpSubFactory;
import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.IntCalculator;
import cmdsh.core.Context;
import cmdsh.core.IContext;
import cmdsh.core.RootCmds;
import cmdsh.core.RootCtxName;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

public class CmdshCfgModule extends AbstractModule{

	@Override
	protected void configure() {
		
		bind(IContext.class).to(Context.class);
		bind(String.class).annotatedWith(RootCtxName.class).toInstance("Cmdsh");
		
		
		/*
		Set<Object> cmds = new HashSet<Object>();
		cmds.add(new GitAdd());
		cmds.add(new IntCalculator());
		cmds.add(new GitCommit());
		
		bind(new TypeLiteral<Set<Object>>(){}).annotatedWith(RootCmds.class).toInstance(cmds);
		*/
		
		Multibinder<Object> cmdsBinder = Multibinder.newSetBinder(binder(), Object.class, RootCmds.class);  
		cmdsBinder.addBinding().to(GitAdd.class);
		cmdsBinder.addBinding().to(GitCommit.class);
		cmdsBinder.addBinding().to(IntCalculator.class);
		
		Multibinder<IntExpressionFactory> exprBinder = Multibinder.newSetBinder(binder(), IntExpressionFactory.class);
		exprBinder.addBinding().to(OpAddFactory.class);
		exprBinder.addBinding().to(OpSubFactory.class);
		exprBinder.addBinding().to(OpDivFactory.class);
	}
}
