package com.schoste.ddd.testing;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class TestFilesTest
{
	/**
	 * Asserts that resources can be correctly obtained as byte array
	 * 
	 * @throws Exception re-throws every exception
	 */
	@Test
	public void testGetTestResourceAsByteArray() throws Exception
	{
		String resourceName = "TestFile.txt";
		byte[] data = TestFiles.getTestResourceAsByteArray(resourceName);
		
		Assert.assertNotNull(data);
	}

	/**
	 * Asserts that resources can be correctly obtained as string
	 * 
	 * @throws Exception re-throws every exception
	 */
	@Test
	public void testGetTestResourceAsText() throws Exception
	{
		String resourceName = "TestFile.txt";
		String data = TestFiles.getTestResourceAsText(resourceName);
		
		Assert.assertEquals("TEST FILE", data);
	}

	/**
	 * Asserts that resources can be correctly obtained as files
	 * 
	 * @throws Exception re-throws every exception
	 */
	@Test
	public void testGetTestResourceAsFile() throws Exception
	{
		String resourceName = "TestFile.txt";
		File file = TestFiles.getTestResourceAsFile(resourceName, "prefix", "suffix");
		
		Assert.assertNotNull(file);
		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.getName().endsWith("suffix"));
		Assert.assertTrue(file.getName().startsWith("prefix"));
	}
}
