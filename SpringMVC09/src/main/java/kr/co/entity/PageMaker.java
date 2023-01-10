package kr.co.entity;

import lombok.Data;

//페이징 처리를 만드는 클래스(vo)
@Data
public class PageMaker {
	private Criteria cri;
	private int totalCount; //총게시글의 수
	private int startPage; // 시작 페이지 번호
	private int endPage; // 마지막 페이지 번호
	private boolean prev; // 이전버튼(true, false)
	private boolean next; // 다음버튼(true, false)
	private int displayPageNum=3; // 1 2 3 4 5 6 7 8 9 10
	// 총 게시글의 수를 구하는 메서드
	public void setTotalCount(int totalCount) {
		this.totalCount=totalCount;
		makePaging(); //총 게시글의 수가 들어가면 makePaging 함수가 실행되어서 계산
	}
	private void makePaging() {
		// 1. 화면에 보여질 마지막 페이지 번호를 구한다.
		endPage = (int) (Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum); // 7/10.0=0.7 >>>>> 1
		// 2. 화면에 보여질 시작 페이지 번호를 구한다.
		startPage = (endPage-displayPageNum)+1;	// 10-10+1= 1
		if(startPage<=0) startPage=1;
		// 3. 전체 마지막 페이지 계산한다.
		int tempEndPage=(int) Math.ceil(totalCount/(double)cri.getPerPageNum()); // 88/10.0 = 9 (9가 마지막페이지)
		// 4.화면에 보여질 마지막 페이지 유효성 체크
		if(tempEndPage<endPage) {
			endPage=tempEndPage;
		}
		// 5. 이전페이지 버튼(링크) 존재 여부
		prev=(startPage==1) ? false : true;
		// 6. 다음페이지 버튼(링크) 존재 여부
		next=(endPage<tempEndPage) ? true : false;
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
