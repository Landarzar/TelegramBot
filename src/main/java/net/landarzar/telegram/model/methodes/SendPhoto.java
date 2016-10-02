/**
 * 
 */
package net.landarzar.telegram.model.methodes;

import java.io.BufferedReader;
import java.io.File;
import java.util.function.BiConsumer;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import net.landarzar.telegram.model.types.Message;
import net.landarzar.telegram.net.MultipartMethod;

/**
 * @author Kai Sauerwald
 *
 */
public class SendPhoto extends MethodWithCallback<Message> implements MultipartMethod
{
	public SendPhoto(String id, String text)
	{
		super(null);
		this.chat_id = id;
	}

	public SendPhoto(String id, String text, BiConsumer<MethodResult, Message> bc)
	{
		super(bc);
		this.chat_id = id;
	}

	/***
	 * Unique identifier for the target chat or username of the target channel
	 * (in the format @channelusername)
	 */
	public String chat_id;

	/***
	 * Photo to send. You can either pass a file_id as String to resend a photo
	 * that is already on the Telegram servers, or upload a new photo using
	 * multipart/form-data.
	 */
	public File photo_file = null;
	/***
	 * Photo to send. You can either pass a file_id as String to resend a photo
	 * that is already on the Telegram servers, or upload a new photo using
	 * multipart/form-data.
	 */
	public String photo_string = null;

	/***
	 * Photo caption (may also be used when resending photos by file_id), 0-200
	 * characters
	 */
	public String caption = null;

	/***
	 * Sends the message silently. iOS users will not receive a notification,
	 * Android users will receive a notification with no sound.
	 */
	public Boolean disable_notification = null;
	/***
	 * If the message is a reply, ID of the original message
	 */
	public Integer reply_to_message_id = null;

	// TODO: Hier richtigen Typen hinzufügen
	/***
	 * Additional interface options. A JSON-serialized object for an inline
	 * keyboard, custom reply keyboard, instructions to hide reply keyboard or
	 * to force a reply from the user.
	 */
	public Object reply_markup = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#getPath()
	 */
	@Override
	public String getPath()
	{
		return "sendPhoto";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#buildMethod()
	 */
	@Override
	public String buildMethod()
	{
		JsonObjectBuilder ob = Json.createObjectBuilder();

		// TODO: implementieren

		ob.add("chat_id", chat_id);
		if (photo_string != null)
			ob.add("photo", photo_string);
		else
			ob.add("photo", "attached_file");
		if (disable_notification != null)
			ob.add("disable_notification", disable_notification);
		if (reply_to_message_id != null)
			ob.add("reply_to_message_id", reply_to_message_id);
		if (reply_markup != null)
			ob.add("reply_markup", reply_markup.toString());

		return ob.build().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.landarzar.telegram.model.methodes.MethodWithCallback#constructResult(
	 * java.io.BufferedReader)
	 */
	@Override
	protected Message constructResult(MethodResult mr)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.net.MultipartSender#isMultipart()
	 */
	@Override
	public boolean shouldSendMultipart()
	{
		if (photo_file != null)
			return true;

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.landarzar.telegram.net.MultipartSender#buildMultiparts(org.apache.
	 * http.entity.mime.MultipartEntityBuilder)
	 */
	@Override
	public void buildMultiparts(MultipartEntityBuilder builder)
	{
		MultipartMethod.super.buildMultiparts(builder);
		
		builder.addPart("chat_id", new StringBody(chat_id, ContentType.TEXT_PLAIN));
		
		FileBody fbody = new FileBody(photo_file);
		builder.addPart("photo", fbody);
	}

}
