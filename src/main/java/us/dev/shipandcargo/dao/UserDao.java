package us.dev.shipandcargo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import us.dev.shipandcargo.domain.User;

@Repository
public interface UserDao {

    int insertUser(User record);
    User selectUserById(@Param("id") Long userId);
    User selectUserByEmail(@Param("email") String email);
    int updateUser(User record);
    int deleteUserById(@Param("id") Long userId);
    int deleteUserByEmail(@Param("email") String email);
    User selectUserByToken(@Param("token") String token);


}
