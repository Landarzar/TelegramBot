/**
 * 
 */
package net.landarzar.telegram.model.types;

/**
 * @author Kai Sauerald
 *
 */
public interface UpdateContent
{
	/***
	 * 
	 * @author Kai Sauerwald
	 * The type of the one of the optional parameters can be present in any given update.
	 */
	public enum Type {
		/***
		 * New incoming message of any kind — text, photo, sticker, etc.
		 */
		MESSAGE,
		/***
		 * New version of a message that is known to the bot and was edited
		 */
		EDIT_MESSAGE,
		/***
		 * New incoming inline query
		 */
		INLINE_QUERY,
		/***
		 * The result of an inline query that was chosen by a user and sent to their chat partner.
		 */
		CHOSEN_INLINE_RESULT,
		/***
		 * New incoming callback query
		 */
		CALLBACK_QUERY
	};

	/***
	 * 
	 * @author Kai Sauerwald
	 * The type of the one of the optional parameters can be present in any given update.
	 */
	public Type getType(); 
}
