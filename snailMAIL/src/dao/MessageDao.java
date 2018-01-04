package dao;

import domain.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/30.
 */
public interface MessageDao {
    //增加信息
    public void addMess(Message message);
    //通过信息的id删除信息
    public void delete(int id);

    //读取已读信息
    public List<Message> readed(int id);

    //读取未发送信息
    public List<Message> unsent(int id);

    //读取已发送信息
    public List<Message> sented(int id);

    //删除信息
    public void delete(Message message);



    //两个时间之间的比较
    public int compare(Date date);


}
