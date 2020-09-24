package com.example.demo.pojo;

public class ShoppingCart {
    private Integer cartId;

    private Integer stuId;

    private Integer goodId;

    private String goodImg;

    private String goodName;

    private Double goodPrice;

    private Integer goodNum;
    
    
    public ShoppingCart() {
		super();
	}
	@Override
	public String toString() {
		return "ShoppingCart [cartId=" + cartId + ", stuId=" + stuId + ", goodId=" + goodId + ", goodImg=" + goodImg
				+ ", goodName=" + goodName + ", goodPrice=" + goodPrice + ", goodNum=" + goodNum + "]";
	}

	public ShoppingCart(Integer cartId, Integer stuId, Integer goodId, String goodImg, String goodName,
			Double goodPrice, Integer goodNum) {
		super();
		this.cartId = cartId;
		this.stuId = stuId;
		this.goodId = goodId;
		this.goodImg = goodImg;
		this.goodName = goodName;
		this.goodPrice = goodPrice;
		this.goodNum = goodNum;
	}

	public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg == null ? null : goodImg.trim();
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }
}