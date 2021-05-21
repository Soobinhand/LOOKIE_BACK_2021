package ac.kr.smu.service;

import ac.kr.smu.mapper.UserMapper;
import ac.kr.smu.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {
    public void save(UserVO userVO);
    public boolean checkEmailDuplication(String email);
    public boolean checkPassword(String email, String password);
    public UserVO findByEmail(String email);


}


