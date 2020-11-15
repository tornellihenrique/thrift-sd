import chavevalor.*;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ChaveValorServer {
    public static ChaveValorHandler handler = new ChaveValorHandler();
    public static ChaveValor.Processor processor = new ChaveValor.Processor(handler);

    public static void main(String[] args) {
        Runnable simple = new Runnable() {
            public void run() {
                simple(processor);
            }
        };

        new Thread(simple).start();
    }

    private static void simple(ChaveValor.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("Starting server");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}