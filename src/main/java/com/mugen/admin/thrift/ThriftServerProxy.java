package com.mugen.admin.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * Created by aaron on 2016/12/27.
 */
public class ThriftServerProxy extends Thread{
    private TServer server;
    private ThriftServerProxy.ServerPort serverPort=new ThriftServerProxy.ServerPort();

    public ThriftServerProxy(int maxThreads, TProcessor processor) {
        TNonblockingServerSocket serverTransport = null;

        try {
            serverTransport = new TNonblockingServerSocket(serverPort.getPort());
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        TProcessorFactory processorFactory = new TProcessorFactory(processor);
        TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);
        tArgs.processorFactory(processorFactory);
        tArgs.transportFactory(new TFramedTransport.Factory());
        tArgs.protocolFactory(new TBinaryProtocol.Factory(true, true));
        tArgs.executorService(Executors.newWorkStealingPool(maxThreads));
        server = new TThreadedSelectorServer(tArgs);
    }


    public int getPort() {
        return serverPort.getPort();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        server.serve();
    }


    class ServerPort{



        private  InetSocketAddress ephemeralSocketAddress =null;
        private int port;

        public ServerPort() {
            this.ephemeralSocketAddress = new InetSocketAddress(0);
            this.port=nextPort();
        }

        /**
         *
         * 获取端口
         *
         * @return
         */
        public int getPort(){
            return this.port;
        }

        /**
         *
         * 获取一个随机端口
         *
         * @return
         */
        public int nextPort(){

            try{
                Socket s = new Socket();
                s.setReuseAddress(true);
                s.bind(ephemeralSocketAddress);
                int localPort = s.getLocalPort();
                s.close();
                return localPort;
            }catch (Exception e ){
                e.printStackTrace();
            }
            return 0;
        }
    }
}
