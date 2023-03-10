package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository // 이 클래스가 dao라는 걸 명시해주는 어노테이션
public class UserDao {

    private JdbcTemplate jdbcTemplate; // db랑 통신하는 여러 라이브러리가 있는데.. jdbctemplate라이브러리 사용중

    @Autowired // 의존성 주입 . JdbcTemplate 라이브러리의 생성자를 의존성 주입 해줌.. /* ??? */
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from USER";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from USER where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name")),
                getUsersByEmailParams);
    }

    public GetUserRes getUser(int userId){
        String getUserQuery = "select * from USER where id = ?";
        int getUserParams = userId;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name")),
                getUserParams);
    }
    

    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into USER (name, email, password) VALUES (?,?,?)"; // ?로 된 것을 query parameter 라고 함
        Object[] createUserParams = new Object[]{postUserReq.getName(), postUserReq.getEmail(), postUserReq.getPassword()}; // query parameter에 들어갈 값 정의
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from USER where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update USER set name = ? where id = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getName(), patchUserReq.getId()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }

    public int deleteUser(int userId){
        String deleteUserNameQuery = "update USER set status = ? where id = ? ";
        Object[] deleteUserNameParams = new Object[]{"INACTIVE", userId};

        return this.jdbcTemplate.update(deleteUserNameQuery,deleteUserNameParams);
    }



}
