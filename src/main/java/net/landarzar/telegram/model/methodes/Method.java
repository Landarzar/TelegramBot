/**
 * 
 */
package net.landarzar.telegram.model.methodes;

import java.io.BufferedReader;

/**
 * @author Kai Sauerwald
 *
 */
public interface Method
{
	/***
	 * Die REST-Addresse dieser Methode
	 * 
	 * @return
	 */
	public abstract String getPath();

	/***
	 * Konstruiert die Anfrage für den Server
	 * @return
	 */
	public abstract String buildMethod();

	/***
	 * Wird aufgerufen, nachdem die Methode erfolgreich ausgeführt wurde
	 */
	public abstract void afterPerform(MethodResult mr);
}
