package br.com.apiall.canalbra.model;

import java.util.List;

public class Memberstats 
{
	private Member member;
	private List<MemberGame> memberGames;
	private int page;
	
	public Memberstats() { }
	
	public Memberstats(Member member, List<MemberGame> memberGames, int page)
	{
		this.member			= member;
		this.memberGames	= memberGames;
		this.page			= page;
	}

	public int getPage(){
		return page;
	}
	
	public void setPage(int page){
		this.page = page;
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
