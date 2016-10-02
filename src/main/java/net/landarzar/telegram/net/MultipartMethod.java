/**
 * 
 */
package net.landarzar.telegram.net;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import net.landarzar.telegram.model.methodes.Method;

/**
 * @author Kai Sauerwald
 *
 */
public interface MultipartMethod extends Method
{
	public default boolean shouldSendMultipart()
	{
		return false;
	}

	public default void buildMultiparts(MultipartEntityBuilder builder)
	{
//		builder.setMode(HttpMultipartMode.STRICT);
//		StringBody cbody = new StringBody(buildMethod(), ContentType.APPLICATION_JSON);
//		builder.addPart("", cbody); //??
	}
}
