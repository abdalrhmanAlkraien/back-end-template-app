package com.marinabits.energietechs.infra.util;

import com.marinabits.energietechs.infra.security.jwt.JwtTokenHelper;
import com.marinabits.energietechs.util.Constant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Web Util used to handle  some utility on http request.
 *
 * @author Omar Ismail
 * @version 1.0
 */
@Log4j2
public final class WebUtils {

    public static final String AUTH_TOKEN_HEADER = "Authorization";

    /**
     * Get request uri.
     *
     * @param request web request
     * @return request uri.
     */
    public static String getRequestUri(final WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    /**
     * Get header value from current request if any.
     *
     * @param headerName header name
     * @return header value
     */
    public static String getHeader(final String headerName) {

        final AtomicReference<String> headerValue = new AtomicReference<String>();

        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .ifPresent(requestAttributes -> {
                    headerValue.set(((ServletRequestAttributes) requestAttributes)
                            .getRequest()
                            .getHeader(headerName));
                });

        return headerValue.get();
    } //getHeader

    /**
     * Get current request url.
     *
     * @return header value
     */
    public static String getCurrentRequestUrl() {

        final AtomicReference<String> requestUrl = new AtomicReference<String>();

        Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .ifPresent(requestAttributes -> {
                    requestUrl.set(((ServletRequestAttributes) requestAttributes)
                            .getRequest()
                            .getRequestURL().toString());
                });

        return requestUrl.get();
    } //getCurrentRequestUrl

    /**
     * Resolve bearer header for the given request.
     *
     * @param request http request
     * @return Bearer authorization value if exist
     */
    public static String resolveBearerToken(final HttpServletRequest request) {

        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    } //resolveBearerToken

    /**
     * get parameters.
     *
     * @param request request
     * @return
     */
    public static String getParameters(final HttpServletRequest request) {

        StringBuffer posted = new StringBuffer();
        Enumeration<?> e = request.getParameterNames();

        if (e != null) {
            posted.append("?");
        }

        while (e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            String curr = (String) e.nextElement();
            posted.append(curr + "=");
            if (curr.contains("password")
                    || curr.contains("pass")
                    || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }

        String ip = request.getHeader("X-FORWARDED-FOR");
        String ipAddr = (ip == null) ? getRemoteAddress(request) : ip;
        if (ipAddr != null && !ipAddr.equals("")) {
            posted.append("&_psip=" + ipAddr);
        }

        return posted.toString();
    } //getParameters

    /**
     * get Remote Address.
     *
     * @param request request
     * @return
     */
    public static String getRemoteAddress(final HttpServletRequest request) {

        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");

        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    } //getRemoteAddress

    public static Map<String,Object> getAuthorizationHeader(){

        String token =  getHeader(AUTH_TOKEN_HEADER);
        Map<String,Object> userInfo = new HashMap<>();

        userInfo.put(Constant.USER_NAME_MAP_KEY,JwtTokenHelper.getUsernameFrom(token));
        userInfo.put(Constant.USER_ID_MAP_KEY,JwtTokenHelper.getUserIdFrom(token));
        return userInfo;
    }

} //class
