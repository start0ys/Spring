package com.oracle.oBootJpaAPI01.repository;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpaAPI01.domain.Member;

@Repository
public class JpaMemberRepository implements MemberRepository {
	
    private final EntityManager em;
    
    public JpaMemberRepository(EntityManager em) {
    	this.em = em;
    }
	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                					.getResultList();

		System.out.println("JpaMemberRepository memberList.size()->"+memberList.size());
		return memberList;
	}
	@Override
	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}
	@Override
	public Member findByMember(Long id) {
		Member member = em.find(Member.class, id);
		return member;
	}


}
