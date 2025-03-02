package fund.paul.common.constant;

/**
 * @author paul
 * @date 2023/5/26 00:05
 */
public interface Constants {

    interface Col {
        String UPDATED_TIME = "updatedTime";

        String CREATED_TIME = "createdTime";

        String CREATED_BY = "createdBy";

        String UPDATED_BY = "updatedBy";
    }

    interface SysPermission {
        int TYPE_LEVEL_ONE = 1;
    }

    interface Storage {
        int INT_1M_BYTES = 1024 * 1024;

        long LONG_1M_BYTES = 1024L * 1024L;
    }

    interface Characters {
        String AT = "@";

        String COMMA = ",";
    }

    interface Network {
        String PING = "ping";

        String PONG = "pong";
    }
}
