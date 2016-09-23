/**
 * 
 */
package net.landarzar.telegram.model.types.inline;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 * @author Kai Sauerwald
 *
 */
public abstract class InputMessageContent
{

	/**
	 * @return
	 */
	public abstract JsonObject build();
//	{
//		JsonObjectBuilder builder = Json.createObjectBuilder();
//
//		return builder.build();
//	}

}
