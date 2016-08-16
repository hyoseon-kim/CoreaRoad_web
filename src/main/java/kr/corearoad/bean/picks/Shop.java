package kr.corearoad.bean.picks;

import kr.corearoad.util.CommonUtils;

import java.util.List;

/**
 * Created by Naver on 2016-08-15.
 */
public class Shop {

    private int shopNo;
    private String shopName;
    private String shopAddrNew;
    private String shopAddrOld;
    private String shopPhone;
    private int shopThumb;
    private String shopPostId;
    private String shopMap;

    private List shopPostIdList;

    public Shop(){}

    public Shop(int shopNo, String shopName, String shopAddrNew, String shopAddrOld, String shopPhone, int shopThumb, String shopPostId, String shopMap) {
        this.shopNo = shopNo;
        this.shopName = shopName;
        this.shopAddrNew = shopAddrNew;
        this.shopAddrOld = shopAddrOld;
        this.shopPhone = shopPhone;
        this.shopThumb = shopThumb;
        this.shopPostId = shopPostId;
        this.shopMap = shopMap;
    }

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddrNew() {
        return shopAddrNew;
    }

    public void setShopAddrNew(String shopAddrNew) {
        this.shopAddrNew = shopAddrNew;
    }

    public String getShopAddrOld() {
        return shopAddrOld;
    }

    public void setShopAddrOld(String shopAddrOld) {
        this.shopAddrOld = shopAddrOld;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public int getShopThumb() {
        return shopThumb;
    }

    public void setShopThumb(int shopThumb) {
        this.shopThumb = shopThumb;
    }

    public String getShopPostId() {
        return shopPostId;
    }

    public void setShopPostId(String shopPostId) {
        this.shopPostId = shopPostId;
    }

    public String getShopMap() {
        return shopMap;
    }

    public void setShopMap(String shopMap) {
        this.shopMap = shopMap;
    }


    public List getShopPostIdList() {
        return shopPostIdList;
    }

    public void setShopPostIdList() {
        this.shopPostIdList = CommonUtils.returnStringArrayFromString(getShopPostId());
    }
}
