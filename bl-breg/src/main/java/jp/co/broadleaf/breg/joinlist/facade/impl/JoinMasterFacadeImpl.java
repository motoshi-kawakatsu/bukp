package jp.co.broadleaf.breg.joinlist.facade.impl;

import jp.co.broadleaf.breg.classifycodeguide.service.ClassifyCodeGuideService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommonId;
import jp.co.broadleaf.breg.common.entity.CarmakerMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.KindCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.JudgeEnum;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.CarmakerMasterCommonService;
import jp.co.broadleaf.breg.common.service.KindCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.SelectCodeMasterCommonService;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import jp.co.broadleaf.breg.goodsguide.service.GoodsGuideService;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterMakerService;
import jp.co.broadleaf.breg.joinlist.facade.JoinMasterFacade;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinGridDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterSearchDto;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterSearchModel;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterService;
import jp.co.broadleaf.breg.joinlistcommon.service.JoinMasterCommonService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.selectmaker.service.SelectMakerService;
import jp.co.broadleaf.breg.setlist.service.SetMasterService;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.framework.security.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <pre>
 * 結合マスタ(メーカー)Facadeクラス.
 * </pre>
 */
public class JoinMasterFacadeImpl implements JoinMasterFacade {

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param joinMasterSearchDto 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 表示順
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param searchMode searchMode
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterInfoList(JoinMasterSearchDto joinMasterSearchDto, int order, Long skipRows,
                                                    Long maxRows, Boolean isPage, int searchMode) {
    JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
    JoinMasterModel joinMasterModel = joinMasterService.searchJoinMasterInfoList(
        convertJoinMasterSearchModel(joinMasterSearchDto), order, skipRows, maxRows, isPage, searchMode);
    joinMasterInfoDto.setJoinMasterDto(convertJoinMasterInfoDto(joinMasterModel));
    joinMasterInfoDto.setSearchCounts(joinMasterModel.getSearchCounts());
    return joinMasterInfoDto;
  }

  /**
   * <pre>
   * 結合選択モードの結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param joinMasterInfoDto 未編集の結合マスタリスト
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterInfoList(JoinMasterInfoDto joinMasterInfoDto) {
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<>();
    for (JoinMasterDto joinMasterDto : joinMasterInfoDto.getJoinMasterDto()) {
      List<JoinMasterDto> editList = new ArrayList<>();
      editList.add(joinMasterDto);
      List<JoinMasterCommon> joinMasterCommonList = joinMasterCommonService
          .searchJoinMasterCommonList(convertJoinMasterModel(editList).getJoinMasterList().get(0));
      if (joinMasterCommonList != null && !joinMasterCommonList.isEmpty()) {
        JoinMasterCommon joinMasterCommon = joinMasterCommonList.get(0);
        StringBuffer compareFlag = new StringBuffer();
        compareFlag.append("0"); // check
        compareFlag.append(",0"); // no
        compareFlag.append(",0"); // 申請状態
        dataJudge(joinMasterDto.getManageKbn(), joinMasterCommon.getDealFlg(), compareFlag);// 処理区分
        compareFlag.append(",0"); // 優良設定詳細コード１
        Integer goodsMGroupCommon = joinMasterCommon.getGoodsMGroup();
        dataJudge(joinMasterDto.getGoodsMGroup(), goodsMGroupCommon, compareFlag); // 商品中分類コード
        compareFlag.append(",0"); // BLコード
        compareFlag.append(",0"); // 結合元メーカーコード
        compareFlag.append(",0"); // 結合元品番(－付き品番)
        compareFlag.append(",0"); // 優良設定詳細コード2
        compareFlag.append(",0"); // 結合表示順位
        String joinDestPartsNoCommon = joinMasterCommon.getJoinDestPartsNo();
        dataJudge(joinMasterDto.getJoinDestPartsNo(), joinDestPartsNoCommon, compareFlag); // 結合先品番(－付き品番)
        Double joinQtyCommon = joinMasterCommon.getJoinQty();
        dataJudge(joinMasterDto.getJoinQty(), joinQtyCommon, compareFlag); // QTY
        String joinSpecialNoteCommon = joinMasterCommon.getJoinSpecialNote();
        dataJudge(joinMasterDto.getJoinSpecialNote(), joinSpecialNoteCommon, compareFlag); // 規格/特記
        String primePartsSpecialNoteCCommon = joinMasterCommon.getPrimePartsSpecialNoteC();
        dataJudge(joinMasterDto.getPrimePartsSpecialNoteC(), primePartsSpecialNoteCCommon, compareFlag); // 規格/特記(一般)
        Short deleteFlgCommon = joinMasterCommon.getDeleteFlg();
        dataJudge(joinMasterDto.getDeleteFlg(), deleteFlgCommon, compareFlag); // 削除依頼区分
        String delReasonCommon = joinMasterCommon.getDeleteReason();
        dataJudge(joinMasterDto.getDeleteReason(), delReasonCommon, compareFlag); // 削除理由
        compareFlag.append(",0"); // 作成日時
        compareFlag.append(",0"); // 更新日時
        Timestamp startTimeCommon = joinMasterCommon.getStartTime();
        dataJudge(joinMasterDto.getStartTime(), startTimeCommon, compareFlag); // 適用日時
        compareFlag.append(",0"); // チェック区分
        compareFlag.append(",0"); // BL登録区分
        compareFlag.append(",0");// データステータス
        compareFlag.append(",0");// エラー内容
        joinMasterDto.setCompareFlag(compareFlag.toString());
      }
      joinMasterDtoList.add(joinMasterDto);
    }
    JoinMasterInfoDto joinMaster = new JoinMasterInfoDto();
    joinMaster.setJoinMasterDto(joinMasterDtoList);
    return joinMaster;
  }

  /**
   * <pre>
   * 比較の取得.(メーカー側のデータと共有側のデータは比較する)
   * </pre>
   * 
   * @param maker Object
   * @param common Object
   * @param flag StringBuffer
   */
  public void dataJudge(Object maker, Object common, StringBuffer flag) {
    if (maker == null) {
      if (common == null) {
        flag.append(",0");
      } else {
        flag.append(",1");
      }
    } else {
      if (!maker.equals(common)) {
        flag.append(",1");
      } else {
        flag.append(",0");
      }
    }
  }

  /**
   * 画面リストの比較
   * 
   * @param joinGridDtoList 改修後のリスト
   * @param joinMasterDtoList 改修前のリスト
   * @return joinMasterMakerDtoList
   */
  public List<JoinMasterDto> campareList(List<JoinMasterDto> joinGridDtoList, List<JoinMasterDto> joinMasterDtoList) {
    List<JoinMasterDto> delJoinMasterDtoList = new ArrayList<>();
    for (JoinMasterDto joinMasterDto : joinMasterDtoList) {
      delJoinMasterDtoList.add(joinMasterDto);
    }
    for (JoinMasterDto joinMasterDto : joinMasterDtoList) {
      for (JoinMasterDto gridDto : joinGridDtoList) {
        if (joinMasterDto.getPrmSetDtlNo1() == gridDto.getPrmSetDtlNo1()
            && joinMasterDto.getTbsPartsCode() == gridDto.getTbsPartsCode()
            && joinMasterDto.getJoinSourceMakerCode() == gridDto.getJoinSourceMakerCode()
            && joinMasterDto.getPrmSetDtlNo2() == gridDto.getPrmSetDtlNo2()
            && joinMasterDto.getJoinSourPartsNoWithH().equals(gridDto.getJoinSourPartsNoWithH())
            && joinMasterDto.getJoinDispOrder() == gridDto.getJoinDispOrder()) {
          delJoinMasterDtoList.remove(joinMasterDto);
          break;
        }
      }
    }
    return delJoinMasterDtoList;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)の全データ取得.
   * </pre>
   * 
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterInfoList() {
    JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
    joinMasterInfoDto.setJoinMasterDto(convertJoinMasterInfoDto(joinMasterService.searchJoinMasterInfoList()));
    return joinMasterInfoDto;
  }

  /**
   * <pre>
   * 結合ページ内行数設定の取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 結合ページ内行数設定
   */
  @Override
  public int searchJoinListRows(int makerCd) {
    return companyInfoMasterCommonService.getCompanyInfo(makerCd).getJoinRows();
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param searchMode searchMode
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterInfoDtoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                                      int searchMode) {
    JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
    joinMasterInfoDto.setJoinMasterDto(convertJoinMasterInfoDto(
        joinMasterService.searchJoinMasterInfoDtoAll(skipRows, maxRows, isPage, makerCd, searchMode)));
    return joinMasterInfoDto;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)の登録.
   * </pre>
   * 
   * @param joinMasterInfoDto 結合マスタ(メーカー)Dto
   * @return 登録件数
   */
  @Override
  public int insertJoinMasterInfoList(JoinMasterInfoDto joinMasterInfoDto) {
    return joinMasterService.insertJoinMasterInfoList(convertJoinMasterModel(joinMasterInfoDto.getJoinMasterDto()));
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param joinMasterDto 結合マスタ(メーカー)Dto
   */
  @Override
  public void updateJoinMaster(JoinMasterDto joinMasterDto) {
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<>();
    joinMasterDtoList.add(joinMasterDto);
    joinMasterService.updateJoinMaster(convertJoinMasterModel(joinMasterDtoList).getJoinMasterList().get(0));
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)削除.
   * </pre>
   * 
   * @param joinMasterDto 結合マスタ(メーカー)Dto
   */
  @Override
  public void deleteJoinMaster(JoinMasterDto joinMasterDto) {
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<>();
    joinMasterDtoList.add(joinMasterDto);
    joinMasterService.deleteJoinMaster(convertJoinMasterModel(joinMasterDtoList).getJoinMasterList().get(0));
  }

  /**
   * CheckDivの取得
   * 
   * @param joinMasterDto 結合マスタ(メーカー)Dto
   * @return CheckDiv
   */
  @Override
  public Boolean getCheckDiv(JoinMasterDto joinMasterDto) {
    SelectMaker selectMaker = selectMakerService.searchJoinById(joinMasterDto.getPrmSetDtlNo1(),
        joinMasterDto.getPartsMakerCd(), joinMasterDto.getTbsPartsCode(), joinMasterDto.getJoinSourceMakerCode(),
        joinMasterDto.getPrmSetDtlNo2(), joinMasterDto.getJoinSourPartsNoWithH(), joinMasterDto.getJoinDispOrder());
    if (selectMaker == null) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * SelectMakerの処理
   * 
   * @param joinGridDto 結合マスタ
   * @param makerCd メーカーコード
   */
  @Override
  public void manegeSelectMaker(JoinGridDto joinGridDto, int makerCd) {
    int jPrmSetDtlNo1 = joinGridDto.getPrmSetDtlNo1() == null ? 9999
        : Integer.parseInt(joinGridDto.getPrmSetDtlNo1().split("：")[0]);
    int jTbsPartsCode = joinGridDto.getTbsPartsCode() == null ? 0
        : Integer.parseInt(joinGridDto.getTbsPartsCode().split("：")[0]);
    int jJoinSourceMakerCode = joinGridDto.getJoinSourceMakerCode() == null ? 0
        : Integer.parseInt(joinGridDto.getJoinSourceMakerCode().split("：")[0]);
    String jJoinSourPartsNoWithH = joinGridDto.getJoinSourPartsNoWithH() == null ? ""
        : joinGridDto.getJoinSourPartsNoWithH().split("：")[0];
    int jPrmSetDtlNo2 = joinGridDto.getPrmSetDtlNo2() == null ? 0
        : Integer.parseInt(joinGridDto.getPrmSetDtlNo2().split("：")[0]);
    int jJoinDispOrder = joinGridDto.getJoinDispOrder() == null ? 0 : Integer.parseInt(joinGridDto.getJoinDispOrder());
    if (joinGridDto.getCheck()) {
      SelectMaker selectMaker = convertSelectMaker(joinGridDto, makerCd);
      selectMakerService.insertJoinById(selectMaker);
    } else {
      selectMakerService.deleteJoinById(jPrmSetDtlNo1, makerCd, jTbsPartsCode, jJoinSourceMakerCode, jPrmSetDtlNo2,
          jJoinSourPartsNoWithH, jJoinDispOrder);
    }
  }

  /**
   * SelectMakerの取得
   * 
   * @param joinGridDto joinGridDto
   * @param makerCd makerCd
   * @return SelectMaker
   */
  private SelectMaker convertSelectMaker(JoinGridDto joinGridDto, int makerCd) {
    SelectMaker selectMaker = new SelectMaker();
    int initValue = 0;
    String empty = "";
    selectMaker.setGPrmSetDtlNo1(initValue);
    selectMaker.setGPartsMakerCd(initValue);
    selectMaker.setGPrimePartsNoWithH(empty);
    selectMaker.setSPrmSetDtlNo1(initValue);
    selectMaker.setSPartsMakerCd(initValue);
    selectMaker.setSSetMainPartsNo(empty);
    selectMaker.setSSetDispOrder(initValue);
    int jPrmSetDtlNo1 = joinGridDto.getPrmSetDtlNo1() == null ? 9999
        : Integer.parseInt(joinGridDto.getPrmSetDtlNo1().split("：")[0]);
    int jTbsPartsCode = joinGridDto.getTbsPartsCode() == null ? 0
        : Integer.parseInt(joinGridDto.getTbsPartsCode().split("：")[0]);
    int jJoinSourceMakerCode = joinGridDto.getJoinSourceMakerCode() == null ? 0
        : Integer.parseInt(joinGridDto.getJoinSourceMakerCode().split("：")[0]);
    String jJoinSourPartsNoWithH = joinGridDto.getJoinSourPartsNoWithH() == null ? ""
        : joinGridDto.getJoinSourPartsNoWithH().split("：")[0];
    int jPrmSetDtlNo2 = joinGridDto.getPrmSetDtlNo2() == null ? 0
        : Integer.parseInt(joinGridDto.getPrmSetDtlNo2().split("：")[0]);
    int jJoinDispOrder = joinGridDto.getJoinDispOrder() == null ? 0 : Integer.parseInt(joinGridDto.getJoinDispOrder());
    selectMaker.setJPrmSetDtlNo1(jPrmSetDtlNo1);
    selectMaker.setJPartsMakerCd(makerCd);
    selectMaker.setJTbsPartsCode(jTbsPartsCode);
    selectMaker.setJJoinSourceMakerCode(jJoinSourceMakerCode);
    selectMaker.setJPrmSetDtlNo2(jPrmSetDtlNo2);
    selectMaker.setJJoinSourPartsNoWithH(jJoinSourPartsNoWithH);
    selectMaker.setJJoinDispOrder(jJoinDispOrder);
    selectMaker.setSelectKbn(2);
    return selectMaker;
  }

  /**
   * <pre>
   * チェック選択に情報取得.
   * </pre>
   * 
   * @param partsMakerCd 部品メーカーコード
   * @return チェック選択情報
   */
  @Override
  public int searchSelectBySelectKbn(int partsMakerCd) {
    return selectMakerService.searchBySelectKbn(2, partsMakerCd, 0).size();
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd 部品メーカーコード
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterInfoBySelect(int partsMakerCd) {
    JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
    List<SelectMaker> selectMakerList = selectMakerService.searchBySelectKbn(2, partsMakerCd, 0);
    List<JoinMasterMaker> joinList = new ArrayList<>();
    for (SelectMaker selectMaker : selectMakerList) {
      JoinMasterMakerId joinMasterId = new JoinMasterMakerId();
      joinMasterId.setPrmSetDtlNo1(selectMaker.getJPrmSetDtlNo1());
      joinMasterId.setPartsMakerCd(selectMaker.getJPartsMakerCd());
      joinMasterId.setTbsPartsCode(selectMaker.getJTbsPartsCode());
      joinMasterId.setJoinSourceMakerCode(selectMaker.getJJoinSourceMakerCode());
      joinMasterId.setPrmSetDtlNo2(selectMaker.getJPrmSetDtlNo2());
      joinMasterId.setJoinSourPartsNoWithH(selectMaker.getJJoinSourPartsNoWithH());
      joinMasterId.setJoinDispOrder(selectMaker.getJJoinDispOrder());
      JoinMasterMaker joinMasterMaker = joinMasterService.searchByJoinMasterId(joinMasterId);
      if (joinMasterMaker != null) {
        joinList.add(joinMasterMaker);
      }
    }
    JoinMasterModel joinMasterModel = new JoinMasterModel();
    joinMasterModel.setJoinMasterList(joinList);
    List<JoinMasterDto> joinMasterDtoList = convertJoinMasterInfoDto(joinMasterModel);
    joinMasterInfoDto.setJoinMasterDto(joinMasterDtoList);
    return joinMasterInfoDto;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param joinMasterModel 結合マスタ(メーカー)Model
   * @return 結合マスタ(メーカー)Dto
   */
  private List<JoinMasterDto> convertJoinMasterInfoDto(JoinMasterModel joinMasterModel) {
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<JoinMasterDto>();
    if (null != joinMasterModel.getJoinMasterList() && joinMasterModel.getJoinMasterList().size() > 0) {
      for (JoinMasterMaker joinMaster : joinMasterModel.getJoinMasterList()) {
        JoinMasterDto joinMasterDto = new JoinMasterDto();
        // 優良設定詳細コード１
        joinMasterDto.setPrmSetDtlNo1(joinMaster.getPrmSetDtlNo1());
        // 部品メーカーコード
        joinMasterDto.setPartsMakerCd(joinMaster.getPartsMakerCd());
        // 商品中分類コード
        joinMasterDto.setGoodsMGroup(joinMaster.getGoodsMGroup());
        // BLコード
        joinMasterDto.setTbsPartsCode(joinMaster.getTbsPartsCode());
        // 結合元メーカーコード
        joinMasterDto.setJoinSourceMakerCode(joinMaster.getJoinSourceMakerCode());
        // 優良設定詳細コード２
        joinMasterDto.setPrmSetDtlNo2(joinMaster.getPrmSetDtlNo2());
        // 結合元品番(－付き品番)
        joinMasterDto.setJoinSourPartsNoWithH(joinMaster.getJoinSourPartsNoWithH());
        // 結合表示順位
        joinMasterDto.setJoinDispOrder(joinMaster.getJoinDispOrder());
        // 結合先品番(－付き品番)
        joinMasterDto.setJoinDestPartsNo(joinMaster.getJoinDestPartsNo());
        // 結合QTY
        joinMasterDto.setJoinQty(joinMaster.getJoinQty());
        // 結合規格・特記事項
        joinMasterDto.setJoinSpecialNote(joinMaster.getJoinSpecialNote());
        // 優良部品規格・特記事項(C向け)
        joinMasterDto.setPrimePartsSpecialNoteC(joinMaster.getPrimePartsSpecialNoteC());
        // 適用日付
        joinMasterDto.setStartTime(joinMaster.getStartTime());
        // 削除時申請理由
        joinMasterDto.setDeleteReason(joinMaster.getDeleteReason());
        // チェック区分
        joinMasterDto.setCheckFlg(joinMaster.getCheckFlg());
        // データステータス
        joinMasterDto.setErrorFlg(joinMaster.getErrorFlg());
        // BL登録区分
        joinMasterDto.setBlEntryFlg(joinMaster.getBlEntryFlg());
        // インポート区分
        joinMasterDto.setImportKbn(joinMaster.getImportKbn());
        // 処理区分
        joinMasterDto.setManageKbn(joinMaster.getManageKbn());
        // エラー内容
        joinMasterDto.setErrorDetail(joinMaster.getErrorDetail());
        // 削除依頼区分
        joinMasterDto.setDeleteFlg(joinMaster.getDeleteFlg());
        // 申請状態
        joinMasterDto.setApplyCondition(joinMaster.getApplyCondition());
        // 作成日時
        joinMasterDto.setInsDtTime(joinMaster.getInsDtTime());
        // 更新日時
        joinMasterDto.setUpdDtTime(joinMaster.getUpdDtTime());
        joinMasterDtoList.add(joinMasterDto);
      }
    }
    return joinMasterDtoList;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param joinMasterDtoList 結合マスタ(メーカー)Dto
   * @return 結合マスタ(メーカー)Model
   */
  private JoinMasterModel convertJoinMasterModel(List<JoinMasterDto> joinMasterDtoList) {
    JoinMasterModel joinMasterModel = new JoinMasterModel();
    if (null != joinMasterDtoList && joinMasterDtoList.size() > 0) {
      List<JoinMasterMaker> joinMasterList = new ArrayList<JoinMasterMaker>();
      for (JoinMasterDto joinMasterDto : joinMasterDtoList) {
        JoinMasterMaker joinMaster = new JoinMasterMaker();
        // 優良設定詳細コード１
        joinMaster.setPrmSetDtlNo1(joinMasterDto.getPrmSetDtlNo1());
        // 部品メーカーコード
        joinMaster.setPartsMakerCd(joinMasterDto.getPartsMakerCd());
        // 商品中分類コード
        joinMaster.setGoodsMGroup(joinMasterDto.getGoodsMGroup());
        // BLコード
        joinMaster.setTbsPartsCode(joinMasterDto.getTbsPartsCode());
        // 結合元メーカーコード
        joinMaster.setJoinSourceMakerCode(joinMasterDto.getJoinSourceMakerCode());
        // 優良設定詳細コード２
        joinMaster.setPrmSetDtlNo2(joinMasterDto.getPrmSetDtlNo2());
        // 結合元品番(－付き品番)
        joinMaster.setJoinSourPartsNoWithH(joinMasterDto.getJoinSourPartsNoWithH());
        // 結合表示順位
        joinMaster.setJoinDispOrder(joinMasterDto.getJoinDispOrder());
        // 結合先品番(－付き品番)
        joinMaster.setJoinDestPartsNo(joinMasterDto.getJoinDestPartsNo());
        // 結合QTY
        joinMaster.setJoinQty(joinMasterDto.getJoinQty());
        // 結合規格・特記事項
        joinMaster.setJoinSpecialNote(joinMasterDto.getJoinSpecialNote());
        // 優良部品規格・特記事項(C向け)
        joinMaster.setPrimePartsSpecialNoteC(joinMasterDto.getPrimePartsSpecialNoteC());
        // 適用日付
        joinMaster.setStartTime(joinMasterDto.getStartTime());
        // 削除時申請理由
        joinMaster.setDeleteReason(joinMasterDto.getDeleteReason());
        // チェック区分
        joinMaster.setCheckFlg(joinMasterDto.getCheckFlg());
        // データステータス
        joinMaster.setErrorFlg(joinMasterDto.getErrorFlg());
        // BL登録区分
        joinMaster.setBlEntryFlg(joinMasterDto.getBlEntryFlg());
        // インポート区分
        joinMaster.setImportKbn(joinMasterDto.getImportKbn());
        // 処理区分
        joinMaster.setManageKbn(joinMasterDto.getManageKbn());
        // エラー内容
        joinMaster.setErrorDetail(joinMasterDto.getErrorDetail());
        // 削除依頼区分
        joinMaster.setDeleteFlg(joinMasterDto.getDeleteFlg());
        // 申請状態
        joinMaster.setApplyCondition(joinMasterDto.getApplyCondition());
        joinMasterList.add(joinMaster);
      }
      joinMasterModel.setJoinMasterList(joinMasterList);
    }
    return joinMasterModel;
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

  /**
   * <pre>
   * BLコード名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return BLコード名称マップ
   */
  @Override
  public Map<String, String> getBlCodeNameMap(int makerCd) {
    Map<String, String> blCodeNameMap = new HashMap<String, String>();
    List<BlCodeMasterCommon> blCodeMasterInfo = blCodeMasterCommonService.getBlCodeMasterInfo(makerCd);
    if (blCodeMasterInfo != null && !blCodeMasterInfo.isEmpty()) {
      for (BlCodeMasterCommon blInfo : blCodeMasterInfo) {
        String blCode = String.valueOf(blInfo.getBlCode());
        if (!blCodeNameMap.containsKey(blCode)) {
          blCodeNameMap.put(blCode, blCode.concat("：").concat(blInfo.getBlFullName()));
        }
      }
    }
    return blCodeNameMap;
  }

  /**
   * <pre>
   * * セレクトコードマスト名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return BLコード名称マップ
   */
  @Override
  public Map<String, String> getSelectCodeNameMap(int makerCd) {
    Map<String, String> selectNameMap = new HashMap<String, String>();
    List<SelectCodeMasterCommon> searchSelectCode = selectCodeMasterCommonService.getSelectCodeInfo(makerCd);
    if (searchSelectCode != null && !searchSelectCode.isEmpty()) {
      for (SelectCodeMasterCommon selectInfo : searchSelectCode) {
        String selectCode = String.valueOf(selectInfo.getPrmSetDtlNo1());
        if (!selectNameMap.containsKey(selectCode)) {
          selectNameMap.put(selectCode, selectCode.concat("：").concat(selectInfo.getPrmSetDtlName()));
        }
      }
    }
    return selectNameMap;
  }

  /**
   * <pre>
   * * 種別コード名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 種別コード
   */
  @Override
  public Map<String, String> getKindCodeNameMap(int makerCd) {
    Map<String, String> kindCodeNameMap = new HashMap<String, String>();
    List<KindCodeMasterCommon> kindCodeInfo = kindCodeMasterCommonService.getKindCodeInfo(makerCd);
    if (kindCodeInfo != null && !kindCodeInfo.isEmpty()) {
      for (KindCodeMasterCommon kindCodeMaster : kindCodeInfo) {
        String kindCode = String.valueOf(kindCodeMaster.getKindCd());
        if (!kindCodeNameMap.containsKey(kindCode)) {
          kindCodeNameMap.put(kindCode, kindCode.concat("：").concat(kindCodeMaster.getKindName()));
        }
      }
    }
    return kindCodeNameMap;
  }

  /**
   * <pre>
   * * カーメーカーコード名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return カーメーカーコード
   */
  @Override
  public Map<String, String> getCarmakerNameMap(int makerCd) {
    Map<String, String> carmakerNameMap = new HashMap<String, String>();
    List<CarmakerMasterCommon> carmakerInfo = carmakerMasterCommonService.getCarmakerMasterInfo(makerCd);
    if (carmakerInfo != null && !carmakerInfo.isEmpty()) {
      for (CarmakerMasterCommon carmakerMasterCommon : carmakerInfo) {
        String carmakerCode = String.valueOf(carmakerMasterCommon.getMakerCode());
        if (!carmakerNameMap.containsKey(carmakerCode)) {
          carmakerNameMap.put(carmakerCode, carmakerCode.concat("：").concat(carmakerMasterCommon.getMakerFullName()));
        }
      }
    }
    return carmakerNameMap;
  }

  /**
   * <pre>
   * * 商品中分類コードマップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 商品中分類コード
   */
  @Override
  public Map<String, String> getClassifyCodeGuideMap(int makerCd) {
    Map<String, String> partsNameMap = new LinkedHashMap<String, String>();
    List<GoodsRateMasterCommon> searchClassifyCodeGuideCode = classifyCodeGuideService
        .getPrimeByCode(GoodsRateMasterCommon.LOGICAL_UNDELETED, makerCd, "", "");
    if (searchClassifyCodeGuideCode != null && !searchClassifyCodeGuideCode.isEmpty()) {
      for (GoodsRateMasterCommon classifyCodeGuide : searchClassifyCodeGuideCode) {
        String classifyCode = String.valueOf(classifyCodeGuide.getGoodsRateGrpCode());
        if (!partsNameMap.containsKey(classifyCode)) {
          partsNameMap.put(classifyCode, classifyCode.concat("：").concat(classifyCodeGuide.getGoodsRateGrpName()));
        }
      }
    }
    return partsNameMap;
  }

  /**
   * <pre>
   * * 優良品番マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 優良品番
   */
  @Override
  public Map<String, String> getPrimeCodeMap(int makerCd) {
    Map<String, String> primeCodeMap = new LinkedHashMap<String, String>();
    List<GoodsMasterMaker> goodsMasterMakerList = goodsGuideService
        .getPrimeByCode(GoodsRateMasterCommon.LOGICAL_UNDELETED, makerCd, "");
    if (goodsMasterMakerList != null && !goodsMasterMakerList.isEmpty()) {
      for (GoodsMasterMaker goodsMasterMaker : goodsMasterMakerList) {
        String goodsMasterMakerCode = String.valueOf(goodsMasterMaker.getPrimePartsNoWithH());
        if (!primeCodeMap.containsKey(goodsMasterMakerCode)) {
          primeCodeMap.put(goodsMasterMakerCode,
              goodsMasterMakerCode.concat("：").concat(goodsMasterMaker.getPrimePartsName()));
        }
      }
    }
    return primeCodeMap;
  }

  /**
   * <pre>
   * * 純正品番マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 純正品番
   */
  @Override
  public Map<String, String> getPureCodeMap(int makerCd) {
    Map<String, String> pureCodeMap = new LinkedHashMap<String, String>();
    List<PuregoodsMasterCommon> pureGoodsList = goodsGuideService
        .getPureGoodsByCode(PuregoodsMasterCommon.LOGICAL_UNDELETED, makerCd, "");
    if (pureGoodsList != null && !pureGoodsList.isEmpty()) {
      for (PuregoodsMasterCommon puregoodsMasterCommon : pureGoodsList) {
        String puregoodsCode = String.valueOf(puregoodsMasterCommon.getPrimePartsNoWithH());
        if (!pureCodeMap.containsKey(puregoodsCode)) {
          pureCodeMap.put(puregoodsCode, puregoodsCode.concat("：").concat(puregoodsMasterCommon.getPrimePartsName()));
        }
      }
    }
    return pureCodeMap;
  }

  /**
   * メッセージを取得します.
   * 
   * @param messageMap messageMap
   */
  public void getMessage(HashMap<String, String> messageMap) {
    messageMap.put(BregMessageCodes.Q00001, messageService.messageInfo(BregMessageCodes.Q00001) == null ? ""
        : messageService.messageInfo(BregMessageCodes.Q00001));
    messageMap.put(BregMessageCodes.Q00002, messageService.messageInfo(BregMessageCodes.Q00002) == null ? ""
        : messageService.messageInfo(BregMessageCodes.Q00002));
    messageMap.put(BregMessageCodes.E00008, messageService.getMessage("結合", "", BregMessageCodes.E00008));
    messageMap.put(BregMessageCodes.E00009, messageService.messageInfo(BregMessageCodes.E00009) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00009));
    messageMap.put(BregMessageCodes.E00010, messageService.messageInfo(BregMessageCodes.E00010) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00010));
    messageMap.put(BregMessageCodes.E00013, messageService.messageInfo(BregMessageCodes.E00013) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00013));
    messageMap.put(BregMessageCodes.E00014, messageService.messageInfo(BregMessageCodes.E00014) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00014));
    messageMap.put(BregMessageCodes.E00015, messageService.messageInfo(BregMessageCodes.E00015) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00015));
    messageMap.put(BregMessageCodes.E00016, messageService.messageInfo(BregMessageCodes.E00016) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00016));
    messageMap.put(BregMessageCodes.E00018, messageService.messageInfo(BregMessageCodes.E00018) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00018));
    messageMap.put(BregMessageCodes.E00011, messageService.getMessage("申請中", "結合", BregMessageCodes.E00011));
    messageMap.put(BregMessageCodes.E00012.concat("1"),
        messageService.getMessage("申請中", "結合", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.E00012.concat("2"),
        messageService.getMessage("インポート", "結合", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.E00012.concat("3"),
        messageService.getMessage("承認済", "結合", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.I00001, messageService.messageInfo(BregMessageCodes.I00001) == null ? ""
        : messageService.messageInfo(BregMessageCodes.I00001));

  }

  /**
   * 画面初期ロード時、機能制御の判断
   * 
   * @return 「True」：機能制御なし、「False」：機能制御あり
   */
  public Boolean isControl() {
    Short importKbn = 0;
    Short applyCondition = 0;
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    String updAccountId = loginPrincipal.getLoginId();
    int partsMakerCd = loginPrincipal.getMakerCode();
    int logicalDeleteCode = 0;
    List<GoodsMasterMaker> goodList = goodsMasterMakerService.searchGoodsInfoList(importKbn, applyCondition,
        updAccountId, partsMakerCd, logicalDeleteCode);
    List<SetMasterMaker> setList = setMasterService.searchSetInfoList(importKbn, applyCondition, updAccountId,
        partsMakerCd, logicalDeleteCode);
    List<JoinMasterMaker> joinList = joinMasterService.searchJoinInfoList(importKbn, applyCondition, updAccountId,
        partsMakerCd, logicalDeleteCode);
    return (goodList == null || goodList.isEmpty()) && (setList == null || setList.isEmpty())
        && (joinList == null || joinList.isEmpty());

  }

  /** 商品マスタ(メーカー)サービス */
  private GoodsMasterMakerService goodsMasterMakerService;

  /**
   * <pre>
   * 商品マスタ(メーカー)サービス.
   * </pre>
   * 
   * @param goodsMasterMakerService 商品マスタ(メーカー)サービス
   */
  @Resource
  public void setGoodsMasterMakerService(GoodsMasterMakerService goodsMasterMakerService) {
    this.goodsMasterMakerService = goodsMasterMakerService;
  }

  /** セットマスタ(メーカー)サービス */
  private SetMasterService setMasterService;

  /**
   * <pre>
   * セットマスタ(メーカー)サービス.
   * </pre>
   * 
   * @param setMasterService セットマスタ(メーカー)サービス
   */
  @Resource
  public void setSetMasterService(SetMasterService setMasterService) {
    this.setMasterService = setMasterService;
  }

  /** 結合マスタ(メーカー)サービス */
  private JoinMasterService joinMasterService;

  /**
   * <pre>
   * 結合マスタ(メーカー)サービス.
   * </pre>
   * 
   * @param joinMasterService 結合マスタ(メーカー)サービス
   */
  @Resource
  public void setJoinMasterService(JoinMasterService joinMasterService) {
    this.joinMasterService = joinMasterService;
  }

  /** BLコードマスタ情報サービス */
  private BlCodeMasterCommonService blCodeMasterCommonService;

  /**
   * <pre>
   * BLコードマスタ情報サービス.
   * </pre>
   * 
   * @param blCodeMasterCommonService BLコードマスタ情報サービス
   */
  @Resource
  public void setBlCodeMasterCommonService(BlCodeMasterCommonService blCodeMasterCommonService) {
    this.blCodeMasterCommonService = blCodeMasterCommonService;
  }

  /** セレクトコードマスト情報サービス */
  private SelectCodeMasterCommonService selectCodeMasterCommonService;

  /**
   * <pre>
   * セレクトコードマスト
   * </pre>
   * 
   * @param selectCodeMasterCommonService セレクトコードマスト
   */
  @Resource
  public void setSelectCodeMasterCommonService(SelectCodeMasterCommonService selectCodeMasterCommonService) {
    this.selectCodeMasterCommonService = selectCodeMasterCommonService;
  }

  /** 種別コードマスト情報サービス */
  private KindCodeMasterCommonService kindCodeMasterCommonService;

  /**
   * <pre>
   * 種別コードマスト
   * </pre>
   * 
   * @param kindCodeMasterCommonService 種別コードマスト
   */
  @Resource
  public void setKindCodeMasterCommonService(KindCodeMasterCommonService kindCodeMasterCommonService) {
    this.kindCodeMasterCommonService = kindCodeMasterCommonService;
  }

  /** カーメーカーコードマスタ情報サービス */
  private CarmakerMasterCommonService carmakerMasterCommonService;

  /**
   * <pre>
   * カーメーカーコードマスタ
   * </pre>
   * 
   * @param carmakerMasterCommonService カーメーカーコードマスタ
   */
  @Resource
  public void setCarmakerMasterCommonService(CarmakerMasterCommonService carmakerMasterCommonService) {
    this.carmakerMasterCommonService = carmakerMasterCommonService;
  }

  /** 会社情報サービス */
  private CompanyInfoMasterCommonService companyInfoMasterCommonService;

  /**
   * <pre>
   * 会社情報サービス.
   * </pre>
   * 
   * @param companyInfoMasterCommonService 会社情報サービス
   */
  @Resource
  public void setCompanyInfoMasterCommonService(CompanyInfoMasterCommonService companyInfoMasterCommonService) {
    this.companyInfoMasterCommonService = companyInfoMasterCommonService;
  }

  /** 結合マスタ(common)のサービス */
  private JoinMasterCommonService joinMasterCommonService;

  /**
   * <pre>
   * 結合マスタ(common)
   * </pre>
   * 
   * @param joinMasterCommonService 結合マスタ(common)
   */
  @Resource
  public void setJoinMasterCommonService(JoinMasterCommonService joinMasterCommonService) {
    this.joinMasterCommonService = joinMasterCommonService;
  }

  /** 中分類コード検索サビース **/
  private ClassifyCodeGuideService classifyCodeGuideService;

  /**
   * <pre>
   * 中分類コード検索サビースを設定する。
   * </pre>
   *
   * @param classifyCodeGuideService 中分類コード検索サビース
   */
  @Resource
  public void setClassifyCodeGuideService(ClassifyCodeGuideService classifyCodeGuideService) {
    this.classifyCodeGuideService = classifyCodeGuideService;
  }

  /** 優良/純正品番検索サビース **/
  private GoodsGuideService goodsGuideService;

  /**
   * <pre>
   * 優良/純正品番検索サビースを設定する。
   * </pre>
   *
   * @param goodsGuideService 優良/純正品番検索サビース
   */
  @Resource
  public void setGoodsGuideService(GoodsGuideService goodsGuideService) {
    this.goodsGuideService = goodsGuideService;
  }

  /** Messageサービス */
  private MessageService messageService;

  /**
   * Messageサービス.
   * 
   * @param messageService サービス
   */
  @Resource
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  /** チェック選択検索サビース **/
  private SelectMakerService selectMakerService;

  /**
   * <pre>
   * チェック選択検索サビースを設定する。
   * </pre>
   *
   * @param selectMakerService チェック選択検索サビース
   */
  @Resource
  public void setSelectMakerService(SelectMakerService selectMakerService) {
    this.selectMakerService = selectMakerService;
  }

  /**
   * 該当キーの結合マスタ（メーカー）本情報を取得
   * 
   * @param selectorCd セレクトコード
   * @param partsMakerCd 部品メーカーコード
   * @param blCode BLコード
   * @param joinMakerCd カーメーカーコード
   * @param kindCode 種別コード
   * @param joinPartsNo 純正品番
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto getJoinMasterInfo(String selectorCd, String partsMakerCd, String blCode, String joinMakerCd,
                                             String kindCode, String joinPartsNo) {
    JoinMasterInfoDto infoDto = new JoinMasterInfoDto();
    JoinMasterModel model = joinMasterService.getJoinMasterMakerInfo(selectorCd, partsMakerCd, blCode, joinMakerCd,
        kindCode, joinPartsNo);
    infoDto.setJoinMasterDto(convertJoinMasterInfoDto(model));
    return infoDto;
  }

  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param joinGridDtoList 結合一覧
   * @param makerCd メーカーコード
   * @return joinMasterInfoDto
   */
  public JoinMasterInfoDto checkImportList(List<JoinGridDto> joinGridDtoList, int makerCd) {
    JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
    int itemErrorCount = 0;
    // 結合マスタ(メーカー)案のチェック
    for (JoinGridDto joinGridDto : joinGridDtoList) {
      if (checkItemWork(joinGridDto, makerCd, joinGridDtoList)) {
        itemErrorCount += 1;
      }
    }
    joinMasterInfoDto.setJoinGridDtoList(joinGridDtoList);
    if (itemErrorCount == 0) {
      joinMasterInfoDto.setIsErrorExist(Boolean.FALSE);
    } else {
      joinMasterInfoDto.setIsErrorExist(Boolean.TRUE);
    }
    return joinMasterInfoDto;
  }

  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param joinGridDto 結合一覧
   * @param makerCd メーカーコード
   * @param joinGridDtoList 結合マスタ
   * @return 「True」：エラーあり、「False」：エラーなし
   */
  private boolean checkItemWork(JoinGridDto joinGridDto, int makerCd, List<JoinGridDto> joinGridDtoList) {
    getMessage();
    StringBuffer stringBuffer = new StringBuffer();
    StringBuffer errorInfo = new StringBuffer();
    errorInfo.append(DEFAULT);
    if (joinGridDto.getHiddenArea().intValue() == JudgeEnum.Delete.getValue()) {
      return false;
    }
    // 1.必須項目チェック 2.未入力項目チェック 5.文字桁数チェック
    stringBuffer.append(checkJoinPK(joinGridDto));
    // 3.商品マスタ存在チェック 4.商品マスタ状態チェック
    List<String> editList = checkGood(joinGridDto, makerCd);
    stringBuffer.append(editList.get(0));
    errorInfo.append(editList.get(1));
    boolean blcdMatchFlag = false;
    // カーメーカー品番チェック
    if (joinGridDto.getJoinSourceMakerCode() != null && joinGridDto.getJoinSourPartsNoWithH() != null
        && !joinGridDto.getJoinSourceMakerCode().isEmpty() && !joinGridDto.getJoinSourPartsNoWithH().isEmpty()
        && joinGridDto.getPrmSetDtlNo1() != null && !joinGridDto.getPrmSetDtlNo1().isEmpty()) {
      int joinSourceMakerCode = Integer.parseInt(joinGridDto.getJoinSourceMakerCode().split("：")[0]);
      int prmSetDtlNo1 = Integer.parseInt(joinGridDto.getPrmSetDtlNo1().split("：")[0]);
      List<PuregoodsMasterCommon> puregoodsMasterCommons = goodsGuideService.getPuregoodsMaster(
          joinGridDto.getJoinSourPartsNoWithH().split("：")[0], joinSourceMakerCode, makerCd, prmSetDtlNo1);
      if (puregoodsMasterCommons.isEmpty()) {
        // 純正品番体系チェック
        List<PuregoodsMasterCommon> puregoodsMasterCommonList = goodsGuideService.getPuregoodsMaster(null,
            joinSourceMakerCode, makerCd, prmSetDtlNo1);
        StringBuffer result = new StringBuffer();
        String joinPartNo = joinGridDto.getJoinSourPartsNoWithH().split("：")[0];
        joinPartNo = joinPartNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
        for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommonList) {
          String pureGoodsNo = puregoodsMasterCommon.getPrimePartsNoWithH();
          pureGoodsNo = pureGoodsNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
          // 結合元品番がPM部品マスタの純正品番一覧に無く､ハイフンやスペースの違いでPM部品マスタと一致する品番があれば､
          // 品番体系をPM部品マスタの体系へ変更した一覧を表示しエラーとする｡
          if (joinPartNo.equals(pureGoodsNo)) {
            result.append(MESSAGE_MAP.get(BregMessageCodes.E00704).replace(REPLACE,
                puregoodsMasterCommon.getPrimePartsNoWithH()));
            blcdMatchFlag = isBlcdMatch(puregoodsMasterCommon, joinGridDto);
            break;
          } else {
            if (Integer.parseInt(joinGridDto.getJoinSourceMakerCode().split("：")[0]) == 5) {
              if (joinPartNo.length() == 11 || (joinPartNo.length() > 11 && Character.isDigit(joinPartNo.charAt(11)))) {
                if (joinPartNo.startsWith(pureGoodsNo)) {
                  result.append(MESSAGE_MAP.get(BregMessageCodes.E00704).replace(REPLACE,
                      puregoodsMasterCommon.getPrimePartsNoWithH()));
                  blcdMatchFlag = isBlcdMatch(puregoodsMasterCommon, joinGridDto);
                  break;
                }
              } else if (joinPartNo.length() > 11 && Character.isLetter(joinPartNo.charAt(11))) {
                if (joinPartNo.equals(pureGoodsNo)) {
                  result.append(MESSAGE_MAP.get(BregMessageCodes.E00704).replace(REPLACE,
                      puregoodsMasterCommon.getPrimePartsNoWithH()));
                  blcdMatchFlag = isBlcdMatch(puregoodsMasterCommon, joinGridDto);
                  break;
                }
              }
            }
          }
        }
        if (result.toString().isEmpty()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00703));
          errorInfo.append(CARCD);
          errorInfo.append(PURE);
        } else {
          stringBuffer.append(result);
        }
      } else {
        // BLコードアンマッチチェック
        for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommons) {
          blcdMatchFlag = isBlcdMatch(puregoodsMasterCommon, joinGridDto);
          break;
        }
      }
    }
    // 8.純正品番重複チェック 13.表示順位の重複チェック
    String repeatErr = checkRepeat(joinGridDto, makerCd, stringBuffer, joinGridDtoList);
    errorInfo.append(repeatErr);
    // 11.削除申請理由チェック
    if (joinGridDto.getDeleteFlg() != null && joinGridDto.getDeleteFlg().equals(DeleteFlgEnum.Delete.getAbbreviation())
        && (joinGridDto.getDeleteReason() == null || joinGridDto.getDeleteReason().isEmpty())) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00017));
    }
    boolean errorExistFlag = getCheckResult(stringBuffer, joinGridDto, errorInfo);
    if (blcdMatchFlag) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.W00502));
      joinGridDto.setErrorDetail(stringBuffer.toString());
    }
    // 9.削除履歴存在チェック
    checkDelete(joinGridDto, stringBuffer, makerCd);
    return errorExistFlag;
  }

  /**
   * 結合マスタ(メーカー)案のチェック結果を取得
   * 
   * @param joinGridDto gridの一行のデータ
   * @param stringBuffer stringBuffer
   * @param errorInfo errorInfo
   * @return 「True」：エラーあり、「False」：エラーなし
   */
  private boolean getCheckResult(StringBuffer stringBuffer, JoinGridDto joinGridDto, StringBuffer errorInfo) {
    boolean errorExistFlag;
    if (stringBuffer.length() == 0) {
      joinGridDto.setErrorDetail(stringBuffer.toString());
      joinGridDto.setErrorFlg(ErrorFlgEnum.NoError.getAbbreviation());
      joinGridDto.setCheckFlg(CheckFlgEnum.Checked.getAbbreviation());
      errorExistFlag = false;
    } else {
      joinGridDto.setErrorDetail(stringBuffer.toString());
      joinGridDto.setErrorFlg(ErrorFlgEnum.Error.getAbbreviation());
      joinGridDto.setCheckFlg(CheckFlgEnum.Checked.getAbbreviation());
      joinGridDto.setErrorFlag(errorInfo.toString());
      errorExistFlag = true;
    }
    return errorExistFlag;
  }

  /**
   * 結合マスタ(メーカー)案の削除履歴存在チェック
   * 
   * @param joinGridDto gridの一行のデータ
   * @param stringBuffer stringBuffer
   * @param makerCd 部品メーカーコード
   */
  private void checkDelete(JoinGridDto joinGridDto, StringBuffer stringBuffer, int makerCd) {
    // 9.削除履歴存在チェック
    JoinMasterCommon joinMasterCommon = new JoinMasterCommon();
    joinMasterCommon.setPrmSetDtlNo1(
        joinGridDto.getPrmSetDtlNo1() == null ? 9999 : Integer.parseInt(joinGridDto.getPrmSetDtlNo1().split("：")[0]));
    joinMasterCommon.setPartsMakerCd(makerCd);
    joinMasterCommon.setTbsPartsCode(joinGridDto.getTbsPartsCode() == null || joinGridDto.getTbsPartsCode().isEmpty()
        ? 0 : Integer.parseInt(joinGridDto.getTbsPartsCode().split("：")[0]));
    joinMasterCommon.setJoinSourceMakerCode(
        joinGridDto.getJoinSourceMakerCode() == null || joinGridDto.getJoinSourceMakerCode().isEmpty() ? 0
            : Integer.parseInt(joinGridDto.getJoinSourceMakerCode().split("：")[0]));
    joinMasterCommon.setPrmSetDtlNo2(joinGridDto.getPrmSetDtlNo2() == null || joinGridDto.getPrmSetDtlNo2().isEmpty()
        ? 0 : Integer.parseInt(joinGridDto.getPrmSetDtlNo2().split("：")[0]));
    joinMasterCommon.setJoinSourPartsNoWithH(
        joinGridDto.getJoinSourPartsNoWithH() == null ? EMPTY : joinGridDto.getJoinSourPartsNoWithH().split("：")[0]);
    joinMasterCommon.setJoinDispOrder(joinGridDto.getJoinDispOrder() == null || joinGridDto.getJoinDispOrder().isEmpty()
        ? 0 : Integer.parseInt(joinGridDto.getJoinDispOrder()));
    List<JoinMasterCommon> joinMasterCommons = joinMasterCommonService.findJoin(joinMasterCommon);
    if (!joinMasterCommons.isEmpty()) {
      for (JoinMasterCommon common : joinMasterCommons) {
        if (common.getDeleteFlg().intValue() == DeleteFlgEnum.Delete.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.W00501));
          joinGridDto.setErrorDetail(stringBuffer.toString());
          break;
        }
      }
    }
  }

  /**
   * 結合マスタ(メーカー)案の商品マスタチェック.
   * 
   * @param joinGridDto gridの一行のデータ
   * @param makerCd 部品メーカーコード
   * @return チェック結果
   */
  private List<String> checkGood(JoinGridDto joinGridDto, int makerCd) {
    StringBuffer stringBuffer = new StringBuffer();
    List<String> checkList = new ArrayList<>();
    String goodCheck = DEFAULTADD;
    if (joinGridDto.getJoinDestPartsNo() != null && !joinGridDto.getJoinDestPartsNo().isEmpty()
        && joinGridDto.getPrmSetDtlNo1() != null && !joinGridDto.getPrmSetDtlNo1().isEmpty()) {
      // 3.商品マスタ存在チェック
      // 優良品番(－付き品番) = 引数.結合先品番
      String primePartsNoWithH = joinGridDto.getJoinDestPartsNo().split("：")[0];
      // 優良設定詳細コード１ = 引数.優良設定詳細コード１
      int prmSetDtlNo1 = Integer.parseInt(joinGridDto.getPrmSetDtlNo1().split("：")[0]);
      GoodsMasterMaker goodsMasterMaker = goodsMasterMakerService.searchGoodsById(prmSetDtlNo1, makerCd,
          primePartsNoWithH);
      if (goodsMasterMaker == null) {
        stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00701));
        goodCheck = PRIME_PARTS_NO_WITH_H;
      }
      // 4.商品マスタ状態チェック
      else {
        if (goodsMasterMaker.getApplyCondition() != null
            && goodsMasterMaker.getApplyCondition().intValue() == ApplyConditionEnum.NoApply.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00702)
              .replace(REPLACE, joinGridDto.getJoinDestPartsNo().split("：")[0]).replace(REPLACE2, NO_APPLY));
          goodCheck = PRIME_PARTS_NO_WITH_H;
        }
        if (goodsMasterMaker.getApplyCondition() != null
            && goodsMasterMaker.getApplyCondition().intValue() == ApplyConditionEnum.Apply.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00702)
              .replace(REPLACE, joinGridDto.getJoinDestPartsNo().split("：")[0]).replace(REPLACE2, APPLY));
          goodCheck = PRIME_PARTS_NO_WITH_H;
        }
      }
    }
    checkList.add(stringBuffer.toString());
    checkList.add(goodCheck);
    return checkList;
  }

  /**
   * 結合マスタ(メーカー)案の重複チェック.
   * 
   * @param joinGridDto gridの一行のデータ
   * @param makerCd メーカーコード
   * @param stringBuffer stringBuffer
   * @param joinGridDtoList gridのデータ
   * @return エラーチェック結果
   */
  private String checkRepeat(JoinGridDto joinGridDto, int makerCd, StringBuffer stringBuffer,
                             List<JoinGridDto> joinGridDtoList) {
    StringBuffer errorBuffer = new StringBuffer();
    // 純正品番重複チェック 新規登録場合
    if (joinGridDto.getPrmSetDtlNo1() != null && !joinGridDto.getPrmSetDtlNo1().isEmpty()
        && joinGridDto.getTbsPartsCode() != null && !joinGridDto.getTbsPartsCode().isEmpty()
        && joinGridDto.getJoinSourceMakerCode() != null && !joinGridDto.getJoinSourceMakerCode().isEmpty()
        && joinGridDto.getJoinSourPartsNoWithH() != null && !joinGridDto.getJoinSourPartsNoWithH().isEmpty()
        && joinGridDto.getJoinDispOrder() != null && !joinGridDto.getJoinDispOrder().isEmpty()
        && joinGridDto.getHiddenArea().intValue() == JudgeEnum.Add.getValue()) {
      JoinMasterMakerId joinMasterId = new JoinMasterMakerId();
      joinMasterId.setPrmSetDtlNo1(Integer.parseInt(joinGridDto.getPrmSetDtlNo1().split("：")[0]));
      joinMasterId.setPartsMakerCd(makerCd);
      joinMasterId.setTbsPartsCode(Integer.parseInt(joinGridDto.getTbsPartsCode().split("：")[0]));
      joinMasterId.setJoinSourceMakerCode(Integer.parseInt(joinGridDto.getJoinSourceMakerCode().split("：")[0]));
      joinMasterId.setJoinSourPartsNoWithH(joinGridDto.getJoinSourPartsNoWithH().split("：")[0]);
      joinMasterId.setJoinDispOrder(Integer.parseInt(joinGridDto.getJoinDispOrder()));
      if (joinMasterService.searchJoinMaster(joinMasterId, null) != null || isKeyRepeat(joinGridDto, joinGridDtoList)) {
        stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00705));
        errorBuffer.append(PURE);
        errorBuffer.append(ORDER);
      }
      if (joinMasterService.searchJoinMaster(joinMasterId, joinGridDto.getJoinDestPartsNo().split("：")[0]) != null
          || isJoinDestPartsNoRepeat(joinGridDto, joinGridDtoList)) {
        stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00706));
        errorBuffer.append(JOIN_DEST_PARTS_NO);
      }
    }
    return errorBuffer.toString();
  }

  /**
   * 純正品番と表示順位重複チェック
   * 
   * @param joinGridDto gridの一行のデータ
   * @param joinGridDtoList gridのデータ
   * @return boolean
   */
  private boolean isKeyRepeat(JoinGridDto joinGridDto, List<JoinGridDto> joinGridDtoList) {
    Boolean isKeyRepeat = false;
    int index = 0;
    for (JoinGridDto joinGridDtoOld : joinGridDtoList) {
      if (joinGridDtoOld.getHiddenArea().intValue() == JudgeEnum.Add.getValue() && joinGridDto.getPrmSetDtlNo1() != null
          && !joinGridDto.getPrmSetDtlNo1().isEmpty() && joinGridDto.getTbsPartsCode() != null
          && !joinGridDto.getTbsPartsCode().isEmpty() && joinGridDto.getJoinSourceMakerCode() != null
          && !joinGridDto.getJoinSourceMakerCode().isEmpty() && joinGridDto.getPrmSetDtlNo2() != null
          && !joinGridDto.getPrmSetDtlNo2().isEmpty() && joinGridDto.getJoinDispOrder() != null
          && !joinGridDto.getJoinDispOrder().isEmpty() && joinGridDtoOld.getPrmSetDtlNo1() != null
          && !joinGridDtoOld.getPrmSetDtlNo1().isEmpty() && joinGridDtoOld.getTbsPartsCode() != null
          && !joinGridDtoOld.getTbsPartsCode().isEmpty() && joinGridDtoOld.getJoinSourceMakerCode() != null
          && !joinGridDtoOld.getJoinSourceMakerCode().isEmpty() && joinGridDtoOld.getPrmSetDtlNo2() != null
          && !joinGridDtoOld.getPrmSetDtlNo2().isEmpty() && joinGridDtoOld.getJoinSourPartsNoWithH() != null
          && !joinGridDtoOld.getJoinSourPartsNoWithH().isEmpty() && joinGridDtoOld.getJoinDispOrder() != null
          && !joinGridDtoOld.getJoinDispOrder().isEmpty() && iskeyEquals(joinGridDtoOld, joinGridDto)) {
        index = index + 1;
      }
    }
    if (index > 1) {
      isKeyRepeat = true;
    }
    return isKeyRepeat;
  }

  /**
   * 純正品番と表示順位重複チェック
   * 
   * @param joinGridDtoOld gridの元データ行
   * @param joinGridDto gridの新規データ行
   * @return boolean
   */
  private boolean iskeyEquals(JoinGridDto joinGridDtoOld, JoinGridDto joinGridDto) {
    if (joinGridDtoOld.getPrmSetDtlNo1().equals(joinGridDto.getPrmSetDtlNo1())
        && joinGridDtoOld.getTbsPartsCode().equals(joinGridDto.getTbsPartsCode())
        && joinGridDtoOld.getJoinSourceMakerCode().equals(joinGridDto.getJoinSourceMakerCode())
        && joinGridDtoOld.getJoinSourPartsNoWithH().equals(joinGridDto.getJoinSourPartsNoWithH())
        && joinGridDtoOld.getJoinDispOrder().equals(joinGridDto.getJoinDispOrder())) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * 優良品番重複チェック
   * 
   * @param joinGridDto gridの一行のデータ
   * @param joinGridDtoList gridのデータ
   * @return boolean
   */
  private boolean isJoinDestPartsNoRepeat(JoinGridDto joinGridDto, List<JoinGridDto> joinGridDtoList) {
    Boolean isKeyRepeat = false;
    int index = 0;
    for (JoinGridDto joinGridDtoOld : joinGridDtoList) {
      if (joinGridDtoOld.getHiddenArea().intValue() == JudgeEnum.Add.getValue() && joinGridDto.getPrmSetDtlNo1() != null
          && !joinGridDto.getPrmSetDtlNo1().isEmpty() && joinGridDto.getTbsPartsCode() != null
          && !joinGridDto.getTbsPartsCode().isEmpty() && joinGridDto.getJoinSourceMakerCode() != null
          && !joinGridDto.getJoinSourceMakerCode().isEmpty() && joinGridDto.getPrmSetDtlNo2() != null
          && !joinGridDto.getPrmSetDtlNo2().isEmpty() && joinGridDto.getJoinDispOrder() != null
          && !joinGridDto.getJoinDispOrder().isEmpty() && joinGridDtoOld.getPrmSetDtlNo1() != null
          && !joinGridDtoOld.getPrmSetDtlNo1().isEmpty() && joinGridDtoOld.getTbsPartsCode() != null
          && !joinGridDtoOld.getTbsPartsCode().isEmpty() && joinGridDtoOld.getJoinSourceMakerCode() != null
          && !joinGridDtoOld.getJoinSourceMakerCode().isEmpty() && joinGridDtoOld.getPrmSetDtlNo2() != null
          && !joinGridDtoOld.getPrmSetDtlNo2().isEmpty() && joinGridDtoOld.getJoinSourPartsNoWithH() != null
          && !joinGridDtoOld.getJoinSourPartsNoWithH().isEmpty() && joinGridDtoOld.getJoinDispOrder() != null
          && !joinGridDtoOld.getJoinDispOrder().isEmpty() && joinGridDtoOld.getJoinDestPartsNo() != null
          && !joinGridDtoOld.getJoinDestPartsNo().isEmpty() && iskeyEquals(joinGridDtoOld, joinGridDto)
          && joinGridDtoOld.getJoinDestPartsNo().equals(joinGridDto.getJoinDestPartsNo())) {
        index = index + 1;
      }
    }
    if (index > 1) {
      isKeyRepeat = true;
    }
    return isKeyRepeat;
  }

  /**
   * BLコードアンマッチチェック
   * 
   * @param puregoodsMasterCommon 純正商品マスタ(共有)
   * @param joinGridDto 結合マスタ
   * @return 「True」：エラーあり、「False」：エラーなし
   */
  private boolean isBlcdMatch(PuregoodsMasterCommon puregoodsMasterCommon, JoinGridDto joinGridDto) {
    if (puregoodsMasterCommon.getTbsPartsCode() != null && joinGridDto.getTbsPartsCode() != null
        && !joinGridDto.getTbsPartsCode().isEmpty() && puregoodsMasterCommon.getTbsPartsCode()
            .equals(Integer.parseInt(joinGridDto.getTbsPartsCode().split("：")[0]))) {
      BlCodeMasterCommonId blCodeMasterCommonId = new BlCodeMasterCommonId(puregoodsMasterCommon.getPartsMakerCd(),
          puregoodsMasterCommon.getTbsPartsCode());
      if (blCodeMasterCommonService.getBlCodeMaster(blCodeMasterCommonId) == null) {
        return true;
      }
    }
    return false;
  }

  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param joinGridDto 結合マスタ
   * @return チェック結果
   */
  private String checkJoinPK(JoinGridDto joinGridDto) {
    StringBuffer stringBuffer = new StringBuffer();
    // 1.必須項目チェック
    // セレクトコード名称
    if (joinGridDto.getPrmSetDtlNo1() == null || joinGridDto.getPrmSetDtlNo1().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00001).replace(REPLACE, PRM_SET_DTL_NO_1));
    }
    // BLコード名称
    if (joinGridDto.getTbsPartsCode() == null || joinGridDto.getTbsPartsCode().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00001).replace(REPLACE, TBS_PARTS_CODE));
    }
    // カーコード名称
    if (joinGridDto.getJoinSourceMakerCode() == null || joinGridDto.getJoinSourceMakerCode().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00001).replace(REPLACE, JOIN_SOURCE_MAKER_CODE));
    }
    // 純正品番
    if (joinGridDto.getJoinSourPartsNoWithH() == null || joinGridDto.getJoinSourPartsNoWithH().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00001).replace(REPLACE, JOIN_SOUR_PARTS_NO_WITH_H));
    }
    // 種別コード名称
    if (joinGridDto.getPrmSetDtlNo2() == null || joinGridDto.getPrmSetDtlNo2().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00001).replace(REPLACE, PRM_SET_DTL_NO_2));
    }
    // 表示順位
    if (joinGridDto.getJoinDispOrder() == null || joinGridDto.getJoinDispOrder().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00001).replace(REPLACE, JOIN_DISP_ORDER));
    }
    // 2.未入力項目チェック
    // 分類コード名称
    if (joinGridDto.getGoodsMGroup() == null || joinGridDto.getGoodsMGroup().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, GOODS_M_GROUP));
    }
    // 優良品番
    if (joinGridDto.getJoinDestPartsNo() == null || joinGridDto.getJoinDestPartsNo().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, JOIN_DEST_PARTS_NO));
    }
    // 適用日時
    if (joinGridDto.getStartTime() == null || joinGridDto.getStartTime().isEmpty()) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, START_TIME));
    }
    // 5.文字桁数チェック
    if (joinGridDto.getJoinSourPartsNoWithH() != null
        && joinGridDto.getJoinSourPartsNoWithH().split("：")[0].length() > 20) {
      stringBuffer.append(
          MESSAGE_MAP.get(BregMessageCodes.E00003).replace(REPLACE, JOIN_SOUR_PARTS_NO_WITH_H).replace(REPLACE2, "20"));
    }
    return stringBuffer.toString();
  }

  /**
   * メッセージを取得します.
   */
  private void getMessage() {
    MESSAGE_MAP.put(BregMessageCodes.E00001, messageService.messageInfo(BregMessageCodes.E00001));
    MESSAGE_MAP.put(BregMessageCodes.E00002, messageService.messageInfo(BregMessageCodes.E00002));
    MESSAGE_MAP.put(BregMessageCodes.E00003, messageService.messageInfo(BregMessageCodes.E00003));
    MESSAGE_MAP.put(BregMessageCodes.E00008, messageService.messageInfo(BregMessageCodes.E00008));
    MESSAGE_MAP.put(BregMessageCodes.E00303, messageService.messageInfo(BregMessageCodes.E00303));
    MESSAGE_MAP.put(BregMessageCodes.E00301, messageService.messageInfo(BregMessageCodes.E00301));
    MESSAGE_MAP.put(BregMessageCodes.E00503, messageService.messageInfo(BregMessageCodes.E00503));
    MESSAGE_MAP.put(BregMessageCodes.E00502, messageService.messageInfo(BregMessageCodes.E00502));
    MESSAGE_MAP.put(BregMessageCodes.E00501, messageService.messageInfo(BregMessageCodes.E00501));
    MESSAGE_MAP.put(BregMessageCodes.E00701, messageService.messageInfo(BregMessageCodes.E00701));
    MESSAGE_MAP.put(BregMessageCodes.E00702, messageService.messageInfo(BregMessageCodes.E00702));
    MESSAGE_MAP.put(BregMessageCodes.E00703, messageService.messageInfo(BregMessageCodes.E00703));
    MESSAGE_MAP.put(BregMessageCodes.E00704, messageService.messageInfo(BregMessageCodes.E00704));
    MESSAGE_MAP.put(BregMessageCodes.E00705, messageService.messageInfo(BregMessageCodes.E00705));
    MESSAGE_MAP.put(BregMessageCodes.E00706, messageService.messageInfo(BregMessageCodes.E00706));
    MESSAGE_MAP.put(BregMessageCodes.E00707, messageService.messageInfo(BregMessageCodes.E00707));
    MESSAGE_MAP.put(BregMessageCodes.W00501, messageService.messageInfo(BregMessageCodes.W00501));
    MESSAGE_MAP.put(BregMessageCodes.E00017, messageService.messageInfo(BregMessageCodes.E00017));
    MESSAGE_MAP.put(BregMessageCodes.W00201, messageService.messageInfo(BregMessageCodes.W00201));
    MESSAGE_MAP.put(BregMessageCodes.W00502, messageService.messageInfo(BregMessageCodes.W00502));
  }

  /** 優良設定詳細コード１ **/
  public static final String PRM_SET_DTL_NO_1 = "セレクトコード名称";
  /** 商品中分類コード **/
  private static final String GOODS_M_GROUP = "分類コード名称";
  /** BLコード **/
  public static final String TBS_PARTS_CODE = "BLコード名称";
  /** 結合元メーカーコード **/
  public static final String JOIN_SOURCE_MAKER_CODE = "カーコード名称";
  /** 優良設定詳細コード２ **/
  public static final String PRM_SET_DTL_NO_2 = "種別コード名称";
  /** 結合元品番(－付き品番) **/
  public static final String JOIN_SOUR_PARTS_NO_WITH_H = "純正品番";
  /** 結合表示順位 **/
  public static final String JOIN_DISP_ORDER = "表示順位";
  /** 結合先品番(－付き品番) **/
  private static final String JOIN_DEST_PARTS_NO = "優良品番";
  /** 適用日時 **/
  public static final String START_TIME = "適用日時";
  /** 優良品番 **/
  public static final String PRIME_PARTS_NO_WITH_H = ",0";
  /** 純正品番 **/
  public static final String PURE = ",1";
  /** 表示順位 **/
  public static final String ORDER = ",2";
  /** 結合元メーカーコード **/
  public static final String CARCD = ",3";
  /** 初期値 **/
  public static final String DEFAULT = "-1";
  /** 固定値 **/
  public static final String DEFAULTADD = ",-1";
  /** 申請中 **/
  private static final String NO_APPLY = "未申請";
  /** 申請中 **/
  private static final String APPLY = "申請中";
  /** MessageMap **/
  private static final HashMap<String, String> MESSAGE_MAP = new HashMap<>();
  /** 文字列:半角-. */
  public static final String HALF_HYPHEN = "-";
  /** 文字列：空白文字. */
  public static final String EMPTY = "";
  /** 文字列：半角スペース. */
  public static final String HALF_SPACE = " ";
  /** 文字列：半角スペース. */
  public static final String REPLACE = "$1";
  /** 文字列：半角スペース. */
  public static final String REPLACE2 = "$2";
}
