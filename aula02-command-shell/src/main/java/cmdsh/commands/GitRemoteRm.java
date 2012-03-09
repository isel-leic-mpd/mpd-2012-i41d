package cmdsh.commands;

public class GitRemoteRm extends AbstractCommand{
	
	public GitRemoteRm() {
		super("git remote rm");
	}
	@Override
	public void performCommand() {
		System.out.println("Performing git remote rm");		
	}
}
