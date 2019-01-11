package com.bit.newdeal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.newdeal.dto.Member;
import com.bit.newdeal.service.memberService;

@Controller
public class memberController {
  @Autowired
  private memberService memberService;
  
  @RequestMapping("loginForm.do")
  public void loginForm() {}
  
  @RequestMapping("joinForm.do")
  public String joinForm() {
    return "joinForm_guest";
  }
  
  @RequestMapping("joinFormEnter.do")
  public String joinFormEnter() {
    return "joinForm_enter";
  }
  
  @RequestMapping("join.do")
  public String join(Member member) {
    memberService.insertMember(member);
    
    return "redirect:main.do";
  }
  
  @RequestMapping("login.do")
  public String login(String id, String pw, HttpSession session) {
    // 로그인 처리
    session.setAttribute("sessionID", id);
    
    return "redirect:main.do";
  }
  
  @RequestMapping(value="deleteMember.do", method=RequestMethod.GET)
  public String deleteMember() {
    return "deleteMember";
  }
  
  @RequestMapping(value="deleteMember.do", method=RequestMethod.POST)
  public String deleteMember(String id, HttpSession session) {
    // 서비스, dao delete 추가해야함
    session.invalidate();
    
    return "redirect:main.do";
  }
  
  @RequestMapping("logout.do")
  public String logout(HttpSession session) {
    session.invalidate();
    
    return "redirect:main.do";
  }
  
  @RequestMapping("updateMember.do")
  public void updateMember(Member member) {
    memberService.updateMember(member);
  }
  
  @RequestMapping("enterUserMyPage.do")
  public String enterUserMyPage(String id, Model model) {
    model.addAttribute("member", memberService.selectOneMember(id));
    
    return "enterUserMyPage_update";
  }
  
  @RequestMapping("userMyPage.do")
  public String userMyPage(/*String id,*/ Model model) {
	  String id = "test@test.com";
    model.addAttribute("member", memberService.selectOneMember(id));
    
    return "userMyPage_update";
  }
  
  @RequestMapping("myPage.do")
  public String myPage() {
    /* 여기서 권한 체크 후 
     각 권한별 마이페이지로 이동
      adminForm.do - 관리자
      enterUserMyPage.do - 기업회원
      userMyPage.do - 일반회원
    */
    return null;
  }
}
