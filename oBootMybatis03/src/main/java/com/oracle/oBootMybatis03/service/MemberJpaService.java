package com.oracle.oBootMybatis03.service;


import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootMybatis03.dao.MemberJpaRepository;
import com.oracle.oBootMybatis03.domain.Member;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class MemberJpaService {
	private final MemberJpaRepository memberJpaRepository;
	
	//회원가입
	public Long join(Member member) {
		memberJpaRepository.save(member);
		return member.getId();
	}
	
	public List<Member> getListAllMember(){
		List<Member> listMember = memberJpaRepository.findAll();
		return listMember;
	}
	
	public Optional<Member> findById(Long memberId){
		Optional<Member> member = memberJpaRepository.findById(memberId);
		return member;
	}
	
	public void memberUpdate(Member member) {
		memberJpaRepository.updateByMember(member);
		return;
	}
}
