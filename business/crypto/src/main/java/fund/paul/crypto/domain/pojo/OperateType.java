package fund.paul.crypto.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型
 *
 * @author paul
 * @date 2025/1/7 00:02
 */
@AllArgsConstructor
@Getter
public enum OperateType {
    // 订阅数据
    SUBSCRIBE("SUBSCRIBE"),

    // 退订数据
    UNSUBSCRIBE("UNSUBSCRIBE"),

    // 列出的当前订阅的数据
    LIST_SUBSCRIPTIONS("LIST_SUBSCRIPTIONS"),

    // 设定属性
    SET_PROPERTY("SET_PROPERTY"),


    ;

    final String binance;
}
