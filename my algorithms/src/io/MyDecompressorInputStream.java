package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
	private InputStream in;

	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}

	@Override
	public int read() throws IOException {
		return in.read();
	}
	public int read(byte[] b) throws IOException {
		int loop, counter, c, i;
		for(i=0;i<36;i++){
			c=in.read();
			b[i] = (byte) c;
		}
		while ((c = in.read()) != -1) {
			counter = in.read();
			for(loop = 0 ;loop < counter ;loop++) {
				b[i] = (byte) c;
				i++;
			}
		}
		return 0; 
		
	}
}
