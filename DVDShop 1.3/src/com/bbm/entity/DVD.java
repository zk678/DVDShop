package com.bbm.entity;

public class DVD {
	private String DVDID;
	private Integer typeId;
	private String dvdName;
	private String author;
	private String publish;

	public String getDVDID() {
		return DVDID;
	}

	public void setDVDID(String dVDID) {
		DVDID = dVDID;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDvdName() {
		return dvdName;
	}

	public void setDvdName(String dvdName) {
		this.dvdName = dvdName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}


}
