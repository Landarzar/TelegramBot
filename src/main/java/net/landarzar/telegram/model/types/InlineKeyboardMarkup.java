/**
 * 
 */
package net.landarzar.telegram.model.types;

import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * @author Kai Sauerwald
 *
 */
public class InlineKeyboardMarkup
{


	public JsonObject build()
	{
		JsonObjectBuilder builder = Json.createObjectBuilder();
		

		JsonArrayBuilder ab = Json.createArrayBuilder();
		for (LinkedList<InlineKeyboardButton> ll : field) {

			JsonArrayBuilder ab2 = Json.createArrayBuilder();
			for (InlineKeyboardButton ikb : ll) {				
				ab2.add(ikb.build());
			}
			ab.add(ab2.build());
		}

		builder.add("inline_keyboard", ab.build());
		
		
		return builder.build();
	}
	
	public LinkedList<LinkedList<InlineKeyboardButton>> field = new LinkedList<>();

}
