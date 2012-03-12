package cmdsh.test;

import junit.framework.Assert;

import org.junit.Test;

import cmdsh.commands.GitAdd;
import cmdsh.commands.GitCommit;
import cmdsh.commands.GitRemote;
import cmdsh.commands.GitRemoteAdd;
import cmdsh.commands.GitRemoteRm;
import cmdsh.core.CmdContainer;
import cmdsh.core.ICommand;
import cmdsh.core.NoSuchCommandException;


public class TestCommands {
	CmdContainer cnt;
	ICommand cAdd, cCommit, cRemote, cRemoteAdd, cRemoteRm; 
	public TestCommands(){
		cnt = new CmdContainer(
				cAdd = new GitAdd(),
				cCommit = new GitCommit(),
				cRemote = new GitRemote(), 
				cRemoteAdd = new GitRemoteAdd(),
				cRemoteRm = new GitRemoteRm());
	}
	@Test
	public void checkCommand(){
		ICommand c = cnt.getCommand("git add");
		Assert.assertEquals(cAdd, c);
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
