package cmdsh.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import cmdsh.core.AbstractCommand;

public class IoZip extends AbstractCommand{

	private static final String inPath = "D:\\MyFolder\\ISEL\\Pg4 mpd - 2011-2012 2º sem\\MDP Apontamentos.doc";
	public IoZip() {
		super("io zip");
	}

	@Override
	public void performCommand(String params) {
		String outPath;
		int idxDot = inPath.lastIndexOf('.');
		if(idxDot >= 0){outPath = inPath.substring(0,idxDot) + ".rar";}
		else{outPath = inPath + ".rar";}
		try {
			InputStream in = new FileInputStream(new File(inPath));
			OutputStream out;
			out = new GZIPOutputStream(new FileOutputStream(outPath));
			int c;
			while ((c = in.read()) != -1)
				out.write(c);
			in.close(); out.close();
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}

}
