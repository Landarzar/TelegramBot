/**
 * 
 */
package net.landarzar.telegram.model.methodes;

import java.io.BufferedReader;
import java.util.function.BiConsumer;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import net.landarzar.telegram.model.types.Message;

/**
 * @author Kai Sauerwald
 *
 */
public class SendPhoto extends MethodWithCallback<Message>
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

	public String chat_id;
	public String captio = null;

	public String parse_mode = "HTML";
	public Boolean disable_web_page_preview = null;
	public Boolean disable_notification = null;
	public Integer reply_to_message_id = null;
	public Object reply_markup = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.landarzar.telegram.model.methodes.Method#getPath()
	 */
	@Override
	public String getPath()
	{
		// TODO Auto-generated method stub
		return "sendMessage";
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
		
		ob.add("chat_id", chat_id);
//		ob.add("text", text);
		if(parse_mode != null)
			ob.add("parse_mode", parse_mode);
		if(disable_web_page_preview != null)
			ob.add("disable_web_page_preview", disable_web_page_preview);
		if(disable_notification != null)
			ob.add("disable_notification", disable_notification);
		if(reply_to_message_id != null)
			ob.add("reply_to_message_id", reply_to_message_id);
		if(reply_markup != null)
			ob.add("reply_markup", reply_markup.toString());
		
		return ob.build().toString();
	}

	/* (non-Javadoc)
	 * @see net.landarzar.telegram.model.methodes.MethodWithCallback#constructResult(java.io.BufferedReader)
	 */
	@Override
	protected Message constructResult(MethodResult mr)
	{
		return null;
	}

}
