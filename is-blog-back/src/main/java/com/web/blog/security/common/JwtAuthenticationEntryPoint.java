package com.web.blog.security.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.web.blog.common.component.GsonUtils;
import com.web.blog.common.exception.CommonErrorCode;
import com.web.blog.common.exception.ErrorCode;
import com.web.blog.security.vo.ResVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        ErrorCode errorCode = CommonErrorCode.UNAUTHORIZED;
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
