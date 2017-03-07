//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                       作成担当 : chenbei
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodsdetail.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 商品マスタ(メーカー)のDtoのクラス。
 */
public class GoodsDetailDto {
	/**
	 * 処理区分
	 */
	private String dealDistinction;
	/**
	 * セレクトコード
	 */
	private String selectCd;

	/**
	 * 分類コード
	 */
	private String classifyCd;

	/**
	 * BLコード
	 */
	private String blCd;

	/**
	 * 優良品番
	 */
	private String exceProNo;

	/**
	 * 品名(半角)
	 */
	private String proNameHalf;

	/**
	 * 品名(全角)
	 */
	private String proNameFull;

	/**
	 * 価格(税抜)
	 */
	private String price;

	/**
	 * OPENプライス
	 */
	private String openPrice;

	/**
	 * JAN
	 */
	private String jan;

	/**
	 * 層別
	 */
	private String classify;

	/**
	 * 装備
	 */
	private String equipment;

	/**
	 * 規格/特記
	 */
	private String specialMatter;

	/**
	 * 規格/特記(一般)
	 */
	private String specialMatterCommon;

	/**
	 * 商品詳細
	 */
	private String productDetail;

	/**
	 * 商品詳細(一般)
	 */
	private String productDetailCommon;

	/**
	 * URL1
	 */
	private String url1;

	/**
	 * URL2
	 */
	private String url2;

	/**
	 * URL3
	 */
	private String url3;

	/**
	 * 商品サイズ（長さ）
	 */
	private String proLenA;

	/**
	 * 商品サイズ（幅）
	 */
	private String proLenB;

	/**
	 * 商品サイズ（高さ）
	 */
	private String proLenC;

	/**
	 * 梱包（長さ）
	 */
	private String packLenA;

	/**
	 * 梱包（幅）
	 */
	private String packLenB;

	/**
	 * 梱包（高さ）
	 */
	private String packLenC;

	/**
	 * 梱包単位
	 */
	private String lengthUnit;

	/**
	 * 重量
	 */
	private String weight;

	/**
	 * 重量単位
	 */
	private String weightUnit;

	/**
	 * 作成日時
	 */
	private String setDate;

	/**
	 * 更新日時
	 */
	private String updateDate;

	/**
	 * 適用日時
	 */
	private String applyDate;

	/**
	 * エラー区分
	 */
	private String errorDistinction;

	/**
	 * 削除依頼区分
	 */
	private String delDistinction;

	/**
	 * 削除理由
	 */
	private String delReason;

	/**
	 * 申請状態
	 */
	private String applyState;

	/**
	 * 処理区分の取得。
	 *
	 * @return String 処理区分
	 */
	public String getSelectCd() {
		return selectCd;
	}

	/**
	 * 処理区分の設定。
	 *
	 * @param dealDistinction 処理区分
	 */
	public void setDealDistinction(String dealDistinction) {
		this.dealDistinction = dealDistinction;
	}
	
	/**
	 * セレクトコードの取得。
	 *
	 * @return String セレクトコード
	 */
	public String getDealDistinction() {
		return dealDistinction;
	}

	/**
	 * セレクトコードの設定。
	 *
	 * @param selectCd セレクトコード
	 */
	public void setSelectCd(String selectCd) {
		this.selectCd = selectCd;
	}

	/**
	 * 分類コードの取得。
	 *
	 * @return String 分類コード
	 */
	public String getClassifyCd() {
		return classifyCd;
	}

	/**
	 * 分類コードの設定。
	 *
	 * @param classifyCd 分類コード
	 */
	public void setClassifyCd(String classifyCd) {
		this.classifyCd = classifyCd;
	}

	/**
	 * BLコードの取得。
	 *
	 * @return String BLコード
	 */
	public String getBlCd() {
		return blCd;
	}

	/**
	 * BLコードの設定。
	 *
	 * @param blCd BLコード
	 */
	public void setBlCd(String blCd) {
		this.blCd = blCd;
	}

	/**
	 * 優良品番の取得。
	 *
	 * @return String 優良品番
	 */
	public String getExceProNo() {
		return exceProNo;
	}

	/**
	 * 優良品番の設定。
	 *
	 * @param exceProNo 優良品番
	 */
	public void setExceProNo(String exceProNo) {
		this.exceProNo = exceProNo;
	}

	/**
	 * 品名(半角)の取得。
	 *
	 * @return String 品名(半角)
	 */
	public String getProNameHalf() {
		return proNameHalf;
	}

	/**
	 * 品名(半角)の設定。
	 *
	 * @param proNameHalf 品名(半角)
	 */
	public void setProNameHalf(String proNameHalf) {
		this.proNameHalf = proNameHalf;
	}

	/**
	 * 品名(全角)の取得。
	 *
	 * @return String 品名(全角)
	 */
	public String getProNameFull() {
		return proNameFull;
	}

	/**
	 * 品名(全角)の設定。
	 *
	 * @param proNameFull 品名(全角)
	 */
	public void setProNameFull(String proNameFull) {
		this.proNameFull = proNameFull;
	}

	/**
	 * 価格(税抜)の取得。
	 *
	 * @return String 価格(税抜)
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * 価格(税抜)の設定。
	 *
	 * @param price 価格(税抜)
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * OPENプランスの取得。
	 *
	 * @return String OPENプランス
	 */
	public String getOpenPrice() {
		return openPrice;
	}

	/**
	 * OPENプランスの設定。
	 *
	 * @param openPrice OPENプランス
	 */
	public void setOpenPrice(String openPrice) {
		this.openPrice = openPrice;
	}

	/**
	 * JANの取得。
	 *
	 * @return String JAN
	 */
	public String getJan() {
		return jan;
	}

	/**
	 * JANの設定。
	 *
	 * @param jan JAN
	 */
	public void setJan(String jan) {
		this.jan = jan;
	}

	/**
	 * 層別の取得。
	 *
	 * @return String 層別
	 */
	public String getClassify() {
		return classify;
	}

	/**
	 * 層別の設定。
	 *
	 * @param classify 層別
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}

	/**
	 * 装備の取得。
	 *
	 * @return String 装備
	 */
	public String getEquipment() {
		return equipment;
	}

	/**
	 * 装備の設定。
	 *
	 * @param equipment 装備
	 */
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	/**
	 * 規格/特記の取得。
	 *
	 * @return String 規格/特記
	 */
	public String getSpecialMatter() {
		return specialMatter;
	}

	/**
	 * 規格/特記の設定。
	 *
	 * @param specialMatter 規格/特記
	 */
	public void setSpecialMatter(String specialMatter) {
		this.specialMatter = specialMatter;
	}

	/**
	 * 規格/特記（一般）の取得。
	 *
	 * @return String 規格/特記（一般）
	 */
	public String getSpecialMatterCommon() {
		return specialMatterCommon;
	}

	/**
	 * 規格/特記（一般）の設定。
	 *
	 * @param specialMatterCommon 規格/特記（一般）
	 */
	public void setSpecialMatterCommon(String specialMatterCommon) {
		this.specialMatterCommon = specialMatterCommon;
	}

	/**
	 * 商品詳細の取得。
	 *
	 * @return String 商品詳細
	 */
	public String getProductDetail() {
		return productDetail;
	}

	/**
	 * 商品詳細の設定。
	 *
	 * @param productDetail 商品詳細
	 */
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	/**
	 * 商品詳細（一般）の取得。
	 *
	 * @return String 商品詳細（一般）
	 */
	public String getProductDetailCommon() {
		return productDetailCommon;
	}

	/**
	 * 商品詳細（一般）の設定。
	 *
	 * @param productDetailCommon 商品詳細（一般）
	 */
	public void setProductDetailCommon(String productDetailCommon) {
		this.productDetailCommon = productDetailCommon;
	}

	/**
	 * URL1の取得。
	 *
	 * @return String URL1
	 */
	public String getUrl1() {
		return url1;
	}

	/**
	 * URL1の設定。
	 *
	 * @param url1 URL1
	 */
	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	/**
	 * URL2の取得。
	 *
	 * @return String URL2
	 */
	public String getUrl2() {
		return url2;
	}

	/**
	 * URL2の設定。
	 *
	 * @param url2 URL2
	 */
	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	/**
	 * URL3の取得。
	 *
	 * @return String URL3
	 */
	public String getUrl3() {
		return url3;
	}

	/**
	 * URL3の設定。
	 *
	 * @param url3 URL3
	 */
	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	/**
	 * 商品サイズ（長さ）の取得。
	 *
	 * @return String 商品サイズ（長さ）
	 */
	public String getProLenA() {
		return proLenA;
	}

	/**
	 * 商品サイズ（長さ）の設定。
	 *
	 * @param proLenA 商品サイズ（長さ）
	 */
	public void setProLenA(String proLenA) {
		this.proLenA = proLenA;
	}

	/**
	 * 商品サイズ（幅）の取得。
	 *
	 * @return String 商品サイズ（幅）
	 */
	public String getProLenB() {
		return proLenB;
	}

	/**
	 * 商品サイズ（幅）の設定。
	 *
	 * @param proLenB 商品サイズ（幅）
	 */
	public void setProLenB(String proLenB) {
		this.proLenB = proLenB;
	}

	/**
	 * 商品サイズ（高さ）の取得。
	 *
	 * @return String 商品サイズ（高さ）
	 */
	public String getProLenC() {
		return proLenC;
	}

	/**
	 * 商品サイズ（高さ）の設定。
	 *
	 * @param proLenC 商品サイズ（高さ）
	 */
	public void setProLenC(String proLenC) {
		this.proLenC = proLenC;
	}

	/**
	 * 梱包（長さ）の取得。
	 *
	 * @return String 梱包（長さ）
	 */
	public String getPackLenA() {
		return packLenA;
	}

	/**
	 * 梱包（長さ）の設定。
	 *
	 * @param packLenA 梱包（長さ）
	 */
	public void setPackLenA(String packLenA) {
		this.packLenA = packLenA;
	}

	/**
	 * 梱包（幅）の取得。
	 *
	 * @return String 梱包（幅）
	 */
	public String getPackLenB() {
		return packLenB;
	}

	/**
	 * 梱包（幅）の設定。
	 *
	 * @param packLenB 梱包（幅）
	 */
	public void setPackLenB(String packLenB) {
		this.packLenB = packLenB;
	}

	/**
	 * 梱包（高さ）の取得。
	 *
	 * @return String 梱包（高さ）
	 */
	public String getPackLenC() {
		return packLenC;
	}

	/**
	 * 梱包（高さ）の設定。
	 *
	 * @param packLenC 梱包（高さ）
	 */
	public void setPackLenC(String packLenC) {
		this.packLenC = packLenC;
	}

	/**
	 * 梱包単位の取得。
	 *
	 * @return String 梱包単位
	 */
	public String getLengthUnit() {
		return lengthUnit;
	}

	/**
	 * 梱包単位の設定。
	 *
	 * @param lengthUnit 梱包単位
	 */
	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}

	/**
	 * 重量の取得。
	 *
	 * @return String 重量
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * 重量の設定。
	 *
	 * @param weight 重量
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * 重量単位の取得。
	 *
	 * @return String 重量単位
	 */
	public String getWeightUnit() {
		return weightUnit;
	}

	/**
	 * 重量単位の設定。
	 *
	 * @param weightUnit 重量単位
	 */
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	/**
	 * 作成日時の取得。
	 *
	 * @return String 作成日時
	 */
	public String getSetDate() {
		return setDate;
	}

	/**
	 * 作成日時の設定。
	 *
	 * @param setDate 作成日時
	 */
	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}

	/**
	 * 更新日時の取得。
	 *
	 * @return String 更新日時
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日時の設定。
	 *
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 適用日時の取得。
	 *
	 * @return String 適用日時
	 */
	public String getApplyDate() {
		return applyDate;
	}

	/**
	 * 適用日時の設定。
	 *
	 * @param applyDate 適用日時
	 */
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	/**
	 * エラー区分の取得。
	 *
	 * @return String エラー区分
	 */
	public String getErrorDistinction() {
		return errorDistinction;
	}

	/**
	 * エラー区分の設定。
	 *
	 * @param errorDistinction エラー区分
	 */
	public void setErrorDistinction(String errorDistinction) {
		this.errorDistinction = errorDistinction;
	}

	/**
	 * 削除依頼区分の取得。
	 *
	 * @return String 削除依頼区分
	 */
	public String getDelDistinction() {
		return delDistinction;
	}

	/**
	 * 削除依頼区分の設定。
	 *
	 * @param delDistinction 削除依頼区分
	 */
	public void setDelDistinction(String delDistinction) {
		this.delDistinction = delDistinction;
	}

	/**
	 * 削除理由の取得。
	 *
	 * @return String 削除理由
	 */
	public String getDelReason() {
		return delReason;
	}

	/**
	 * 削除理由の設定。
	 *
	 * @param delReason 削除理由
	 */
	public void setDelReason(String delReason) {
		this.delReason = delReason;
	}

	/**
	 * 申請状態の取得。
	 *
	 * @return String 申請状態
	 */
	public String getApplyState() {
		return applyState;
	}

	/**
	 * 申請状態の設定。
	 *
	 * @param applyState 申請状態
	 */
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	/**
	 * ハッシュコードを設定する。
	 *
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(selectCd).append(classifyCd).append(blCd).append(exceProNo)
				.append(proNameHalf).append(proNameFull).append(price).append(openPrice).append(jan).append(classify)
				.append(equipment).append(specialMatter).append(specialMatterCommon).append(productDetail)
				.append(productDetailCommon).append(url1).append(url2).append(url3).append(proLenA).append(proLenB)
				.append(proLenC).append(packLenA).append(packLenB).append(packLenC).append(lengthUnit).append(weight)
				.append(weightUnit).append(setDate).append(updateDate).append(applyDate).append(errorDistinction)
				.append(delDistinction).append(delReason).append(applyState).toHashCode();
	}

	/**
	 * equalsメソッド
	 * 
	 * @param obj オブジェクト
	 * @return 比較結果
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		GoodsDetailDto other = (GoodsDetailDto) obj;
		return new EqualsBuilder().append(selectCd, other.selectCd).append(classifyCd, other.classifyCd)
				.append(blCd, other.blCd).append(exceProNo, other.exceProNo).append(proNameHalf, other.proNameHalf)
				.append(proNameFull, other.proNameFull).append(price, other.price).append(openPrice, other.openPrice)
				.append(jan, other.jan).append(classify, other.classify).append(equipment, other.equipment)
				.append(specialMatter, other.specialMatter).append(specialMatterCommon, other.specialMatterCommon)
				.append(productDetail, other.productDetail).append(productDetailCommon, other.productDetailCommon)
				.append(url1, other.url1).append(url2, other.url2).append(url3, other.url3)
				.append(proLenA, other.proLenA).append(proLenB, other.proLenB).append(proLenC, other.proLenC)
				.append(packLenA, other.packLenA).append(packLenB, other.packLenB).append(packLenC, other.packLenC)
				.append(lengthUnit, other.lengthUnit).append(weight, other.weight).append(weightUnit, other.weightUnit)
				.append(setDate, other.setDate).append(updateDate, other.updateDate).append(applyDate, other.applyDate)
				.append(errorDistinction, other.errorDistinction).append(delDistinction, other.delDistinction)
				.append(delReason, other.delReason).append(applyState, other.applyState).isEquals();
	}
}
