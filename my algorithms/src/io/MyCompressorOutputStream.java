package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}

	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	public void write(byte[] b) throws IOException {
		int i, counter = 1;
		for(i = 1; i<b.length;i++) {
			if(b[i-1] != b[i]) { 
				out.write(b[i-1]);
				out.write(counter);
				counter = 1;
			}
			else
				counter++;
			if(i == (b.length - 1)) {
				out.write(b[i]);
				out.write(counter);
			}
		}
	}

}
