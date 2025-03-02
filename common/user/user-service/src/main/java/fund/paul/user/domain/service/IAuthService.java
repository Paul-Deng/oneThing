package fund.paul.user.domain.service;

import fund.paul.userapi.dto.UserDTO;

public interface IAuthService {
    String login(UserDTO userDTO);
    void logout();
    UserDTO register(UserDTO userDTO);
}