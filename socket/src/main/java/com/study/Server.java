package com.study;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 李国栋
 * @create 2020-10-28 4:26 下午
 */
public class Server {

    public static void main(String[] args) {
        final String QUIT = "quit";
        final int DEFAULT_PORT = 8888;
        ServerSocket serverSocket = null;

        try {
            // 绑定监听端口
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口： " + DEFAULT_PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端 ["+socket.getPort()+"]已连接");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String msg = null;
                while (( msg = reader.readLine()) != null) {
                    // 读取客户端发送的消息
                        System.out.println("客户端["+socket.getPort()+"]:"+msg);
                        writer.write("服务器："+msg +"\n");
                        writer.flush();

                        if (QUIT.equals(msg)){
                            System.out.println("客户端["+socket.getPort()+"]已断开连接");
                            break;
                        }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                    System.out.println("关闭serverSocket");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
