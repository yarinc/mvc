package properties;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

import presenter.Properties;

public class Helper {
    public static void write(Properties f, String filename) throws Exception{
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
        encoder.writeObject(f);
        encoder.close();
    }

    public static Properties read(String filename) throws Exception {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
        Properties o = (Properties)decoder.readObject();
        decoder.close();
        return o;
    }
    public static void main (String [] args) throws Exception{
    	Properties f1 = new Properties();
    	f1.setNumberOfThreads(2);
    	f1.setCacheFileLocation("c:\\temp\\cachedMazes.gz");
    	f1.setViewType("GUI");
    	f1.setSolutionAlgorithm("air");
    	Helper.write(f1, "c:\\temp\\properties.xml");

    	Properties f2 = Helper.read("c:\\temp\\properties.xml");
        System.out.println(f2.getNumberOfThreads());
        System.out.println(f2.getCacheFileLocation());
    }
}

