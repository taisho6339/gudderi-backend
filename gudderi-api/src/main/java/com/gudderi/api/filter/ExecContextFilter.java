package com.gudderi.api.filter;

import com.gudderi.api.component.context.ExecContext;
import com.gudderi.api.component.context.ExecContextHolder;
import com.gudderi.api.component.context.HolderStrategy;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ExecContextFilter implements Filter {

    private String appId;
    /**
     * proc idの最大文字列長。
     * (proc idを格納するDB側のカラムに影響)
     */
    private static final int PROCID_MAX_LENGTH = 255;
    private final int funcIdMaxLength;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public ExecContextFilter(String appId, HolderStrategy strategy) {
        this.appId = appId;
        this.funcIdMaxLength = PROCID_MAX_LENGTH - appId.length() - 1;
        ExecContextHolder.setStrategy(strategy);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String funcId = format(req.getRequestURI(), funcIdMaxLength);
            ExecContext execContext = ExecContextHolder.get();
            execContext.setProcId(appId + ":" + funcId);
            chain.doFilter(request, response);
        } finally {
            ExecContextHolder.clear();
        }
    }

    static String format(String s, int max) {
        if (s == null) {
            return "";
        }
        int len = s.length();
        if (len > max) {
            return "~" + s.substring(len - max + 1);
        } else {
            return s;
        }
    }
}
