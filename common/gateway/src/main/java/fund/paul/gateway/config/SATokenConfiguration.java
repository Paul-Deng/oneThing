package fund.paul.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关的启动类
 *
 * @author paul
 * @date 2023/5/18 13:17
 */
@Configuration
public class SATokenConfiguration {

    /**
     * 注册 [sa-token全局过滤器]
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 指定 [拦截路由]
                .addInclude("/**")
                // 指定 [放行路由]
                .addExclude("/favicon.ico")
                // 指定[认证函数]: 每次请求执行
                .setAuth(r -> {
                    System.out.println("---------- sa全局认证");
                    // SaRouter.match("/test/test", () -> StpUtil.checkLogin());
                })
                // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数
                .setError(e -> {
                    System.out.println("---------- sa全局异常 ");
                    //todo 这里应该是直接写流，让其跳转
//                    ResponseUtils.responseWriter();
                    return null;
                })
                ;
    }
}
