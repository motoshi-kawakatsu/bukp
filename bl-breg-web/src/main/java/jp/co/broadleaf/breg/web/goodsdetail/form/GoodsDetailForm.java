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

import jp.co.broadleaf.breg.goodsdetail.facade.dto.GoodsDetailDto;

/**
 * ログインFormのクラスのクラス。
 */
public class GoodsDetailForm {

	/**
	 * mode
	 */
	private int mode;
	/**
	 * goodsDetailDto
	 */
	private GoodsDetailDto goodsDetailDto;	
	/**
	 * imageNum
	 */
	private int imageNum;
	
	/**
	 * modeの取得。
	 *
	 * @return int mode
	 */
	public int getGoodsDetaiMode() {
		return mode;
	}

	/**
	 * modeの設定。
	 *
	 * @param mode int
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	/**
	 * imageNumの取得。
	 *
	 * @return int imageNum
	 */
	public int getImageNum() {
		return imageNum;
	}

	/**
	 * imageNumの設定。
	 *
	 * @param imageNum int
	 */
	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

	/**
	 * goodsDetailDtoの取得。
	 *
	 * @return GoodsDetailDto goodsDetailDto
	 */
	public GoodsDetailDto getGoodsDetailDto() {
		return goodsDetailDto;
	}

	/**
	 * goodsDetailDtoの設定。
	 *
	 * @param goodsDetailDto GoodsDetailDto
	 */
	public void setGoodsDetailDto(GoodsDetailDto goodsDetailDto) {
		this.goodsDetailDto = goodsDetailDto;
	}
}
