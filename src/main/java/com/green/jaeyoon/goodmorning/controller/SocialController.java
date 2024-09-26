package com.green.jaeyoon.goodmorning.controller;

import com.green.jaeyoon.goodmorning.dto.MemberDTO;
import com.green.jaeyoon.goodmorning.service.MemberService;
import com.green.jaeyoon.goodmorning.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
public class SocialController {
    private final MemberService memberService;

    @GetMapping("/api/member/kakao")
    public Map<String, Object> getMemberFromKakao(String accessToken) {
        log.info("access Token " + accessToken);
        MemberDTO memberDTO = memberService.getKakaoMember(accessToken);
        Map<String, Object> claims = memberDTO.getClaims();
        String jwtAccessToken = JWTUtil.generateToken(claims,10);
        String jwtRefreshToken = JWTUtil.generateToken(claims,60*24);
        claims.put("accessToken", jwtAccessToken);
        claims.put("refreshToken", jwtRefreshToken);
        return  claims;
//        log.info(accessToken);
//
//        memberService.getKakaoMember(accessToken);
//
//        return new String[] {"AAA", "BBB","CCC"};
    }
}
