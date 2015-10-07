package gps.tomas.com.gps.models.object;

/**
 * Created by Tomas on 12/09/2015.
 */
public class Location
{
    private int id;
    private String altitude, latitude, longitude, speed, acc;

    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }
    public String getLatitude()
    {
        return this.latitude;
    }
    public void setAltitude(String altitude)
    {
        this.altitude = altitude;
    }
    public String getAltitude()
    {
        return this.altitude;
    }
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }
    public String getLongitude()
    {
        return this.longitude;
    }
    public void setSpeed(String speed)
    {
        this.speed = speed;
    }
    public String getSpeed()
    {
        return this.speed;
    }
    public void setAcc(String acc)
    {
        this.acc = acc;
    }
    public String getAcc()
    {
        return this.acc;
    }
}
