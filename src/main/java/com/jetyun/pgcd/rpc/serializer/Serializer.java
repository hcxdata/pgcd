package com.jetyun.pgcd.rpc.serializer;

import java.io.Serializable;

/**
 * Interface to be implemented by custom serializer objects that convert to and
 * from Java objects and JSON objects.
 */
public interface Serializer extends Serializable {

	/**
	 * Determine if the given java,json class pair can be handled by this
	 * serializer. Both for serialzing from java => json and deserializing from
	 * json => java.
	 * 
	 * @param clazz
	 *            java Class type.
	 * @param jsonClazz
	 *            json Class wrapper type.
	 * @return true if this serializer can serialize/deserialize the given pair.
	 */
	public boolean canSerialize(Class clazz, Class jsonClazz);

	/**
	 * Get the json java classes that this Serializer is able to serialize from
	 * json into java and deserialize into json from java.
	 * <p/>
	 * These will typically be primitive class type wrappers or JSONObject,
	 * JSONArray.
	 * 
	 * @return json side java classes that can be serialized/deserialized by
	 *         this serializer.
	 */
	public Class[] getJSONClasses();

	/**
	 * Get the java classes that this Serializer is able to serialize from java
	 * into json and deserialize into java from json.
	 * 
	 * @return java side classes that can be serialized/deserialized by this
	 *         serializer.
	 */
	public Class[] getSerializableClasses();

	/**
	 * Marshall a java object into an equivalent json object.
	 * 
	 * @param state
	 *            can be used to hold state while unmarshalling through
	 *            recursive levels.
	 * @param p
	 *            parent of java object being marshalled into json (can be null
	 *            if the object is the root object being marshalled.
	 * @param o
	 *            java object to marhsall into json.
	 * @return that JSONObject or JSONArray that contains the json
	 *         representation of the java object that was marshalled.
	 * @throws MarshallException
	 *             if there is a problem marshalling java to json.
	 */
	public Object marshall(Object p, Object o) throws MarshallException;

	/**
	 * Set the owning JSONSerializer of this Serializer instance.
	 * 
	 * @param ser
	 *            the owning JSONSerializer of this Serializer instance.
	 */
	public void setOwner(JSONSerializer ser);

	/**
	 * Attempts to unmarshal a javascript object
	 * 
	 * @param clazz
	 *            The class to unmarhall to
	 * @param json
	 *            The object to unmarshal
	 * @return An ObjectMatch denoting whether the object matches the class (?)
	 * @throws UnmarshallException
	 */
	public ObjectMatch tryUnmarshall(Class clazz, Object json)
			throws UnmarshallException;

	/**
	 * Unmarshall json into an equivalent java object.
	 * 
	 * @param state
	 *            can be used to hold state while unmarshalling through
	 *            recursive levels.
	 * @param clazz
	 *            optional java class to unmarshall to.
	 * @param json
	 *            JSONObject or JSONArray that contains the json to unmarshall.
	 * @return the java object representing the json that was unmarshalled.
	 * @throws UnmarshallException
	 *             if there is a problem unmarshalling json to java.
	 */
	public Object unmarshall(Class clazz, Object json)
			throws UnmarshallException;
}
