package ac.kr.smu.service;

import ac.kr.smu.mapper.UserMapper;
import ac.kr.smu.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface UserService {
    public void save(UserVO userVO);
    public boolean checkEmailDuplication(String email);
}
@RequiredArgsConstructor
@Service
class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public void save(UserVO userVO) {
        userMapper.save(userVO);
    }

    @Override
    public boolean checkEmailDuplication(String email) {
        return userMapper.checkEmailDuplication(email)==0;
    }
}