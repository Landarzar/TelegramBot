/**
 * 
 */
package net.landarzar.telegram.model.types.inline;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 * @author Kai Sauerwald
 *
 */
public abstract class InlineQueryResult
{
	public abstract JsonObject build();
	
	
	/***
	 *  	Type of the result, must be article
	 */
	public String type;
	
	/***
	 *  	Unique identifier for this result, 1-64 Bytes
	 */
	public String id;
}
