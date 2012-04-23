package cmdsh.test;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.core.AbstractCommand;
import cmdsh.core.Context;
import cmdsh.core.IArgument;
import cmdsh.core.IContext;
import cmdsh.core.MissingArgumentException;
import cmdsh.core.NoSuchCommandException;


public class TestCommands {
	Context cnt;
	public TestCommands(){
		try{
			Collection<AbstractCommand> cmds = new LinkedList<AbstractCommand>();
			File dir = new File("cmdsh\\commands");
			for (String fileName : dir.list()) {
				int idx;
				if((idx = fileName.indexOf(".class")) >= 0){
					String className = fileName.substring(0, idx);
					className = "cmdsh.commands." + className;
					Class c = Class.forName(className);
					if(AbstractCommand.class.isAssignableFrom(c)){
						Object o = c.newInstance();
						cmds.add((AbstractCommand)o);
					}
				}
			}
			cnt = new Context("CmdSh",cmds.toArray(new IContext[cmds.size()]));
		}catch(Exception e){
			throw new RuntimeException(e);			
		}
	}
	@Test
	public void checkCommand(){
		IContext c = cnt.getContext("git add");
		Assert.assertEquals("git add", c.getName());
	}
	@Test(expected=MissingArgumentException.class)
	public void checkGitRemoteAddWithArgsMissingArg(){
		IContext c = cnt.getContext("git remote add");
		Assert.assertEquals("git remote add", c.getName());
		String params = "-f --tags url";
		performCommand(c, params);
	}
	@Test
	public void checkGitRemoteAddWithArgsSucceed(){
		IContext c = cnt.getContext("git remote add");
		Assert.assertEquals("git remote add", c.getName());
		String params = "-f --tags name url";
		performCommand(c, params);
	}
	@Test
	public void checkGitAddWithAllArgsSucceed(){
		IContext c = cnt.getContext("git add");
		Assert.assertEquals("git add", c.getName());
		String params = "git add -f -n -v -i";
		c.performCommand(params);
		for (IArgument a : c.getArgs()) {
			Assert.assertEquals(true, ((Boolean)a.getValue()).booleanValue());
		}
	}
	@Test
	public void checkGitAddWithAllUnorderedArgsSucceed(){
		IContext c = cnt.getContext("git add");
		Assert.assertEquals("git add", c.getName());
		String params = "git add -n -f -i -v";
		c.performCommand(params);
		for (IArgument a : c.getArgs()) {
			Assert.assertEquals(true, ((Boolean)a.getValue()).booleanValue());
		}
	}
	@Test
	public void checkGitAddWithSomeArgsSucceed(){
		IContext c = cnt.getContext("git add");
		Assert.assertEquals("git add", c.getName());
		String params = "git add -n -i";
		c.performCommand(params);
		Iterator<IArgument> iter = c.getArgs().iterator();
		Assert.assertEquals(false, ((Boolean)iter.next().getValue()).booleanValue());
		Assert.assertEquals(true, ((Boolean)iter.next().getValue()).booleanValue());
		Assert.assertEquals(false, ((Boolean)iter.next().getValue()).booleanValue());
		Assert.assertEquals(true, ((Boolean)iter.next().getValue()).booleanValue());
	}
	public static void performCommand(IContext c, String params){
		String [] paramsSplit = params.split(" ");
		c.performCommand("git remote add " + params);
		Iterable<IArgument> args = c.getArgs();
		int idx = 0;
		for (IArgument a : args) {
			if(a.getName().equals("-f") || a.getName().equals("--tags"))
				Assert.assertTrue((Boolean) a.getValue());
			else
				Assert.assertEquals(paramsSplit[idx], a.getValue());
			idx++;
		}
	}
	/*
	@Test
	public void checkForUnkownCommand(){
		try{
			ICommand c = cnt.getCommand("git xpto");
			Assert.assertTrue(false);
		}catch(NoSuchCommandException e){
			Assert.assertTrue(true);
		}
	}*/
	@Test(expected=NoSuchCommandException.class)
	public void checkForUnkownCommand(){
		IContext c = cnt.getContext("git xpto");
	}
}
