package mensajeria.tomas.com.conexion.models.object;

/*
*
* Created by Tomas on 30/07/2015.
*
*/
public class Config
{
	private String host;
	private String status;

	public Config()
	{

	}

	public String getHost()
	{
		return this.host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getStatus()
	{
		return this.status;
	}
}