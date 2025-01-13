package fund.paul.websocket.domain.pojo;

import cn.hutool.json.JSONUtil;
import fund.paul.websocket.application.pojo.WssCallback;
import fund.paul.websocket.domain.utils.HttpClientSingleton;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * okWss客户端的实现类
 *
 * @author paul
 * @date 2025/1/6 01:09
 */
public class OkWssConnection extends WebSocketListener {
    private final Logger LOGGER = LoggerFactory.getLogger(OkWssConnection.class);

    private static final int NORMAL_CLOSURE_STATUS = 1000;

    public static final WssCallback NOOP_CALL_BACK = msg -> {};


    private static final AtomicInteger connectionCounter = new AtomicInteger(0);
    private static final OkHttpClient client = HttpClientSingleton.getHttpClient();
    private final WssCallback onOpenCallback;
    private final WssCallback onMessageCallback;
    private final WssCallback onClosingCallback;
    private final WssCallback onFailureCallback;
    @Getter
    private final String connectionId;
    private final Request request;

    private final String streamName;

    private WebSocket webSocket;

    private final Object mutex;

    public OkWssConnection(WssCallback onOpenCallback, WssCallback onMessageCallback, WssCallback onClosingCallback, WssCallback onFailureCallback, Request request) {
        this.onClosingCallback = onClosingCallback;
        this.onMessageCallback = onMessageCallback;
        this.onOpenCallback = onOpenCallback;
        this.onFailureCallback = onFailureCallback;
        this.request = request;
        this.connectionId = request.tag() + String.valueOf(connectionCounter.incrementAndGet());
        this.streamName = request.url().host() + request.url().encodedPath();
        this.mutex = new Object();
    }



    public void connect() {
        synchronized (mutex) {
            if (null == webSocket) {
                LOGGER.info("[Connection {}] Connecting to {}", connectionId, streamName);
                webSocket = client.newWebSocket(request, this);
            } else {
                LOGGER.info("[Connection {}] is already connected to {}", connectionId, streamName);
            }
        }
    }

    public void close() {
        if (null != webSocket) {
            LOGGER.info("[Connection {}] Closing connection to {}", connectionId, streamName);
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
    }


    @Override
    public void onOpen(WebSocket ws, Response response) {
        LOGGER.info("[Connection {}] Connected to Server", connectionId);
        onOpenCallback.onReceive(null);
    }

    @Override
    public void onClosing(WebSocket ws, int code, String reason) {
        super.onClosing(ws, code, reason);
        onClosingCallback.onReceive(reason);
    }

    @Override
    public void onMessage(WebSocket ws, String text) {
        LOGGER.info("[Connection {}] receive msg : {}", connectionId, text);
        onMessageCallback.onReceive(text);
    }

    @Override
    public void onFailure(WebSocket ws, Throwable t, Response response) {
        LOGGER.error("[Connection {}] Failure", connectionId, t);
        onFailureCallback.onReceive(null);
    }

    public void send(Object msg) {
        webSocket.send(JSONUtil.toJsonStr(msg));
    }
}
