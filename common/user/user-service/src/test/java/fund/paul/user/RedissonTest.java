package fund.paul.user;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void testRedissonClient() throws IOException {
        assertNotNull(redissonClient);
        System.out.println("RedissonClient config: " + redissonClient.getConfig().toJSON());
    }
}