package com.sbs.kakao.domain.member.controller;

import com.sbs.kakao.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String showLogin(){
        return "user/member/lgoin";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public String showMe(){
        return "/user/member/me";
    }
}
