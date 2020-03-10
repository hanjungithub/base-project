package com.zhizheng.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常控制类
 *
 * @author wangbo
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {
	private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception exception) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(getErrorMsg(exception));
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("异常处理出问题啦！！！！", e);
		} finally {
			exception.printStackTrace();
		}
		return modelAndView;
	}

	private String getErrorMsg(Exception e) {
		String msg = "系统异常，请联系管理员！";
		if (e instanceof BusinessException) {
			msg = e.getMessage();
		}
		if (e instanceof UnauthorizedException) {
			msg = "权限不足";
		}
		logger.error("全局异常捕获",e);
		return msg;
	}

}
