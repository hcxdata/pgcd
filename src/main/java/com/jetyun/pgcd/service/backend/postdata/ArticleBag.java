package com.jetyun.pgcd.service.backend.postdata;

import java.util.List;

public class ArticleBag {

	private int articleNum;
	
	private List<Article> articles;

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	
}
