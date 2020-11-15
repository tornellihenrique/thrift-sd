import org.apache.thrift.TException;
import java.util.HashMap;
import chavevalor.*;

public class ChaveValorHandler implements ChaveValor.Iface {
    private HashMap<Integer,String> keyValue = new HashMap<>();

    @Override
    public String getKV(int key) throws TException {
        if(keyValue.containsKey(key)) {
            return keyValue.get(key);
        } else {
            throw new KeyNotFound();
        }
    }

    @Override
    public String setKV(int key, String valor) throws TException {
        String val = "None";

        if(keyValue.containsKey(key)) {
            val = getKV(key);
        }

        keyValue.put(key, valor);

        return val;
    }

    @Override
    public void delKV(int key) throws TException {
        keyValue.remove(key);
    }
}
