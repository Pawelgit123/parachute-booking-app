package com.parachute.booking.security;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

public class SecurityConfiguration {

    @GetMapping("/counter")
    Map<Object, Object> getCounter(HttpServletRequest request, HttpServletResponse response) {

        int counter = 0;

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            counter = 1;
            Cookie cookie = new Cookie("counter", String.valueOf(counter));
            response.addCookie(cookie);
            return Map.of("counter", counter);
        }

        Cookie cookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("counter"))
                .findFirst()
                .orElseGet(() -> new Cookie("counter", "0"));

        String stringvalue = cookie.getValue();
        counter = Integer.parseInt(stringvalue);
        counter++;
        cookie.setValue(String.valueOf(counter));
        cookie.setMaxAge(1800);
        response.addCookie(cookie);
        return Map.of("counter", counter);
    }

    @GetMapping("/counter/session")
    Map<Object, Object> getSessionCounter(HttpSession httpSession) {
        Object counter = httpSession.getAttribute("counter");

        if (counter == null) {
            httpSession.setAttribute("counter", 1);
            return Map.of("counter", 1);
        }
        int counterInt = (int) counter;
        counterInt++;
        httpSession.setAttribute("counter", counterInt);

        return Map.of("counter", counterInt);
    }
}
