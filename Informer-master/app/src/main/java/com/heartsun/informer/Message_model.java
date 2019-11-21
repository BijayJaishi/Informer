package com.heartsun.informer;

/**
 * Created by Bijay on 11/20/2019.
 */

public class Message_model
{
    private String Headline;

    private String CreatedDate;

    private String Photo;

    private String MsgDesc;

    public String getHeadline ()
    {
        return Headline;
    }

    public void setHeadline (String Headline)
    {
        this.Headline = Headline;
    }

    public String getCreatedDate ()
    {
        return CreatedDate;
    }

    public void setCreatedDate (String CreatedDate)
    {
        this.CreatedDate = CreatedDate;
    }

    public String getPhoto ()
    {
        return Photo;
    }

    public void setPhoto (String Photo)
    {
        this.Photo = Photo;
    }

    public String getMsgDesc ()
    {
        return MsgDesc;
    }

    public void setMsgDesc (String MsgDesc)
    {
        this.MsgDesc = MsgDesc;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Headline = "+Headline+", CreatedDate = "+CreatedDate+", Photo = "+Photo+", MsgDesc = "+MsgDesc+"]";
    }
}
