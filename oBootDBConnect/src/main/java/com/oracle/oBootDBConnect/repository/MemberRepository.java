package com.oracle.oBootDBConnect.repository;

import java.util.List;

import com.oracle.oBootDBConnect.domain.Member;

public interface MemberRepository {
	Member       save(Member member);
	List<Member> findAll();
}
