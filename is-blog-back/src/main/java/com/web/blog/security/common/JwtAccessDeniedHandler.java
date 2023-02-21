package com.web.blog.security.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.web.blog.common.component.GsonUtils;
import com.web.blog.common.exception.CommonErrorCode;
import com.web.blog.common.exception.ErrorCode;
import com.web.blog.security.response.ResVO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler{
	
	// https://aljjabaegi.tistory.com/659의 5. 인증구현을 이어서할것
	
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        ErrorCode errorCode = CommonErrorCode.FORBIDDEN;
        ResVO res = ResVO.builder()
                    .status(errorCode.getResultCode().value())
                    .message(errorCode.getResultMsg()).build();
        try{
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            writer.write(GsonUtils.GSON.toJson(res));
        }catch(NullPointerException e){
            log.error("응답 메시지 작성 에러", e);
        }finally{
            if(writer != null) {
                writer.flush();
                writer.close();
            }
        }
        response.getWriter().write(GsonUtils.GSON.toJson(res));
    }

}
