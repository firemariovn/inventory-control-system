package test;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
public class Messenger extends JFrame{
	private final int online = 0;
    private final int offline = 1;
    private final int refresh = 2;
    private DatagramSocket ds;
    private final String guid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
    private Map<String, User> userTable = new Hashtable<String, User>();
    
    public Messenger() {
        listen();// 监听收到的消息
        broadcast(online);// 广播上线
        System.out.println("hello!");
    }
    /*发送广播*/
    public void broadcast(int flag) {
        try {
            if (ds != null) {
                byte[] data = (flag + guid ).getBytes("UTF-8");
                ds.send(new DatagramPacket(data, 0, data.length, InetAddress.getByName("255.255.255.255"), 5413));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*监听*/
    public void listen() {
        try {
            ds = new DatagramSocket(5413);// 开启监听
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "5413端口被佔用", "錯誤", JOptionPane.ERROR_MESSAGE);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            return;
        }
        // 开启一个监听线程
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
                        ds.receive(dp);

                        
                        String msg = new String(dp.getData(), 0, dp.getLength(), "UTF-8");

                        if (msg != null && msg.length() > 0) {

                            int msgType = Integer.parseInt(msg.substring(0, 1));// 取得消息类型
                            String uid = msg.substring(1, 33);// 取得用户标识

                            switch (msgType) {
                            case 0:// 上线广播
                                if (!userTable.containsKey(uid)) {
                                    User user = new User();
                                    user.setGuid(uid);
                                    user.setInetAddress(dp.getAddress());

                                     addUser(user);// 添加到用戶
                                     System.out.println("新用户上线");
                                     System.out.println(uid);
                                     sleep(5000);
                                    // 响应
                                    byte[] data = (online + guid).getBytes("UTF-8");
                                    DatagramPacket p = new DatagramPacket(data, data.length, dp.getAddress(), 5413);
                                    ds.send(p);
                                    //ds.close();
                                }
                                else{
                                	System.out.println("已经存在");
                                }
                                break;
                            case 1:// 下线广播
                                removeUser(uid);
                                break;
                            case 2:// 刷新廣播
                                if (!guid.equals(uid)) {
                                    // 响应
                                    byte[] data = (online + guid ).getBytes("UTF-8");
                                    DatagramPacket p = new DatagramPacket(data, data.length, dp.getAddress(), 5413);
                                    ds.send(p);
                                }
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    
    public void removeUser(String uid) {
        userTable.remove(uid);
        System.out.println("一个用户下线");
    }
    public void addUser(User user) {
        userTable.put(user.getGuid(), user);
      
    }
    
    
}
