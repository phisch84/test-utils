package com.schoste.ddd.testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests the implementation of the {@link com.schoste.ddd.testing.GenericObjectTest} class
 * by testing {@link java.lang.String} objects.
 * 
 * @author Philipp Schosteritsch <s.philipp@schoste.com>
 */
public class StringObjectTest extends GenericObjectTest<String>
{

	@Override
	protected Map<String, Collection<String>> getEqualObjects() 
	{
		Map<String, Collection<String>> equalObjects = new HashMap<String, Collection<String>>();
		Collection<String> equalStrings = new ArrayList<String>();
		
		equalStrings.add("HALLO");
		equalStrings.add("HALLO");
		
		equalObjects.put("equalStrings", equalStrings);
		
		return equalObjects;
	}

	@Override
	protected Map<String, Collection<String>> getNotEqualObjects() 
	{
		Map<String, Collection<String>> notEqualObjects = new HashMap<String, Collection<String>>();

		Collection<String> nullAndEmptyStrings = new ArrayList<String>();
		nullAndEmptyStrings.add(null);
		nullAndEmptyStrings.add("");
		notEqualObjects.put("nullAndEmptyStrings", nullAndEmptyStrings);
		
		Collection<String> differentStrings = new ArrayList<String>();
		differentStrings.add("HaLLO");
		differentStrings.add("HALLO");
		differentStrings.add("HALLO ");
		differentStrings.add("WELT");
		notEqualObjects.put("differentStrings", differentStrings);

		return notEqualObjects;
	}

}
