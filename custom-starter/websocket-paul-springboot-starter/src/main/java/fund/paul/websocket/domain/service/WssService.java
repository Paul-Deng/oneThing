package fund.paul.websocket.domain.service;

public interface WssService {
    void closeConnection(String streamId);
    void closeAllConnections();
}