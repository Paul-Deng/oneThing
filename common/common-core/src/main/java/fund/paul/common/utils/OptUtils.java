package fund.paul.common.utils;

/**
 * 可选择的工具类
 *
 * @author paul
 * @date 2025/1/11 22:09
 */
public final class OptUtils {

    /**
     * 优先选一个不为空的数据
     *
     * @param opts 选项
     * @param <T> 泛型
     * @return 不为空的数据
     */
    public static <T> T orElse(T... opts) {
        for (T opt : opts) {
            if (opt != null) {
                return opt;
            }
        }
        return null;
    }
}
