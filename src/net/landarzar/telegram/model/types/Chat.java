/**
 * 
 */
package net.landarzar.telegram.model.types;

/**
 * @author Kai Sauerwald
 *
 */
public class Chat
{
	/***
	 * Unique identifier for this chat. This number may be greater than 32 bits
	 * and some programming languages may have difficulty/silent defects in
	 * interpreting it. But it smaller than 52 bits, so a signed 64 bit integer
	 * or double-precision float type are safe for storing this identifier.
	 */
	public long id;

	/***
	 * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
	 */
	public String type;

	/***
	 * Optional. Title, for channels and group chats
	 */
	public String title = null;

	/***
	 * Optional. Username, for private chats, supergroups and channels if
	 * available
	 */
	public String username = null;

	/***
	 * Optional. First name of the other party in a private chat
	 */
	public String first_name = null;

	/***
	 * Optional. Last name of the other party in a private chat
	 */
	public String last_name = null;
}
