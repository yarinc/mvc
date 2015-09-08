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
		int loop, counter, c, i=0;
		while ((c = in.read()) != -1) {
			counter = in.read();
			if(counter != -1){
				for(loop = 0 ;loop < counter ;loop++) {
					b[i] = (byte) c;
					i++;
				}
			}
			else
				System.out.println("BLABLA");
		}
		return 0; 
		
	}
}
