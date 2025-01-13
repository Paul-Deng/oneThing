package fund.paul.websocket.application.pojo;

/**
 * websocket的回调函数
 *
 * @author paul
 * @date 2025/1/6 01:23
 */
@FunctionalInterface
public interface WssCallback {
    void onReceive(String data);
}
