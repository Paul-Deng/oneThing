package fund.paul.websocket.domain.service.impl;

import fund.paul.websocket.application.pojo.WssCallback;
import fund.paul.websocket.domain.pojo.OkWssConnection;
import fund.paul.websocket.domain.service.WssService;
import fund.paul.websocket.domain.utils.HttpClientSingleton;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * websocket具体实现类
 *
 * @author paul
 * @date 2025/1/6 01:40
 */
public class OkWssServiceImpl implements WssService {
    private final Logger LOGGER = LoggerFactory.getLogger(OkWssServiceImpl.class);

    private final Map<String, OkWssConnection> connections = new HashMap<>();

    /**
     * 返回 连接的id
     *
     * @param onOpenCallback
     * @param onMessageCallback
     * @param onClosingCallback
     * @param onFailureCallback
     * @param request
     * @return
     */
    public String createConnection(
            WssCallback onOpenCallback,
            WssCallback onMessageCallback,
            WssCallback onClosingCallback,
            WssCallback onFailureCallback,
            Request request
    ) {
        OkWssConnection connection = new OkWssConnection(onOpenCallback, onMessageCallback, onClosingCallback, onFailureCallback, request);
        connection.connect();
        String connectionId = connection.getConnectionId();
        connections.put(connectionId, connection);
        return connectionId;
    }

    @Override
    public void closeConnection(String connectionId) {
        if (connections.containsKey(connectionId)) {
            connections.get(connectionId).close();
            LOGGER.info("Closing Connection ID {}", connectionId);
            connections.remove(connectionId);
        } else {
            LOGGER.info("Connection ID {} does not exist!", connectionId);
        }
    }

    @Override
    public void closeAllConnections() {
        if (!connections.isEmpty()) {
            LOGGER.info("Closing {} connections(s)", connections.size());
            Iterator<Map.Entry<String, OkWssConnection>> iter = connections.entrySet().iterator();
            while (iter.hasNext()) {
                OkWssConnection connection = iter.next().getValue();
                connection.close();
                iter.remove();
            }
        }

        if (connections.isEmpty()) {
            HttpClientSingleton.getHttpClient().dispatcher().executorService().shutdown();
            LOGGER.info("All connections are closed!");
        }
    }
}
