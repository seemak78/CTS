/**
 * @author Seema
 *	
 *	20-Jul-2016
 */
package com.cucumber.framework.utility;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Seema
 *	
 *	20-Jul-2016
 *
 */
public class ResourceHelper {

	public static String getResourcePath(String resource) {
		String path = getBaseResourcePath() + resource;
		//System.out.println(path);
		return path;
	}
	
	public static String getBaseResourcePath() {
		String path = ResourceHelper.class.getResource("/").getPath();
		//System.out.println(path);
		return path;
	}
	
	/* Java 8
	 * public static InputStream getResourcePathInputStream(String resource) throws FileNotFoundException {
		return new FileInputStream(ResourceHelper.getResourcePath(resource));
	}*/
	
	public static InputStream getResourcePathInputStream(String resource) throws FileNotFoundException {
        // declaring the input stream
        // and initializing the stream.
        InputStream instr = null;
		try {
			instr = ResourceHelper.class.getClassLoader().getResourceAsStream(resource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Resource not found:"+resource+": error:"+e.getMessage());
		}
  
        // reading the files with buffered reader 
        //InputStreamReader readStream = new InputStreamReader(instr);
		if (instr==null)
			System.out.println("Resoure File "+resource+" not found");
		return instr;
	}
	   
	@SuppressWarnings("unused")
	private InputStream getFileInputStream(final String fileName) 
	{
	        InputStream ioStream = this.getClass()
	            .getClassLoader()
	            .getResourceAsStream(fileName);
	        
	        if (ioStream == null) {
	            throw new IllegalArgumentException(fileName + " is not found");
	        }
	        return ioStream;
	    }
	
	
}
