package cmdsh.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemote;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.core.CmdContainer;
import cmdsh.core.IArgument;
import cmdsh.core.ICommand;
import cmdsh.core.MissingArgumentException;
import cmdsh.core.NoSuchCommandException;


public class TestCommands {
	CmdContainer cnt;
	ICommand cAdd, cCommit, cRemote, cRemoteAdd, cRemoteRm; 
	public TestCommands(){
		cnt = new CmdContainer(
				cAdd = new GitAdd(),
				cCommit = new GitCommit(), 
				cRemoteAdd = new GitRemoteAdd(),
				cRemoteRm = new GitRemoteRm(),
				cRemote = new GitRemote());
	}
	@Test
	public void checkCommand(){
		ICommand c = cnt.getCommand("git add");
		Assert.assertEquals(cAdd, c);
	}
	@Test(expected=MissingArgumentException.class)
	public void checkGitRemoteAddWithArgsMissingArg(){
		ICommand c = cnt.getCommand("git remote add");
		Assert.assertEquals(cRemoteAdd, c);
		String params = "-f --tags url";
		performCommand(c, params);
	}
	@Test
	public void checkGitRemoteAddWithArgsSucceed(){
		ICommand c = cnt.getCommand("git remote add");
		Assert.assertEquals(cRemoteAdd, c);
		String params = "-f --tags name url";
		performCommand(c, params);
	}
	public static void performCommand(ICommand c, String params){
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
		ICommand c = cnt.getCommand("git xpto");
	}
}
