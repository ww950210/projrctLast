package com.example.demo.pojo;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Integer goodId;

    private String goodNo;

    private String goodName;

    private String goodImg;

    private String goodPrice;

    private Date goodModified;

    private String goodNote;

    private Integer goodNum;

    public Goods() {
		super();
	}
    
	@Override
	public String toString() {
		return "Goods [goodId=" + goodId + ", goodNo=" + goodNo + ", goodName=" + goodName + ", goodImg=" + goodImg
				+ ", goodPrice=" + goodPrice + ", goodModified=" + goodModified + ", goodNote=" + goodNote
				+ ", goodNum=" + goodNum + "]";
	}

	public Goods(Integer goodId, String goodNo, String goodName, String goodImg, String goodPrice, Date goodModified,
			String goodNote, Integer goodNum) {
		super();
		this.goodId = goodId;
		this.goodNo = goodNo;
		this.goodName = goodName;
		this.goodImg = goodImg;
		this.goodPrice = goodPrice;
		this.goodModified = goodModified;
		this.goodNote = goodNote;
		this.goodNum = goodNum;
	}

	public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo == null ? null : goodNo.trim();
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg == null ? null : goodImg.trim();
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice == null ? null : goodPrice.trim();
    }

    public Date getGoodModified() {
        return goodModified;
    }

    public void setGoodModified(Date goodModified) {
        this.goodModified = goodModified;
    }

    public String getGoodNote() {
        return goodNote;
    }

    public void setGoodNote(String goodNote) {
        this.goodNote = goodNote == null ? null : goodNote.trim();
    }

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }
}