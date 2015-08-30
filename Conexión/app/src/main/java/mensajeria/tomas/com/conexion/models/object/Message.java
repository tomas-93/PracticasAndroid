package mensajeria.tomas.com.conexion.models.object;

/*
*
* Created by Tomas on 30/07/2015.
*
*/

public class Message
{
    private int id;
	private String phone;
	private String message;
	private String date;

	public Message()
	{	}

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public String getDate()
	{
		return this.date;
	}

}