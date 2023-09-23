package com.schoste.ddd.testing.v1;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

/**
 * Generic test class to test objects (models, entities, DOs, DTOs, etc.)
 * Performs tests on the {@link java.lang.Object#equals(Object)} method and the
 * {@link java.lang.Object#hashCode()} method.
 * 
 * @author Philipp Schosteritsch <s.philipp@schoste.com>
 *
 * @param <T> the actual class of the objects to test.
 */
public abstract class GenericObjectTest<T>
{
    /**
     * Gets sets of equal objects. The {@link java.lang.Object#equals(Object)} methods
     * for these objects is expected to return true.
     * The {@link java.lang.Object#hashCode()} method is expected to return the same value
     * for these objects.
     * 
     * Actual test classes must implement this method.
     * 
     * @returns A map of sets with equal objects
     */
	protected abstract Map<String, Collection<T>> getEqualObjects();

    /**
     * Gets sets of unequal objects. The {@link java.Object#equals(Object)} methods
     * for these objects is expected to return false.
     * The {@link java.Object#hashCode()} method may return either an equal or different hash code.
     * 
     * Actual test classes must implement this method.
     * 
     * @returns A map of sets with equal objects
     */
	protected abstract Map<String, Collection<T>> getNotEqualObjects();

	private void testEqualSetHashCode(String setName, Collection<T> equalObjects)
	{
		Collection<T> innerObjects = equalObjects;
		Collection<T> outerObjects = equalObjects;
		
		for (T outerObject : outerObjects)
		{
			for (T innerObject : innerObjects)
			{
				String message = String.format("Object %s has a different hash code than object %s", innerObject, outerObject);
				Assert.assertTrue(message, innerObject.hashCode() == outerObject.hashCode());
			}
		}
	}
	
	/**
	 * Asserts that for all objects in an equal-set {@link #getEqualObjects()}
	 * the hash code has the same value.
	 */
	@Test
	public void testHashCode()
	{
		Map<String, Collection<T>> equalObjectsSet = this.getEqualObjects();
		Collection<String> setNames = equalObjectsSet.keySet();
		
		for (String setName : setNames)
		{
			Collection<T> equalObjects = equalObjectsSet.get(setName);
			
			this.testEqualSetHashCode(setName, equalObjects);
		}
	}
	
	private void testEqualSet(String setName, Collection<T> equalObjects)
	{
		Collection<T> innerObjects = equalObjects;
		Collection<T> outerObjects = equalObjects;
		
		for (T outerObject : outerObjects)
		{
			for (T innerObject : innerObjects)
			{
				if (innerObject == outerObject) continue;

				String message = String.format("Object %s does not equal object %s", innerObject, outerObject);
				Assert.assertTrue(message, Objects.equals(innerObject, outerObject));
			}
		}
	}

	private void testNotEqualSet(String setName, Collection<T> notEqualObjects)
	{
		Collection<T> innerObjects = notEqualObjects;
		Collection<T> outerObjects = notEqualObjects;
		
		for (T outerObject : outerObjects)
		{
			for (T innerObject : innerObjects)
			{
				if (innerObject == outerObject) continue;
				
				String message = String.format("Object %s does equals object %s", innerObject, outerObject);
				Assert.assertFalse(message, Objects.equals(innerObject, outerObject));
			}
		}
	}

	/**
	 * Asserts that for all objects in an equal-set {@link #getEqualObjects()}
	 * the {@link java.lang.Object#equals(Object)} method returns true and that
	 * for all objects in an equal-set {@link #getNotEqualObjects()}
	 * the {@link java.lang.Object#equals(Object)} method returns false.
	 */
	@Test
	public void testEquals()
	{
		Map<String, Collection<T>> equalObjectsSet = this.getEqualObjects();
		Collection<String> equalObjectsSetNames = equalObjectsSet.keySet();
		
		for (String equalObjectsSetName : equalObjectsSetNames)
		{
			Collection<T> equalObjects = equalObjectsSet.get(equalObjectsSetName);
			
			this.testEqualSet(equalObjectsSetName, equalObjects);
		}
		
		Map<String, Collection<T>> notEqualObjectsSet = this.getNotEqualObjects();
		Collection<String> notEqualObjectsSetNames = notEqualObjectsSet.keySet();
		
		for (String notEqualObjectsSetName : notEqualObjectsSetNames)
		{
			Collection<T> notEqualObjects = notEqualObjectsSet.get(notEqualObjectsSetName);
			
			this.testNotEqualSet(notEqualObjectsSetName, notEqualObjects);
		}
	}
}
