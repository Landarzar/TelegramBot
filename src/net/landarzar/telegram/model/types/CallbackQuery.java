/**
 * 
 */
package net.landarzar.telegram.model.types;

import javax.json.JsonObject;

/**
 * @author Kai Sauerwald
 *
 */
public class CallbackQuery
{

	/**
	 * @param jsonObject
	 * @return
	 */
	public static UpdateContent build(JsonObject jsonObject)
	{
		throw new UnsupportedOperationException(jsonObject.toString());
	}

}
