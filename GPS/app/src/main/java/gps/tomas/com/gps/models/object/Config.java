package gps.tomas.com.gps.models.object;

/**
 * Created by Tomas on 12/09/2015.
 */
public class Config
{
    private int id;
    private String host, timer, process;

    public String getHost()
    {
        return this.host;
    }
    public void setHost(String host)
    {
        this.host = host;
    }
    public String getTimer()
    {
        return this.timer;
    }
    public void setTimer(String timer)
    {
        this.timer = timer;
    }
    public String getProcess()
    {
        return this.process;
    }
    public void setProcess(String process)
    {
        this.process = process;
    }

}
