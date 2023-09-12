package com.schoste.ddd.v1.testing;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Utility class which provides convenience methods to work with test files
 * 
 * @author Philipp Schosteritsch <s.philipp@schoste.com>
 */
public class TestFiles 
{
	/**
	 * Returns the contents of a resource as byte array
	 * 
	 * @param resourceName the name of the resource to get
	 * @return the content of the resource or null on error
	 */
	public static byte[] getTestResourceAsByteArray(String resourceName)
	{
		try
		{
			try (InputStream is = TestFiles.class.getClassLoader().getResourceAsStream(resourceName);
		         BufferedInputStream bis = new BufferedInputStream(is);
	             ByteArrayOutputStream baos = new ByteArrayOutputStream())
			{
		        byte[] buffer = new byte[bis.available()];
		        
		        while(bis.read(buffer, 0, buffer.length) > 0) baos.write(buffer);
		        
		        return buffer;
			}
		}
		catch (Exception e)
		{
			System.err.println(String.format("%s: %s", TestFiles.class.getName(), e.getMessage()));
			
			return null;
		}
	}
	
	/**
	 * Exports a resource into a temporary file and returns it
	 * 
	 * @param resourceName the name of the resource to get
	 * @param prefix the prefix of the temporary file
	 * @param suffix the suffix of the temporary file
	 * @return a File instance for the resource file or null on error
	 */
	public static File getTestResourceAsFile(String resourceName, String prefix, String suffix)
	{
		try
		{
			File tempFile = File.createTempFile(prefix, suffix);
			byte[] fileData = getTestResourceAsByteArray(resourceName);
			
			try (FileOutputStream fos = new FileOutputStream(tempFile)) 
			{
				fos.write(fileData);
				fos.close();
			}
			
			return tempFile;
		}
		catch (Exception e)
		{
			System.err.println(String.format("%s: %s", TestFiles.class.getName(), e.getMessage()));
			
			return null;
		}		
	}
	
	/**
	 * Returns the contents of a resource as UTF-8 text
	 * 
	 * @param resourceName the name of the resource to get
	 * @return the content of the resource as UTF-8 text or null on error
	 */
	public static String getTestResourceAsText(String resourceName)
	{
		try
		{
			byte[] fileData = getTestResourceAsByteArray(resourceName);
			
			return new String(fileData, Charset.forName("UTF-8"));
		}
		catch (Exception e)
		{
			System.err.println(String.format("%s: %s", TestFiles.class.getName(), e.getMessage()));
			
			return null;
		}		
	}
}
