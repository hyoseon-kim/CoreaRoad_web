package kr.corearoad.bean.picks;

import kr.corearoad.util.CommonUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Naver on 2016-08-15.
 */
@Component
public class FoodShop extends Shop{

    private int shopOpenTime;
    private int shopCloseTime;
    private String shopMenu;

    private List<Map> shopMenuList;



    public FoodShop(){super();}

    public FoodShop(int shopNo, String shopName, String shopAddrNew, String shopAddrOld, String shopPhone, int shopThumb, String shopPostIdList, String shopMap, int shopOpenTime, int shopCloseTime, String shopMenu) {
        super(shopNo, shopName, shopAddrNew, shopAddrOld, shopPhone, shopThumb, shopPostIdList, shopMap);
        this.shopOpenTime = shopOpenTime;
        this.shopCloseTime = shopCloseTime;
        this.shopMenu = shopMenu;
    }


    public int getShopOpenTime() {
        return shopOpenTime;
    }

    public void setShopOpenTime(int shopOpenTime) {
        this.shopOpenTime = shopOpenTime;
    }

    public int getShopCloseTime() {
        return shopCloseTime;
    }

    public void setShopCloseTime(int shopCloseTime) {
        this.shopCloseTime = shopCloseTime;
    }

    public String getShopMenu() {
        return shopMenu;
    }

    public void setShopMenu(String shopMenu) {
        this.shopMenu = shopMenu;
    }


    public List<Map> getShopMenuList() {
        return shopMenuList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FoodShop{");
        sb.append("shopNo=").append(getShopNo());
        sb.append(", shopName='").append(getShopName()).append('\'');
        sb.append(", shopAddrNew='").append(getShopAddrNew()).append('\'');
        sb.append(", shopAddrOld='").append(getShopAddrOld()).append('\'');
        sb.append(", shopPhone='").append(getShopPhone()).append('\'');
        sb.append(", shopThumb=").append(getShopThumb());
        sb.append(", shopPostId='").append(getShopPostId()).append('\'');
        sb.append(", shopMap='").append(getShopMap()).append('\'');
        sb.append(", shopOpenTime=").append(shopOpenTime);
        sb.append(", shopCloseTime=").append(shopCloseTime);
        sb.append(", shopMenu='").append(shopMenu).append('\'');
        sb.append(", shopPostIdList=").append(getShopPostIdList());
        sb.append(", shopMenuList=").append(shopMenuList);
        sb.append('}');
        return sb.toString();
    }
}
