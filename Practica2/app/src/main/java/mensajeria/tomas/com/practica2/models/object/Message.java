package mensajeria.tomas.com.practica2.models.object;

/*
*
* Created by Tomas on 30/07/2015.
*
*/

public class Message
{
	private String latitude;
	private String logitude;
	private String altitude;
	private String speed;

	public Message()
	{	}

	public String getLatitude()
	{
		return this.logitude;
	}
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	public String getLongitude()
	{
		return this.logitude;
	}
	public void setLongitude(String logitude)
	{
		this.logitude = logitude;
	}
	public String getAltitude()
	{
		return this.altitude;
	}
	public void setAltitude(String altitude)
	{
		this.altitude = altitude;
	}
	public String getSpeed()
	{
		return this.speed;
	}
	public void setSpeed(String speed)
	{
		this.speed = speed;
	}
}