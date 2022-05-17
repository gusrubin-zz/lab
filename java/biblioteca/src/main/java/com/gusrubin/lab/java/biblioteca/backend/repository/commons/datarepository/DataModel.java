package com.gusrubin.lab.java.biblioteca.backend.repository.commons.datarepository;

import java.util.List;

public class DataModel<T extends EntityModel> {
	
	Integer idIndex;
	List<T> entityList;
	
	protected DataModel() {
		if (this.idIndex == null) {
			this.idIndex = 0;
		}
	}
	
	public Integer nextId() {
		setIdIndex(idIndex + 1);
		return idIndex;
	}

	public Integer getIdIndex() {
		return idIndex;
	}
	
	public void setIdIndex(Integer nextId) {
		this.idIndex = nextId;
	}
	
	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}
}
