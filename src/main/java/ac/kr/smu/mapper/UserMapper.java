package ac.kr.smu.mapper;
import ac.kr.smu.service.UserService;

import ac.kr.smu.vo.UserVO;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

     public void save(UserVO userVO);
     public int checkEmailDuplication(String email);
     public UserVO findByEmail(String email);
     public UserVO findById(int id);


}
