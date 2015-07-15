package br.com.apiall.canalbra.model;

import java.util.List;

public class Memberstats 
{
	private Member member;
	private List<MemberGame> memberGames;
	
	public Memberstats() { }
	
	public Memberstats(Member member, List<MemberGame> memberGames)
	{
		this.member			= member;
		this.memberGames	= memberGames;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<MemberGame> getMemberGames() {
		return memberGames;
	}

	public void setMemberGames(List<MemberGame> memberGames) {
		this.memberGames = memberGames;
	}
	
	
}
