package ac.kr.smu.service.Impl;

import ac.kr.smu.mapper.UserMapper;
import ac.kr.smu.service.UserService;
import ac.kr.smu.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(UserVO userVO) {
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
        userMapper.save(userVO);
    }

    @Override
    public boolean checkEmailDuplication(String email) {
        return userMapper.checkEmailDuplication(email)==0;
    }

    @Override
    public boolean checkPassword(String email, String password) {
        return passwordEncoder.matches(password,userMapper.findByEmail(email).getPassword());
    }

    @Override
    public UserVO findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

}
