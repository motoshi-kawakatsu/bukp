//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 李超傑
// 作 成 日       2017/02/06   修正内容 : 結合詳細：新規作成
package jp.co.broadleaf.breg.joindetail.service;

import java.util.List;
import java.util.Map;

import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.joindetail.facade.dto.JoinDetailDto;

/**
 * <pre>
 * 結合詳細サービスインターフェース
 * </pre>
 */
public interface JoinDetailService {
  /**
   * 結合マストリストを取得します。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param tbsPartsCode BLコード
   * @param joinSourceMakerCode 結合元メーカーコード
   * @param prmSetDtlNo2 優良設定詳細コード２
   * @param joinSourPartsNoWithH 結合元品番(－付き品番)
   * @return 結合詳細のDTOリスト
   */
  List<JoinMasterMaker> getJoinDetail(String prmSetDtlNo1, String partsMakerCd, String tbsPartsCode,
                                      String joinSourceMakerCode, String prmSetDtlNo2, String joinSourPartsNoWithH);


  /**
   * 結合マスタ(メーカー)案のチェック.
   * @param joinDetailDto joinDetailDto
   * @param makerCd メーカーコード
   * @param messageMap messageMap
   * @param mode mode
   * @return チェック結果
   */
  boolean checkJoin(JoinDetailDto joinDetailDto, int makerCd, Map<String, String> messageMap ,int mode);
  
  /**
   * 表示順位重複チェック
   * 
   * @param joinDetailDtoList JoinDetailDto
   * @return チェック結果
   */
  boolean checkDispOrder(List<JoinDetailDto> joinDetailDtoList);
  
  /**
   * 商品マスタ存在チェック
   * 
   * @param joinDetailDtoList JoinDetailDto
   * @return チェック結果
   */
  boolean checkGoodsMasterIsExist(List<JoinDetailDto> joinDetailDtoList);

}
