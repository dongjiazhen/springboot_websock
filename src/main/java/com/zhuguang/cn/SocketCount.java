package com.zhuguang.cn;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket")  //该注解表示该类被声明为一个webSocket终端
public class SocketCount {
	 /**
     * 初始在线人数
     */
    private static int online_num = 0;
    /**
     * 线程安全的socket集合
     */
    private static CopyOnWriteArraySet<SocketCount> webSocketSet = new CopyOnWriteArraySet<SocketCount>();
    /**
     * 会话
     */
    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.err.println("有链接加入，当前人数为:"+getOnline_num());
        this.session.getAsyncRemote().sendText("有链接加入，当前人数为:"+getOnline_num());

    }
    @OnClose
    public void onClose(){
    	 
    	 subOnlineCount();
    	 System.err.println("有链接关闭,当前人数为:"+getOnline_num());
    	webSocketSet.remove(this);
        
        
       
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        System.err.println("来自客户端的消息:"+message);
        for(SocketCount item:webSocketSet){
        	item.session.getAsyncRemote().sendText(message); 
 //       	 this.session.getAsyncRemote().sendText(message);  
//        	synchronized(this.session){       		 
//        		 this.session.getAsyncRemote().sendText(message);  	 
//        	}                     
           // session.getBasicRemote().sendText(message);  //同步发送
           // session.getAsyncRemote().sendText(message); //异步发送
        }
    }
    public synchronized int getOnline_num(){
        return SocketCount.online_num;
    }
    public synchronized int subOnlineCount(){
        return SocketCount.online_num--;
    }
    public synchronized int addOnlineCount(){
        return SocketCount.online_num++;
    }

}
