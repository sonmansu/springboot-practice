package com.example.demo.src.test;

import com.example.demo.common.BaseException;
import com.example.demo.src.test.model.PostMemoReq;
import com.example.demo.src.test.model.PostMemoRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor //di 의존성 주입
@Service // 스프링 컨테이너에 서비스라는걸 인지시킴
public class TestService {

    private final TestDao testDao; // 의존성 주입으로 dao 들어감

    //POST
    public PostMemoRes createMemo(PostMemoReq postMemoReq) throws BaseException {
        //논리적인 예외처리)
        if(checkMemo(postMemoReq.getMemo()) == 1) {
            throw new BaseException(POST_USERS_EXISTS_MEMO);
        }
        try{
            int memoId = testDao.createMemo(postMemoReq); // POINT
            return new PostMemoRes(memoId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkMemo(String memo) throws BaseException {
        try {
            return testDao.checkMemo(memo);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
