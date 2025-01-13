package fund.paul.userapi.vo;

import fund.paul.userapi.dto.UserDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author paul
 * @date 2024/1/30 23:45
 */
@Data
@NoArgsConstructor
public class UserVO extends UserDTO {

    @Builder(builderMethodName = "childBuilder")
    public UserVO(String username, String password, String nickname,
                  Integer gender, String email, String phone, String avatar, Integer status) {
        super(username, password, nickname, gender, email, phone, avatar, status);
    }

    private String testName;
}
