package com.oracle.oBootDBConnect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootDBConnect.repository.JdbcTemplateMemberRepository;
import com.oracle.oBootDBConnect.repository.MemberRepository;
import com.oracle.oBootDBConnect.repository.MemoryMemberRepository;
import com.oracle.oBootDBConnect.service.MemberService;

@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	@Bean
	public MemberRepository memberRepository() {
		return new JdbcTemplateMemberRepository(dataSource);
//		return new MemoryMemberRepository();
	}
}
