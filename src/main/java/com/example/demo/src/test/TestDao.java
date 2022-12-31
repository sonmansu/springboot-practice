package com.example.demo.src.test;

import com.example.demo.src.test.model.PostMemoReq;
import com.example.demo.src.user.model.PostUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Repository // 이 클래스가 dao라는 걸 명시해주는 어노테이션
public class TestDao {
    private JdbcTemplate jdbcTemplate; // db랑 통신하는 여러 라이브러리가 있는데.. jdbctemplate라이브러리 사용중

    public int createMemo(PostMemoReq postMemoReq){
        String createMemoQuery = "insert into MEMO (memo) VALUES (?)"; // ?로 된 것을 query parameter
        // 라고 함
        Object[] createMemoParams = new Object[]{postMemoReq.getMemo()}; // query parameter에 들어갈 값 정의
        this.jdbcTemplate.update(createMemoQuery, createMemoParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkMemo(String memo) {
        String checkMemoQuery = "select exists(select memo from MEMO where memo = ? and state = 'ACTIVE')";
        String checkMemoParams = memo;
        return this.jdbcTemplate.queryForObject(checkMemoQuery, int.class, checkMemoParams);
    }
}
