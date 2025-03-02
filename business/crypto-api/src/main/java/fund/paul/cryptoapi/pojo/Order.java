package fund.paul.cryptoapi.pojo;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单类型
 *
 * @author paul
 * @date 2025/1/10 11:48
 */
public interface Order {

    /**
     * 订单见的字段
     */
    interface Col {
        /**
         * 最远离当前价格的入场价
         */
        String MAX_ENTRY_PRICE = "maxEntryPrice";

        /**
         * 最靠近当前价格的入场价
         */
        String MIN_ENTRY_PRICE = "minEntryPrice";

        /**
         * 总的购买金额
         */
        String TOTAL_PURCHASE_AMOUNT = "totalPurchasePrice";

        /**
         * 待执行的计划id
         */
        String ORDER_ID = "ORDER_ID";

        /**
         * 划分的数量
         */
        String SPLIT_COUNT = "SPLIT_COUNT";

        String CUR_PRICE = "CUR_PRICE";


    }

    /**
     * 订单格式
     */
    interface Format {
        int MAX_SCALE = 5;
    }


    /**
     * 订单类型
     */
    @AllArgsConstructor
    @Getter
    enum Type implements IEnum<String> {
        /**
         * 限价单
         */
        LIMIT("LIMIT"),

        /**
         *  市价单
         */
        MARKET("MARKET"),

        /**
         * 止损限价单
         */
        STOP("STOP"),

        /**
         * 止损市价单
         */
        STOP_MARKET("STOP_MARKET"),

        /**
         * 止盈限价单
         */
        TAKE_PROFIT("TAKE_PROFIT"),

        /**
         * 止盈市价单
         */
        TAKE_PROFIT_MARKET("TAKE_PROFIT_MARKET"),

        /**
         * 跟踪止损单
         */
        TRAILING_STOP_MARKET("TRAILING_STOP_MARKET"),

        /**
         * 爆仓
         */
        LIQUIDATION("LIQUIDATION"),
        ;

        private String binance;

        @Override
        public String getValue() {
            return this.name();
        }
    }

    /**
     * 订单方向
     */
    @AllArgsConstructor
    @Getter
    enum Direction implements IEnum<String> {
        BUY("BUY"),
        SELL("SELL"),
        ;
        private String binance;

        @Override
        public String getValue() {
            return this.name();
        }
    }

    /**
     * 订单操作
     */
    @AllArgsConstructor
    @Getter
    enum Operate implements IEnum<String> {
        NEW("NEW"),
        CANCELED("CANCELED"),

        /**
         * 订单 ADL 或爆仓
         */
        CALCULATED("CALCULATED"),

        /**
         *  订单失效
         */
        EXPIRED("EXPIRED"),

        /**
         * 交易
         */
        TRADE("TRADE"),

        /**
         * 订单修改
         */
        AMENDMENT("AMENDMENT"),

        ;
        private String binance;

        @Override
        public String getValue() {
            return this.name();
        }
    }

    /**
     * 订单状态
     */
    @AllArgsConstructor
    @Getter
    enum Status implements IEnum<String> {
        NEW("NEW"),

        /**
         * 部分成交
         */
        PARTIALLY_FILLED("PARTIALLY_FILLED"),

        /**
         * 成交
         */
        FILLED("FILLED"),

        /**
         *  取消
         */
        CANCELED("CANCELED"),

        /**
         * 过期
         */
        EXPIRED("EXPIRED"),

        /**
         * ？？？
         */
        EXPIRED_IN_MATCH("EXPIRED_IN_MATCH"),

        /**
         * 拟定，待执行
         */
        WAITING(""),
        ;
        private String binance;

        @Override
        public String getValue() {
            return this.name();
        }
    }

    /**
     * 有效方式
     */
    @AllArgsConstructor
    @Getter
    enum Method implements IEnum<String> {
        GTC("GTC"),

        /**
         * ???
         */
        IOC("IOC"),

        /**
         * ???
         */
        FOK("FOK"),

        /**
         *  ??
         */
        GTX("GTX"),

        ;
        private String binance;

        @Override
        public String getValue() {
            return this.name();
        }
    }
}
