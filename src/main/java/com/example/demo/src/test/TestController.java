//package com.example.demo.src.test;
//
//import com.example.demo.common.BaseException;
//import com.example.demo.common.BaseResponse;
//import com.example.demo.src.test.model.PostMemoReq;
//import com.example.demo.src.test.model.PostMemoRes;
//import com.example.demo.src.user.model.PostUserReq;
//import com.example.demo.src.user.model.PostUserRes;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import static com.example.demo.common.BaseResponseStatus.*;
//import static com.example.demo.utils.ValidationRegex.isRegexEmail;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//    private final TestService testService; // 의존성 주입, 구현체
//
//    /**
//     * 로그 테스트 API
//     * [GET] /test/log
//     * @return String
//     */
//    @ResponseBody
//    @GetMapping("/log") // 하위 url 정의
//    public String getAll() {
//        System.out.println("test");
//        return "Success Test!!";
//    }
//
//    /**
//     * test memo 생성 API
//     * [POST] /test/memos
//     * @return BaseResponse<PostMemoRes>
//     */
//    // Body
////    @ResponseBody
////    @GetMapping("/memos")
////    public BaseResponse<String> createMemo(@RequestBody PostMemoReq memoDto) {
////        System.out.println("hi");
//////        if(memoDto.getMemo() == null){
//////            return new BaseResponse<>(POST_MEMOS_EMPTY_MEMO);
//////        }
//////        try{
//////            testService.createMemo(memoDto);
//////
////            String result = "생성 성공!!";
////            return new BaseResponse<>(result);
//////        } catch(BaseException exception){
//////            return new BaseResponse<>((exception.getStatus()));
//////        }
////    }
////    @ResponseBody
////    @PostMapping("/memos")
////    // PostUserReq라는 dto 인자 받음.클라이언트에서 바디에 데이터를 담아서 post url로 요청을 하면,,
////    // 컨트롤러의 리퀘스트 바디라는 ㅜ부분에 이 객체가 담긴다..
////    public BaseResponse<String> createMemo(@RequestBody PostMemoReq postMemoReq) {
////        System.out.println("hi");
//////        System.out.println(postMemoReq);
////        // TODO: memo와 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
////        if(postMemoReq.getMemo() == null){ // 클라이언트에서 메모를 널로 줬는지
////            return new BaseResponse<>(POST_MEMOS_EMPTY_MEMO);
////        }
////        try{
////            PostMemoRes postMemoRes = testService.createMemo(postMemoReq);
////            String result = "생성 성공!!";
////            return new BaseResponse<>(result);
////        } catch(BaseException exception){
////            return new BaseResponse<>((exception.getStatus())); // 실패한 경우의 생성자 동작
////        }
////    }
//}

package com.example.demo.src.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 로그 테스트 API
     * [GET] /test/log
     * @return String
     */
    @ResponseBody
    @GetMapping("/log")
    public String getAll() {
        System.out.println("테스트");
        return "Success Test";
    }
}
