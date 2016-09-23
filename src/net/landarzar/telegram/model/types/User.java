/**
 * 
 */
package net.landarzar.telegram.model.types;

/**
 * @author Kai Sauerwald This object represents a Telegram user or bot.
 */
public class User
{
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
