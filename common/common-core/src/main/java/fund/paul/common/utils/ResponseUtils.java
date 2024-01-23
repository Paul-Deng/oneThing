package fund.paul.common.utils;

import cn.hutool.json.JSONUtil;
import fund.paul.common.basic.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.Writer;

/**
 * 响应Utils，用于给前端绘制页面，或强行跳转
 *
 * @author paul
 * @date 2023/5/18 13:23
 */
public class ResponseUtils {
    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * 通过流写到前端
     *
     * @param response
     * @param msg          返回信息
     * @param httpStatus   返回状态码
     * @throws IOException
     */
    public static void responseWriter(HttpServletResponse response, String msg, int httpStatus) throws IOException {
        Result result = Result.of(null, httpStatus, msg);
        responseWrite(response, result);
    }

    /**
     * 通过流写到前端
     * @param response
     * @param obj
     */
    public static void responseSucceed(HttpServletResponse response, Object obj) throws IOException {
        Result result = Result.succeed(obj);
        responseWrite(response, result);
    }

    /**
     * 通过流写到前端
     * @param 
     * @param response
     * @param msg
     * @throws IOException
     */
    public static void responseFailed(HttpServletResponse response, String msg) throws IOException {
        Result result = Result.failed(msg);
        responseWrite(response, result);
    }

    private static void responseWrite(HttpServletResponse response, Result result) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (Writer writer = response.getWriter()) {
            writer.write(JSONUtil.toJsonStr(result));
            writer.flush();
        }
    }
}
