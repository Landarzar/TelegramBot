/**
 * 
 */
package net.landarzar.telegram.model.types;

import javax.json.JsonObject;

/**
 * @author Kai Sauerwald This object represents a Telegram user or bot.
 */
public class User
{
	public static User build(JsonObject usr)
	{
		User u = new User();

		u.id = usr.getInt("id");
		u.first_name = usr.getString("first_name");
		if (usr.containsKey("last_name"))
			u.last_name = usr.getString("last_name");
		if (usr.containsKey("username"))
			u.username = usr.getString("username");

		return u;
	}
	
	/***
	 * Unique identifier for this user or bot
	 */
	public int id;

	/***
	 * User‘s or bot’s first name
	 */
	public String first_name;

	/***
	 * Optional. User‘s or bot’s last name
	 */
	public String last_name = null;

	/***
	 * Optional. User‘s or bot’s last name
	 */
	public String username = null;
}
