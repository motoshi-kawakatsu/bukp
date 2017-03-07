package jp.co.broadleaf.breg.totalinfo.facade.dto;

import java.util.List;

/**
*
* 累積情報のDtoのクラス。
*/
public class TotalinfoDto {

    /**
     * 商品情報
     */
    private List<GoodsTotalinfoDto> goodsTotalinfoDtoList;

    /**
     * 商品検索数
     */
    private int goodsSearchCount;

    /**
     * 商品全件数
     */
    private int goodsAllCount;

    /**
     * セット情報()
     */
    private List<SetTotalinfoDto> setTotalinfoDtoList;

    /**
     * セット検索数
     */
    private int setSearchCount;

    /**
     * セット全件数
     */
    private int setAllCount;

    /**
     * 結合情報()
     */
    private List<JoinTotalinfoDto> joinTotalinfoDtoList;

    /**
     * 結合検索数
     */
    private int joinSearchCount;

    /**
     * 結合全件数
     */
    private int joinAllCount;


    /**
     * 商品情報の取得
     *
     * @return goodsTotalinfoDtoList 商品情報
     */
    public List<GoodsTotalinfoDto> getGoodsTotalinfoDtoList() {
        return goodsTotalinfoDtoList;
    }

    /**
     * 商品検索数の取得
     *
     * @return int 商品検索数
     */
    public int getGoodsSearchCount() {
        return goodsSearchCount;
    }

    /**
     * 商品全件数の取得
     *
     * @return int 商品全件数
     */
    public int getGoodsAllCount() {
        return goodsAllCount;
    }

    /**
     * セット情報の取得
     *
     * @return setTotalinfoDtoList セット情報
     */
    public List<SetTotalinfoDto> getSetTotalinfoDtoList() {
        return setTotalinfoDtoList;
    }

    /**
     * セット検索数の取得
     *
     * @return int セット検索数
     */
    public int getSetSearchCount() {
        return setSearchCount;
    }

    /**
     * セット全件数の取得
     *
     * @return int セット全件数
     */
    public int getSetAllCount() {
        return setAllCount;
    }

    /**
     * 結合情報の取得
     *
     * @return joinTotalinfoDtoList 結合情報
     */
    public List<JoinTotalinfoDto> getJoinTotalinfoDtoList() {
        return joinTotalinfoDtoList;
    }

    /**
     * 結合検索数の取得
     *
     * @return int 結合検索数
     */
    public int getJoinSearchCount() {
        return joinSearchCount;
    }

    /**
     * 結合全件数の取得
     *
     * @return int 結合全件数
     */
    public int getJoinAllCount() {
        return joinAllCount;
    }

    /**
     * 商品情報の設定
     *
     * @param newGoodsTotalinfoDtoList 商品情報()
     */
    public void setGoodsTotalinfoDtoList(List<GoodsTotalinfoDto> newGoodsTotalinfoDtoList) {
        this.goodsTotalinfoDtoList = newGoodsTotalinfoDtoList;
    }

    /**
     * 商品検索数の設定
     *
     * @param newGoodsSearchCount 商品検索数
     */
    public void setGoodsSearchCount(int newGoodsSearchCount) {
        this.goodsSearchCount = newGoodsSearchCount;
    }

    /**
     * 商品全件数の設定
     *
     * @param newGoodsAllCount 商品全件数
     */
    public void setGoodsAllCount(int newGoodsAllCount) {
        this.goodsAllCount = newGoodsAllCount;
    }

    /**
     * セット情報の設定
     *
     * @param newSetTotalinfoDtoList セット情報()
     */
    public void setSetTotalinfoDtoList(List<SetTotalinfoDto> newSetTotalinfoDtoList) {
        this.setTotalinfoDtoList = newSetTotalinfoDtoList;
    }

    /**
     * セット検索数の設定
     *
     * @param newSetSearchCount セット検索数
     */
    public void setSetSearchCount(int newSetSearchCount) {
        this.setSearchCount = newSetSearchCount;
    }

    /**
     * セット全件数の設定
     *
     * @param newSetAllCount セット全件数
     */
    public void setSetAllCount(int newSetAllCount) {
        this.setAllCount = newSetAllCount;
    }

    /**
     * 結合情報の設定
     *
     * @param newJoinTotalinfoDtoList 結合情報()
     */
    public void setJoinTotalinfoDtoList(List<JoinTotalinfoDto> newJoinTotalinfoDtoList) {
        this.joinTotalinfoDtoList = newJoinTotalinfoDtoList;
    }

    /**
     * 結合検索数の設定
     *
     * @param newJoinSearchCount 結合検索数
     */
    public void setJoinSearchCount(int newJoinSearchCount) {
        this.joinSearchCount = newJoinSearchCount;
    }

    /**
     * 結合全件数の設定
     *
     * @param newJoinAllCount 結合全件数
     */
    public void setJoinAllCount(int newJoinAllCount) {
        this.joinAllCount = newJoinAllCount;
    }
}

