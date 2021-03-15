package com.recommender.app.controller.params;

import java.util.List;

import com.recommender.app.model.Questionary;
import com.recommender.app.model.RecommenderUser;

public class DashboardDTO {
	private boolean successSearch;
	private boolean successUpdate;
	private boolean searchedIsFavorite;
	private boolean searched;
	private String foundUserName;
	private String currentUserName;
	List<RecommenderUser> favorites;
	Questionary currentUserQuestionary;
	Questionary searchedQuestionary;
	
	public Questionary getSearchedQuestionary() {
		return searchedQuestionary;
	}
	public void setSearchedQuestionary(Questionary searchedQuestionary) {
		this.searchedQuestionary = searchedQuestionary;
	}
	public boolean isSuccessSearch() {
		return successSearch;
	}
	public void setSuccessSearch(boolean successSearch) {
		this.successSearch = successSearch;
	}
	public boolean isSuccessUpdate() {
		return successUpdate;
	}
	public void setSuccessUpdate(boolean successUpdate) {
		this.successUpdate = successUpdate;
	}
	public boolean isSearchedIsFavorite() {
		return searchedIsFavorite;
	}
	public void setSearchedIsFavorite(boolean searchedIsFavorite) {
		this.searchedIsFavorite = searchedIsFavorite;
	}
	public boolean isSearched() {
		return searched;
	}
	public void setSearched(boolean searched) {
		this.searched = searched;
	}
	public String getFoundUserName() {
		return foundUserName;
	}
	public void setFoundUserName(String foundUserName) {
		this.foundUserName = foundUserName;
	}
	public String getCurrentUserName() {
		return currentUserName;
	}
	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}
	public List<RecommenderUser> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<RecommenderUser> favorites) {
		this.favorites = favorites;
	}
	public Questionary getCurrentUserQuestionary() {
		return currentUserQuestionary;
	}
	public void setCurrentUserQuestionary(Questionary currentUserQuestionary) {
		this.currentUserQuestionary = currentUserQuestionary;
	}
	
}
