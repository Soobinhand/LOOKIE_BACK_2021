package ac.kr.smu.vo;

import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
@Data
public class UserVO implements UserDetails {
    private int id;
    private String name;
    private String password;
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    /*
     * Spring Security의 username을 반환
     */
    @Override
    public String getUsername() {
        return email;
    }

    /*
     * 계정이 만료되었는가?
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /*
     * 계정이 잠겼는가?
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /*
     * 계정의 인증(비밀번)이 만료되었는가?
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /*
     * 계정이 사용가능한가?
     * */
    @Override
    public boolean isEnabled() {
        return true;
    }
   }
