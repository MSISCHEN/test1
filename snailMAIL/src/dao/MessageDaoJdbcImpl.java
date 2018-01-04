package dao;

import domain.Message;
import jdbc.JdbcUtil;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/30.
 */
public class MessageDaoJdbcImpl implements MessageDao {
    @Override
    public void addMess(Message message) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getConnection();
            String sql = "INSERT INTO message(sendID,acceptID,mess,recordtime) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setObject(1, message.getSentID());
            ps.setObject(2, message.getAcceptID());
            ps.setObject(3, message.getMess());
            ps.setObject(4, message.getRecordtime());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=JdbcUtil.getConnection();
            String sql="DELETE FROM message WHERE id=?";
            ps=con.prepareStatement(sql);
            ps.setObject(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public List<Message> readed(int acceptID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<Message>();
        try {
            con = JdbcUtil.getConnection();
            String sql = "SELECT id,sendID,mess,recordtime FROM message Where acceptID=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, acceptID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date datetime = rs.getDate("recordtime");
                if (compare(datetime) >= 0) {
                    Message message = new Message();
                    message.setId(rs.getInt("id"));
                    message.setSentID(rs.getInt("sendID"));
                    message.setMess(rs.getString("mess"));
                    message.setRecordtime(datetime);
                    messages.add(message);
                } else {
                    continue;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, con);
        }
        return messages;
    }

    @Override
    public int compare(Date date) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = df.parse(df.format((new Date())));
            Date dt2 = df.parse(df.format(date));
            if (dt1.getTime() > dt2.getTime()) {
//                System.out.print("当前时间在date的后面，则date这件事情发生了");
                //返回1表示已发送的信息
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
//                System.out.print("当前时间在date的前面，则date这件事情还没有发生");
                //返回-1表示未发送
                return -1;
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Message> unsent(int sentID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<Message>();
        try {
            con = JdbcUtil.getConnection();
            String sql = "SELECT id,acceptID,mess,recordtime FROM message Where sendID=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, sentID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date datetime = rs.getDate("recordtime");
                if (compare(datetime) == -1) {
                    Message message = new Message();
                    message.setId(rs.getInt("id"));
                    message.setAcceptID(rs.getInt("acceptID"));
                    message.setMess(rs.getString("mess"));
                    message.setRecordtime(datetime);
                    messages.add(message);
                } else {
                    continue;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, con);
        }
        return messages;

    }

    @Override
    public List<Message> sented(int sentID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Message> messages = new ArrayList<Message>();
        try {
            con = JdbcUtil.getConnection();
            String sql = "SELECT id,acceptID,mess,recordtime FROM message Where sendID=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, sentID);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date datetime = rs.getDate("recordtime");
                if (compare(datetime) == 1) {
                    Message message = new Message();
                    message.setId(rs.getInt("id"));
                    message.setAcceptID(rs.getInt("acceptID"));
                    message.setMess(rs.getString("mess"));
                    message.setRecordtime(datetime);
                    messages.add(message);
                } else {
                    continue;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, con);
        }
        return messages;

    }

    @Override
    public void delete(Message message) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getConnection();
            String sql = "DELETE message WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, message.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtil.free(rs, ps, con);
        }
    }
}
