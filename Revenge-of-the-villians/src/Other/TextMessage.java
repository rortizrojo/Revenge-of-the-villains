package Other;

import org.newdawn.slick.Image;




/**
 * This class encapsulates data about how text is displayed in a Level
 * @author William Andrew Cahill
 */
public class TextMessage
{
	private String message;				// Message to display
	private Image messageSpeaker;		// Speaker of the message
	private int messageSpeed;			// Desired speed of the message
	private boolean autoMessage;// Flag that determines if the message should automatically remove itself after a certain amount of time
	private int autoTimeout;			// Amount of waiting time before message removes itself after finishing displaying its message

	
	
	
	/**
	 * Constructor
	 * @param message What is said
	 * @param messageSpeaker Image used for representing the message's speaker
	 */
	public TextMessage(String message, Image messageSpeaker)
	{
		// Assigns general text message attributes
		if(message == null || messageSpeaker == null)
			throw new IllegalArgumentException("Arguments must not be null");
		this.message = message;
		this.messageSpeaker = messageSpeaker;
		
		// Sets up default variables
		autoMessage = false;
		autoTimeout = 150;
		messageSpeed = 1;
	}
	
	
	
	
	/**
	 * Builds a TextMessage with no speaker
	 * @param message What is said
	 * @param messageSpeed The rate at which the message is given
	 */
	public TextMessage(String message)
	{
		// Assigns the message
		if(message == null)
			throw new IllegalArgumentException("Message must not be null");
		this.message = message;
		
		// Default message speed
		messageSpeed = 1;
		
		// Default auto value
		autoMessage = false;
		
		// Timeout value
		autoTimeout = 150;

	}
	
	
	
	
	/**
	 * @return What is said
	 */
	public String getMessage()
	{
		return message;
	}
	
	
	
	
	/**
	 * @return Image used for representing the message's speaker
	 */
	public Image getMessageSpeaker()
	{
		return messageSpeaker;
	}
	
	
	
	/**
	 * @return The speed at which the message is displayed.  Default value is 1.
	 */
	public int getMessageSpeed()
	{
		return messageSpeed;
	}
	
	
	
	
	/**
	 * Sets the speed at which the message is displayed.  Forces integers between 0 - 5.  1 is default.
	 * @param messageSpeed The speed at which the message is displayed.
	 */
	public void setMessageSpeed(int messageSpeed)
	{
		// Does safe assignment
		if(messageSpeed < 0)
			this.messageSpeed = 0;
		else if(messageSpeed > 5)
			this.messageSpeed = 5;
		else
			this.messageSpeed = messageSpeed;
	}
	
	
	
	
	/**
	 * @return Boolean flag that determines if the message automatically removes itself after a certain amount of time.
	 */
	public boolean isAutoMessage()
	{
		return autoMessage;
	}
	
	
	
	/**
	 * Sets the Boolean flag that determines if the message automatically removes itself after a certain amount of time.
	 * @param isAutoMessage Flag that determines if the message automatically removes itself after a certain amount of time.
	 */
	public void setAutoMessage(boolean isAutoMessage)
	{
		autoMessage = isAutoMessage;
	}
	
	
	
	
	/**
	 * Sets the amount of time it takes for a TextMessage to remove itself from a Level.  Never if isAutoMessage() returns false
	 * @param autoTimeout Amount of time it takes for a TextMessage to remove itself from a Level
	 * @return
	 */
	public void setAutoTimeout(int autoTimeout)
	{
		if(autoTimeout < 0)
			this.autoTimeout = 0;
		else if(autoTimeout > 10000)
			this.autoTimeout = 10000;
		else
			this.autoTimeout = autoTimeout;
	}
	
	
	
	
	/**
	 * Retrieves the amount of time it takes for a TextMessage to remove itself from a Level.  Never if isAutoMessage() returns false
	 */
	public int getAutoTimeout()
	{
		return autoTimeout;
	}
}
