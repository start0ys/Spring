package com.oracle.oBootMybatis03.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMybatis03.domain.Member;
import com.oracle.oBootMybatis03.service.MemberJpaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberJpaController {
	private final MemberJpaService memberJpaService;
	
	@GetMapping(value = "/memberJpa/new")
	public String createForm() {
		return "memberJpa/createMemberForm";
	}
	
	@PostMapping(value = "/memberJpa/save")
	public String create(Member member) {
		memberJpaService.join(member);
		return "memberJpa/createMemberForm";
	}
	@GetMapping(value = "/members")
	public String members(Model model) {
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("members", memberList);
		return "memberJpa/memberList";
	}
	
	@GetMapping(value = "/memberJpa/memberUpdateForm")
	public String memberUpdateForm(Long id, Model model) {
		Member member = null;
		String rtnJsp = "";
		Optional<Member> maybeMember = memberJpaService.findById(id);
		if(maybeMember.isPresent()) {
			member = maybeMember.get();
			model.addAttribute("member", member);
			rtnJsp = "memberJpa/memberModify";
		} else {
			model.addAttribute("message", "member가 존재하지 않으니, 입력부터 수행해 주세요");
			rtnJsp = "forward:/members";
		}
		return rtnJsp;
	}
	
	@GetMapping(value = "/memberJpa/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		memberJpaService.memberUpdate(member);
		return "redirect:/members";
	}
}
