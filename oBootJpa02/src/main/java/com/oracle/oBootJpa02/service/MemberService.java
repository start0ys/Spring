package com.oracle.oBootJpa02.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.repository.MemberRepository;
//JPA  --> 서비스 계층에 트랜잭션 추가
//스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고,
//메서드가 정상 종료되면 트랜잭션을 커밋. 만약 런타임 예외가 발생하면 롤백.
//JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행

@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// 회원가입 
	public Member join(Member member) {
		System.out.println("MemberService join member.getName()->"+member.getName());
		memberRepository.save(member);
		return member;
	}
	
    // 전체회원 조회
	public List<Member> getListAllMember() {
		List<Member> listMember  = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()->"+listMember.size());
		return listMember;
	}
	
	// 회원 상세 조회
	public Optional<Member> findByMember(Long memberId) {
		Optional<Member> member = memberRepository.findByMember(memberId);
		System.out.println("MemberService findByMember member.get().getId()->"+member.get().getId());
		System.out.println("MemberService findByMember member.get().getName()->"+member.get().getName());
		System.out.println("MemberService findByMember member.get().getTeam().getName()->"
		                    +member.get().getTeam().getName());
		return member;
	}

	// 회원수정
	public void memberUpdate(Member member) {
		System.out.println("MemberService memberUpdate member.getName()->"+member.getName());
		System.out.println("MemberService memberUpdate member.getTeamname()->"+member.getTeamname());
		memberRepository.updateByMember(member);
		return;
	}

	// 이름에 해당하는  회원 조회
	public List<Member> getListSearchMember(String searchName) {
		System.out.println("MemberService getListSearchMember Start...");
		// String pSearchName = searchName + '%';
		System.out.println("MemberService getListSearchMember searchName->"+searchName);
		List<Member> listMember  = memberRepository.findByNames(searchName);
		System.out.println("MemberService getListSearchMember listMember.size()->"+listMember.size());
		return listMember;
	}
	
	
	
	

}
