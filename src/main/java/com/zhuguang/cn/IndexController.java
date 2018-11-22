package com.zhuguang.cn;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {
	public static final Logger logger = LoggerFactory.getLogger(App.class);
    @RequestMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	logger.info("index! lily");
    	response.sendRedirect("index.jsp");
    }
   
    
   
   
}
