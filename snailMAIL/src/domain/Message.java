package domain;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/30.
 */
public class Message {
    private int id;
    private int sentID;
    private int acceptID;
    private String mess;
    private Date recordtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSentID() {
        return sentID;
    }

    public void setSentID(int sentID) {
        this.sentID = sentID;
    }

    public int getAcceptID() {
        return acceptID;
    }

    public void setAcceptID(int acceptID) {
        this.acceptID = acceptID;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }
}
