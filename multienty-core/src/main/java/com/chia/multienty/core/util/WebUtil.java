package com.chia.multienty.core.util;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.util.SpringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String writeRsp(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String writeRsp(HttpServletResponse response, Result result) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            ObjectMapper mapper = SpringUtil.getBean(ObjectMapper.class);
            response.getWriter().print(mapper.writeValueAsString(result));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
