package jp.co.broadleaf.breg.joinlist.facade.impl;

import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.joinlist.facade.JoinMasterWorkFacade;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterSearchDto;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterSearchModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterWorkModel;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterWorkService;
import jp.co.broadleaf.breg.message.service.MessageService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 結合マスタ(メーカー)ワークFacadeクラス.
 * </pre>
 */
public class JoinMasterWorkFacadeImpl implements JoinMasterWorkFacade {

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの取得.
   * </pre>
   * 
   * @param joinMasterSearchDto 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 表示順
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param importKbn インポート区分
   * @return 結合マスタ(メーカー)ワークDto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterWorkInfoList(JoinMasterSearchDto joinMasterSearchDto, int order,
                                                        Long skipRows, Long maxRows, Boolean isPage,
                                                        Integer importKbn) {
    JoinMasterInfoDto joinMasterWorkInfoDto = new JoinMasterInfoDto();
    JoinMasterWorkModel joinMasterWorkModel = joinMasterWorkService.searchJoinMasterWorkInfoList(
        convertJoinMasterSearchModel(joinMasterSearchDto), order, skipRows, maxRows, isPage, importKbn);
    joinMasterWorkInfoDto.setJoinMasterDto(convertJoinMasterWorkInfoDto(joinMasterWorkModel));
    joinMasterWorkInfoDto.setSearchCounts(joinMasterWorkModel.getSearchCounts());
    return joinMasterWorkInfoDto;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの全データ取得.
   * </pre>
   * 
   * @return 結合マスタ(メーカー)ワークDto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterWorkInfoList() {
    JoinMasterInfoDto joinMasterWorkInfoDto = new JoinMasterInfoDto();
    joinMasterWorkInfoDto
        .setJoinMasterDto(convertJoinMasterWorkInfoDto(joinMasterWorkService.searchJoinMasterWorkList()));
    return joinMasterWorkInfoDto;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param importKbn インポート区分
   * @return 結合マスタ(メーカー)ワークDto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterWorkAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                                   Integer importKbn) {
    JoinMasterInfoDto joinMasterWorkInfoDto = new JoinMasterInfoDto();
    joinMasterWorkInfoDto.setJoinMasterDto(convertJoinMasterWorkInfoDto(
        joinMasterWorkService.searchJoinMasterWorkAll(skipRows, maxRows, isPage, makerCd, importKbn)));
    return joinMasterWorkInfoDto;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの登録.
   * </pre>
   * 
   * @param joinMasterWorkInfoDto 結合マスタ(メーカー)ワークDto
   * @return 登録件数
   */
  @Override
  public int insertJoinMasterWorkInfoList(JoinMasterInfoDto joinMasterWorkInfoDto) {
    return joinMasterWorkService
        .insertJoinMasterWorkInfoList(convertJoinMasterWorkModel(joinMasterWorkInfoDto.getJoinMasterDto()));
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク情報更新.
   * </pre>
   * 
   * @param joinMasterWorkDto 結合マスタ(メーカー)ワークDto
   */
  @Override
  public void updateJoinMasterWork(JoinMasterDto joinMasterWorkDto) {
    List<JoinMasterDto> joinMasterWorkDtoList = new ArrayList<>();
    joinMasterWorkDtoList.add(joinMasterWorkDto);
    joinMasterWorkService
        .updateJoinMasterWork(convertJoinMasterWorkModel(joinMasterWorkDtoList).getJoinMasterWorkList().get(0));
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク削除.
   * </pre>
   * 
   * @param joinMasterWorkDto 結合マスタ(メーカー)ワーク
   */
  @Override
  public void deleteJoinMasterWork(JoinMasterDto joinMasterWorkDto) {
    List<JoinMasterDto> joinMasterWorkDtoList = new ArrayList<>();
    joinMasterWorkDtoList.add(joinMasterWorkDto);
    joinMasterWorkService
        .deleteJoinMasterWork(convertJoinMasterWorkModel(joinMasterWorkDtoList).getJoinMasterWorkList().get(0));
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク情報全削除.
   * </pre>
   */
  @Override
  public void deleteAllJoinMasterWork() {
    joinMasterWorkService.deleteAll();
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param joinMasterWorkModel 結合マスタ(メーカー)ワークModel
   * @return 結合マスタ(メーカー)ワークDto
   */
  private List<JoinMasterDto> convertJoinMasterWorkInfoDto(JoinMasterWorkModel joinMasterWorkModel) {
    List<JoinMasterDto> joinMasterWorkDtoList = new ArrayList<JoinMasterDto>();
    if (null != joinMasterWorkModel.getJoinMasterWorkList() && joinMasterWorkModel.getJoinMasterWorkList().size() > 0) {
      for (JoinMasterWorkMaker joinMasterWork : joinMasterWorkModel.getJoinMasterWorkList()) {
        JoinMasterDto joinMasterWorkDto = new JoinMasterDto();
        // 優良設定詳細コード１
        joinMasterWorkDto.setPrmSetDtlNo1(joinMasterWork.getPrmSetDtlNo1());
        // 部品メーカーコード
        joinMasterWorkDto.setPartsMakerCd(joinMasterWork.getPartsMakerCd());
        // 商品中分類コード
        joinMasterWorkDto.setGoodsMGroup(joinMasterWork.getGoodsMGroup());
        // BLコード
        joinMasterWorkDto.setTbsPartsCode(joinMasterWork.getTbsPartsCode());
        // 結合元メーカーコード
        joinMasterWorkDto.setJoinSourceMakerCode(joinMasterWork.getJoinSourceMakerCode());
        // 優良設定詳細コード２
        joinMasterWorkDto.setPrmSetDtlNo2(joinMasterWork.getPrmSetDtlNo2());
        // 結合元品番(－付き品番)
        joinMasterWorkDto.setJoinSourPartsNoWithH(joinMasterWork.getJoinSourPartsNoWithH());
        // 結合表示順位
        joinMasterWorkDto.setJoinDispOrder(joinMasterWork.getJoinDispOrder());
        // 結合先品番(－付き品番)
        joinMasterWorkDto.setJoinDestPartsNo(joinMasterWork.getJoinDestPartsNo());
        // 結合QTY
        joinMasterWorkDto.setJoinQty(joinMasterWork.getJoinQty());
        // 結合規格・特記事項
        joinMasterWorkDto.setJoinSpecialNote(joinMasterWork.getJoinSpecialNote());
        // 優良部品規格・特記事項(C向け)
        joinMasterWorkDto.setPrimePartsSpecialNoteC(joinMasterWork.getPrimePartsSpecialNoteC());
        // 適用日付
        joinMasterWorkDto.setStartTime(joinMasterWork.getStartTime());
        // 削除時申請理由
        joinMasterWorkDto.setDeleteReason(joinMasterWork.getDeleteReason());
        // チェック区分
        joinMasterWorkDto.setCheckFlg(joinMasterWork.getCheckFlg());
        // データステータス
        joinMasterWorkDto.setErrorFlg(joinMasterWork.getErrorFlg());
        // BL登録区分
        joinMasterWorkDto.setBlEntryFlg(joinMasterWork.getBlEntryFlg());
        // インポート区分
        joinMasterWorkDto.setImportKbn(joinMasterWork.getImportKbn());
        // 処理区分
        joinMasterWorkDto.setManageKbn(joinMasterWork.getManageKbn());
        // エラー内容
        joinMasterWorkDto.setErrorDetail(joinMasterWork.getErrorDetail());
        // 削除依頼区分
        joinMasterWorkDto.setDeleteFlg(joinMasterWork.getDeleteFlg());
        // 申請状態
        joinMasterWorkDto.setApplyCondition(joinMasterWork.getApplyCondition());
        // 作成日時
        joinMasterWorkDto.setInsDtTime(joinMasterWork.getInsDtTime());
        // 更新日時
        joinMasterWorkDto.setUpdDtTime(joinMasterWork.getUpdDtTime());
        joinMasterWorkDtoList.add(joinMasterWorkDto);
      }
    }
    return joinMasterWorkDtoList;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param joinMasterWorkDtoList 結合マスタ(メーカー)ワークDto
   * @return 結合マスタ(メーカー)ワークModel
   */
  private JoinMasterWorkModel convertJoinMasterWorkModel(List<JoinMasterDto> joinMasterWorkDtoList) {
    JoinMasterWorkModel joinMasterWorkModel = new JoinMasterWorkModel();
    if (null != joinMasterWorkDtoList && joinMasterWorkDtoList.size() > 0) {
      List<JoinMasterWorkMaker> joinMasterWorkList = new ArrayList<JoinMasterWorkMaker>();
      for (JoinMasterDto joinMasterWorkDto : joinMasterWorkDtoList) {
        JoinMasterWorkMaker joinMasterWork = new JoinMasterWorkMaker();
        // 優良設定詳細コード１
        joinMasterWork.setPrmSetDtlNo1(joinMasterWorkDto.getPrmSetDtlNo1());
        // 部品メーカーコード
        joinMasterWork.setPartsMakerCd(joinMasterWorkDto.getPartsMakerCd());
        // 商品中分類コード
        joinMasterWork.setGoodsMGroup(joinMasterWorkDto.getGoodsMGroup());
        // BLコード
        joinMasterWork.setTbsPartsCode(joinMasterWorkDto.getTbsPartsCode());
        // 結合元メーカーコード
        joinMasterWork.setJoinSourceMakerCode(joinMasterWorkDto.getJoinSourceMakerCode());
        // 優良設定詳細コード２
        joinMasterWork.setPrmSetDtlNo2(joinMasterWorkDto.getPrmSetDtlNo2());
        // 結合元品番(－付き品番)
        joinMasterWork.setJoinSourPartsNoWithH(joinMasterWorkDto.getJoinSourPartsNoWithH());
        // 結合表示順位
        joinMasterWork.setJoinDispOrder(joinMasterWorkDto.getJoinDispOrder());
        // 結合先品番(－付き品番)
        joinMasterWork.setJoinDestPartsNo(joinMasterWorkDto.getJoinDestPartsNo());
        // 結合QTY
        joinMasterWork.setJoinQty(joinMasterWorkDto.getJoinQty());
        // 結合規格・特記事項
        joinMasterWork.setJoinSpecialNote(joinMasterWorkDto.getJoinSpecialNote());
        // 優良部品規格・特記事項(C向け)
        joinMasterWork.setPrimePartsSpecialNoteC(joinMasterWorkDto.getPrimePartsSpecialNoteC());
        // 適用日付
        joinMasterWork.setStartTime(joinMasterWorkDto.getStartTime());
        // 削除時申請理由
        joinMasterWork.setDeleteReason(joinMasterWorkDto.getDeleteReason());
        // チェック区分
        joinMasterWork.setCheckFlg(joinMasterWorkDto.getCheckFlg());
        // データステータス
        joinMasterWork.setErrorFlg(joinMasterWorkDto.getErrorFlg());
        // BL登録区分
        joinMasterWork.setBlEntryFlg(joinMasterWorkDto.getBlEntryFlg());
        // インポート区分
        joinMasterWork.setImportKbn(joinMasterWorkDto.getImportKbn());
        // 処理区分
        joinMasterWork.setManageKbn(joinMasterWorkDto.getManageKbn());
        // エラー内容
        joinMasterWork.setErrorDetail(joinMasterWorkDto.getErrorDetail());
        // 削除依頼区分
        joinMasterWork.setDeleteFlg(joinMasterWorkDto.getDeleteFlg());
        // 申請状態
        joinMasterWork.setApplyCondition(joinMasterWorkDto.getApplyCondition());
        joinMasterWorkList.add(joinMasterWork);
      }
      joinMasterWorkModel.setJoinMasterWorkList(joinMasterWorkList);
    }
    return joinMasterWorkModel;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param joinMasterSearchDto 結合マスタの検索条件のDto
   * @return 結合マスタの検索条件のModel
   */
  private JoinMasterSearchModel convertJoinMasterSearchModel(JoinMasterSearchDto joinMasterSearchDto) {
    JoinMasterSearchModel joinMasterSearchModel = new JoinMasterSearchModel();
    if (null == joinMasterSearchDto) {
      return null;
    }
    // BLコード
    joinMasterSearchModel.setTbsPartsCode(joinMasterSearchDto.getTbsPartsCode());
    // 純正品番
    joinMasterSearchModel.setJoinSourPartsNoWithH(joinMasterSearchDto.getJoinSourPartsNoWithH());
    // 優良品番
    joinMasterSearchModel.setJoinDestPartsNo(joinMasterSearchDto.getJoinDestPartsNo());
    // 申請状態
    joinMasterSearchModel.setApplyCondition(joinMasterSearchDto.getApplyCondition());
    // 処理区分
    joinMasterSearchModel.setManageKbn(joinMasterSearchDto.getManageKbn());
    // データステータス
    joinMasterSearchModel.setErrorFlg(joinMasterSearchDto.getErrorFlg());
    // セレクトコード
    joinMasterSearchModel.setPrmSetDtlNo1(joinMasterSearchDto.getPrmSetDtlNo1());
    // 商品中分類コード
    joinMasterSearchModel.setGoodsMGroup(joinMasterSearchDto.getGoodsMGroup());
    // カーコード
    joinMasterSearchModel.setJoinSourceMakerCode(joinMasterSearchDto.getJoinSourceMakerCode());
    // 適用日付From
    joinMasterSearchModel.setStartTimeStart(joinMasterSearchDto.getStartTimeStart());
    // 適用日付To
    joinMasterSearchModel.setStartTimeEnd(joinMasterSearchDto.getStartTimeEnd());
    // 分類コード
    joinMasterSearchModel.setGoodsMGroup(joinMasterSearchDto.getGoodsMGroup());
    // 種別コード
    joinMasterSearchModel.setPrmSetDtlNo2(joinMasterSearchDto.getPrmSetDtlNo2());
    // 作成日時From
    joinMasterSearchModel.setInsDtTimeStart(joinMasterSearchDto.getInsDtTimeStart());
    // 作成日時To
    joinMasterSearchModel.setInsDtTimeEnd(joinMasterSearchDto.getInsDtTimeEnd());
    // 規格・特記
    joinMasterSearchModel.setJoinSpecialNote(joinMasterSearchDto.getJoinSpecialNote());
    // 削除依頼区分
    joinMasterSearchModel.setDeleteFlg(joinMasterSearchDto.getDeleteFlg());
    // 更新日時From
    joinMasterSearchModel.setUpdDtTimeStart(joinMasterSearchDto.getUpdDtTimeStart());
    // 更新日時To
    joinMasterSearchModel.setUpdDtTimeEnd(joinMasterSearchDto.getUpdDtTimeEnd());
    // 規格/特記(一般)
    joinMasterSearchModel.setPrimePartsSpecialNoteC(joinMasterSearchDto.getPrimePartsSpecialNoteC());
    // 部品メーカーコード
    joinMasterSearchModel.setPartsMakerCd(joinMasterSearchDto.getPartsMakerCd());
    return joinMasterSearchModel;
  }

  /** 結合マスタ(メーカー)ワークサービス */
  private JoinMasterWorkService joinMasterWorkService;

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークサービス.
   * </pre>
   * 
   * @param joinMasterWorkService 結合マスタ(メーカー)ワークサービス
   */
  @Resource
  public void setJoinMasterWorkService(JoinMasterWorkService joinMasterWorkService) {
    this.joinMasterWorkService = joinMasterWorkService;
  }

  /** メッセージサービス */
  private MessageService messageService;

  /**
   * メッセージサービス
   * 
   * @param messageService MessageService
   */
  @Resource
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * メッセージ情報を取得
   * 
   * @param messageCode メッセージコード
   * @return メッセージ情報
   */
  @Override
  public String getMessage(String messageCode) {
    return messageService.messageInfo(messageCode);
  }
}
