//****************************************************************************//
// システム                                    :優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                  作成担当 : 楊蕾蕾
// 作 成 日       2017/02/13   修正内容 : 申請 (新規品目):新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applynewcategory.facade;
import jp.co.broadleaf.breg.applynewcategory.facade.dto.ApplyNewCategoryDto;

/**
 * <pre>
 * 申請 (新規品目)Facadeインタフェース.
 * </pre>
 */
public interface ApplyNewCategoryFacade {
  /**
   * <pre>
   * 初期データを取得する.
   * </pre>
   * @param applyNewCategoryDto 申請 (新規品目)のDTO
   * @param isApplyAgain ApplyStatus
   * @return applyNo 申請ID
   */
  ApplyNewCategoryDto getApplyHistoryInfo(ApplyNewCategoryDto applyNewCategoryDto,boolean isApplyAgain);
  
  /**
   * <pre>
   * 申請ヘッダマスタのApplyNOを取得する.
   * </pre>
   * 
   * @return applyNo 申請ID
   */
   int searchApplyNO();
   
   /**
    * <pre>
    * 申請 (新規品目)の情報を取得する.
    * </pre>
    * 
    * @param applyNewCategoryDto 申請 (新規品目)のDTO
    * @return 0:挿入成功  1:挿入失敗
    */
    int saveApplyNewCaregory (ApplyNewCategoryDto applyNewCategoryDto);
    
    /**
     * Mail
     * 
     * @param makerCode makerCode
     * @param loginId loginId
     * @param ipAddr ipAddr
     * @return メッセージ
     */
    boolean sendMail(int makerCode, String loginId, String ipAddr);

}
