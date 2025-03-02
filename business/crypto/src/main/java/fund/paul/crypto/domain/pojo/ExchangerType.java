package fund.paul.crypto.domain.pojo;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * 交易所类型
 *
 * @author paul
 * @date 2025/1/7 01:01
 */
public enum ExchangerType implements IEnum<String> {
    BINANCE,
    ;

    @Override
    public String getValue() {
        return this.name();
    }
}
