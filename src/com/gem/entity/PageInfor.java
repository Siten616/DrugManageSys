package com.gem.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageInfor<T> {
	private List<T> list;
	//当前页
	private int curPage;
	//每页显示的条数
	private int pageNum;
	//总页数
	private int lastPage;
	//总条数(总页数=总条数/每页显示的条数)
	private int totalNum;
	//上一页
	private int prePage;
	//下一页
	private int nextPage;
	//列表
	private List<Integer> pageList = new ArrayList<Integer>();

	public PageInfor(List<T> list, int curPage, int pageNum, int totalNum) {
		super();
		this.list = list;
		this.curPage = curPage;
		this.pageNum = pageNum;
		this.totalNum = totalNum;
		this.lastPage = totalNum % pageNum != 0 ? totalNum / pageNum + 1 : totalNum / pageNum;
		this.prePage = curPage <= 1 ? curPage : curPage - 1;
		this.nextPage = curPage == lastPage ? curPage : curPage + 1;
		//页面上最多显示5个
		if (lastPage <= 5) {
			for (int i = 1; i <= lastPage; i++) {
				pageList.add(i);
			}
		} else if (curPage <= 2) {
			//总页数大于5
			for (int i = 1; i <= 5; i++) {
				pageList.add(i);
			}
		} else if (curPage >= lastPage - 2) {
			for (int i = lastPage - 4; i <= lastPage; i++) {
				pageList.add(i);
			}
		} else {
			for (int i = curPage - 2; i <= curPage + 2; i++) {
				pageList.add(i);
			}
		}
	}

}
