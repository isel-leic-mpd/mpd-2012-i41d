package cmdsh.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;

import cmdsh.args.ArgWithoutNameMandatory;
import cmdsh.args.ArgWithoutNameOptional;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;
import cmdsh.parsers.ArgsParserOrdered;

public class IoZip extends AbstractCommand{
	

	public IoZip() {
		super("io zip", new ArgsParserOrdered(), new IArgument[]{
				new ArgWithoutNameMandatory("inPath"),
				new ArgWithoutNameOptional("outPath")
		});
	}

	@Override
	public void executeCommand(){
		//
		// Executing command
		//
		Iterator<IArgument> iter = getArgs().iterator();
		IArgument<String> inPath = iter.next();
		IArgument<String> outPath = iter.next();
		String outFilename = null;
		if(outPath.getValue() == null){
			int idxDot = inPath.getValue().lastIndexOf('.');
			if(idxDot >= 0){outFilename = inPath.getValue().substring(0,idxDot) + ".rar";}
			else{outFilename = inPath.getValue() + ".rar";}
		}
		try {
			InputStream in = new FileInputStream(new File(inPath.getValue()));
			OutputStream out;
			out = new GZIPOutputStream(new FileOutputStream(outFilename));
			int c;
			while ((c = in.read()) != -1)
				out.write(c);
			in.close(); out.close();
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
}
