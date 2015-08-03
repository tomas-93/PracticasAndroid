package mensajeria.tomas.com.practica2.models.object;

/*
*
* Created by Tomas on 30/07/2015.
*
*/
public class Config
{
	private String phone;
	private String host;
	private String hostStatus;

	public Config()
	{

	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getHost()
	{
		return this.host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getHostStatus()
	{
		return this.hostStatus;
	}
	
	public void setHostStatus(String hostStatus)
	{
		this.hostStatus = hostStatus;
	}
}