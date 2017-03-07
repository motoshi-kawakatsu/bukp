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
package jp.co.broadleaf.breg.web.goodsdetail.form;

import java.util.List;

/**
 * ログインFormのクラスのクラス。
 */
public class GoodsImgForm {

	/**
	 * imgSrcList
	 */
	private List<String> imgSrcList;
	/**
	 * imgNameList
	 */
	private List<String> imgNameList;
	/**
	 * delImgList
	 */
	private List<String> delImgList;
	/**
	 * 優良品番
	 */
	private String goodsCd;

	/**
	 * imgSrcListの取得。
	 *
	 * @return List imgSrcList
	 */
	public List<String> getImgSrcList() {
		return imgSrcList;
	}

	/**
	 * imgSrcListの設定。
	 *
	 * @param imgSrcList List
	 */
	public void setImgSrcList(List<String> imgSrcList) {
		this.imgSrcList = imgSrcList;
	}

	/**
	 * imgNameListの取得。
	 *
	 * @return List imgNameList
	 */
	public List<String> getImgNameList() {
		return imgNameList;
	}

	/**
	 * imgNameListの設定。
	 *
	 * @param imgNameList List
	 */
	public void setImgNameList(List<String> imgNameList) {
		this.imgNameList = imgNameList;
	}
	
	/**
	 * delImgListの取得。
	 *
	 * @return List delImgList
	 */
	public List<String> getDelImgList() {
		return delImgList;
	}

	/**
	 * delImgListの設定。
	 *
	 * @param delImgList List
	 */
	public void setDelImgList(List<String> delImgList) {
		this.delImgList = delImgList;
	}
	
	/**
	 * 優良品番の取得。
	 *
	 * @return String goodsCd
	 */
	public String getGoodsCd() {
		return goodsCd;
	}

	/**
	 * 優良品番の設定。
	 *
	 * @param goodsCd String
	 */
	public void setGoodsCd(String goodsCd) {
		this.goodsCd = goodsCd;
	}
}
