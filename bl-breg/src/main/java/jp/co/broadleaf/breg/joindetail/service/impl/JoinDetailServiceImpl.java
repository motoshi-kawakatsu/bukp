//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 李超傑
// 作 成 日       2017/02/06   修正内容 : 結合詳細：新規作成
package jp.co.broadleaf.breg.joindetail.service.impl;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommonId;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;

import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommonId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;

import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommonId;

import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.joindetail.facade.dto.JoinDetailDto;
import jp.co.broadleaf.breg.joindetail.service.JoinDetailService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.common.util.BroadleafStringUtils;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import org.apache.commons.lang3.Validate;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <pre>
 * 結合詳細サービスクラスです。
 * </pre>
 */
public class JoinDetailServiceImpl implements JoinDetailService {
    /**
     * 結合マストリストを取得します。
     * 
     * @param prmSetDtlNo1
     *            優良設定詳細コード１
     * @param partsMakerCd
     *            部品メーカーコード
     * @param tbsPartsCode
     *            BLコード
     * @param joinSourceMakerCode
     *            結合元メーカーコード
     * @param prmSetDtlNo2
     *            優良設定詳細コード２
     * @param joinSourPartsNoWithH
     *            結合元品番(－付き品番)
     * @return 結合詳細のDTOリスト
     */
	@Override
    public List<JoinMasterMaker> getJoinDetail(String prmSetDtlNo1, String partsMakerCd, String tbsPartsCode,
            String joinSourceMakerCode, String prmSetDtlNo2, String joinSourPartsNoWithH) {
        Validate.notNull(prmSetDtlNo1, "prmSetDtlNo1 must not be null");
        Validate.notNull(partsMakerCd, "partsMakerCd must not be null");
        Validate.notNull(tbsPartsCode, "tbsPartsCode must not be null");
        Validate.notNull(joinSourceMakerCode, "joinSourceMakerCode must not be null");
        Validate.notNull(prmSetDtlNo2, "prmSetDtlNo2 must not be null");
        Validate.notNull(joinSourPartsNoWithH, "joinSourPartsNoWithH must not be null");

        JoinMasterMaker searchCmd = new JoinMasterMaker();
        searchCmd.setPrmSetDtlNo1(Integer.parseInt(prmSetDtlNo1));
        searchCmd.setPartsMakerCd(Integer.parseInt(partsMakerCd));
        searchCmd.setTbsPartsCode(Integer.parseInt(tbsPartsCode));
        searchCmd.setJoinSourceMakerCode(Integer.parseInt(joinSourceMakerCode));
        searchCmd.setPrmSetDtlNo2(Integer.parseInt(prmSetDtlNo2));
        searchCmd.setJoinSourPartsNoWithH(joinSourPartsNoWithH);
        searchCmd.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());

        return joinMasterMakerDao.findByExample(searchCmd);
    }

    /**
     * 結合マスタ(メーカー)案のチェック.
     * 
     * @param joinDetailDto
     *            結合マスタ(メーカー)案
     * @param makerCd
     *            makerCd
     *            @param messageMap messageMap
     *            @param mode mode
     * @return 「True」：エラーなし、「False」：エラーあり
     */
	@Override
    public boolean checkJoin(JoinDetailDto joinDetailDto, int makerCd, Map<String, String> messageMap, int mode) {
        // 部品メーカーコード = 引数.部品メーカーコード
        NumberFilter goodsRateMakerCd = NumberFilterBuilder.equals(GoodsRateMasterCommon.GOODS_MAKER, makerCd);
        Filter goodsRateFilter = new AndFilter(goodsRateMakerCd);
        List<GoodsRateMasterCommon> goodsRateMasterCommons = goodsRateMasterCommonDao.findByFilter(goodsRateFilter,
                Limit.NO_LIMIT);
        for (GoodsRateMasterCommon goodsRateMasterCommon : goodsRateMasterCommons) {
            GOODS_RATE_CODE.add(goodsRateMasterCommon.getGoodsRateGrpCode());
        }

        // 部品メーカーコード = 引数.部品メーカーコード
        NumberFilter blCodeMakerCd = NumberFilterBuilder.equals(BlCodeMasterCommon.GOODS_MAKER, makerCd);
        Filter blCodeFilter = new AndFilter(blCodeMakerCd);
        List<BlCodeMasterCommon> blCodeMasterCommons = blCodeMasterCommonDao.findByFilter(blCodeFilter, Limit.NO_LIMIT);
        for (BlCodeMasterCommon blCodeMasterCommon : blCodeMasterCommons) {
            BL_CODE_SET.add(blCodeMasterCommon.getBlCode());
        }

        // 部品メーカーコード = 引数.部品メーカーコード
        NumberFilter selectMakerCd = NumberFilterBuilder.equals(SelectCodeMasterCommon.GOODS_MAKER_CD, makerCd);
        Filter selectFilter = new AndFilter(selectMakerCd);
        List<SelectCodeMasterCommon> selectCodeMasterCommons = selectCodeMasterCommonDao.findByFilter(selectFilter,
                Limit.NO_LIMIT);
        for (SelectCodeMasterCommon selectCodeMasterCommon : selectCodeMasterCommons) {
            SELECT_CODE_SET.add(selectCodeMasterCommon.getPrmSetDtlNo1());
        }

        StringBuffer stringBuffer = new StringBuffer();
        // 1: 必須項目チェック 2:未入力項目チェック3商品マスタ存在チェック4商品マスタ状態チェック11削除申請理由チェックcheck
        // 2.3.6
        String checkErrorMsg = checkMustInput(joinDetailDto).toString();
        if (!checkErrorMsg.isEmpty()) {
            stringBuffer.append(checkErrorMsg);
            joinDetailDto.setErrorDetail(stringBuffer.toString());
            return false;
        }

        stringBuffer.append(checkDigit(joinDetailDto));
        stringBuffer.append(checkJoin0102(joinDetailDto, messageMap, mode, makerCd));

        boolean check10 = false;
        NumberFilter makerCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.PARTS_MAKER_CD, makerCd);
        NumberFilter partsMakerCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.JOIN_SOURCE_MAKER_CODE,
                Integer.parseInt(joinDetailDto.getJoinSourceMakerCode().split("：")[0]));
        StringFilter primePartsNoWithHFilter = StringFilterBuilder.equals(PuregoodsMasterCommon.PRIME_PARTS_NO_WITH_H,
                joinDetailDto.getJoinSourPartsNoWithH().split("：")[0]);
        Filter pureFilter = new AndFilter(makerCdFilter, partsMakerCdFilter, primePartsNoWithHFilter);
        List<PuregoodsMasterCommon> puregoodsMasterCommons = puregoodsMasterCommonDao.findByFilter(pureFilter,
                Limit.NO_LIMIT);
        if (puregoodsMasterCommons.isEmpty()) {
            // check 2.3.7
            Filter pureFilter2 = new AndFilter(makerCdFilter, partsMakerCdFilter);
            List<PuregoodsMasterCommon> puregoodsMasterCommonList = puregoodsMasterCommonDao.findByFilter(pureFilter2,
                    Limit.NO_LIMIT);
            StringBuffer result = new StringBuffer();
            String joinPartNo = joinDetailDto.getJoinSourPartsNoWithH().split("：")[0];
            joinPartNo = joinPartNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
            for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommonList) {
                String pureGoodsNo = puregoodsMasterCommon.getPrimePartsNoWithH();
                pureGoodsNo = pureGoodsNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
                // 結合元品番がPM部品マスタの純正品番一覧に無く､ハイフンやスペースの違いでPM部品マスタと一致する品番があれば､
                // 品番体系をPM部品マスタの体系へ変更した一覧を表示しエラーとする｡
                if (joinPartNo.equals(pureGoodsNo)) {
                    result.append(messageMap.get(BregMessageCodes.E00704).replace(REPLACE,
                            puregoodsMasterCommon.getPrimePartsNoWithH()));
                    check10 = check10(puregoodsMasterCommon, joinDetailDto);
                    break;
                } else {
                    if (makerCd == 005) {
                        if (joinPartNo.length() == 11
                                || (joinPartNo.length() > 11 && Character.isDigit(joinPartNo.charAt(11)))) {
                            if (joinPartNo.startsWith(pureGoodsNo)) {
                                result.append(messageMap.get(BregMessageCodes.E00704).replace(REPLACE,
                                        puregoodsMasterCommon.getPrimePartsNoWithH()));
                                check10 = check10(puregoodsMasterCommon, joinDetailDto);
                                break;
                            }
                        } else if (joinPartNo.length() > 11 && Character.isLetter(joinPartNo.charAt(11))) {
                            if (joinPartNo.equals(pureGoodsNo)) {
                                result.append(messageMap.get(BregMessageCodes.E00704).replace(REPLACE,
                                        puregoodsMasterCommon.getPrimePartsNoWithH()));
                                check10 = check10(puregoodsMasterCommon, joinDetailDto);
                                break;
                            }
                        }
                    }
                }
            }
            if (result.toString().isEmpty()) {
                stringBuffer.append(messageMap.get(BregMessageCodes.E00703));
            } else {
                stringBuffer.append(result);
            }
        } else {
            for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommons) {
                check10 = check10(puregoodsMasterCommon, joinDetailDto);
                break;
            }
        }
        boolean errorExistFlag = false;
        if (stringBuffer.length() == 0) {
            joinDetailDto.setErrorDetail(stringBuffer.toString());
            joinDetailDto.setErrorFlg(ErrorFlgEnum.NoError.getAbbreviation());
            joinDetailDto.setCheckFlg(CheckFlgEnum.Checked.getAbbreviation());
            errorExistFlag = true;
        } else {
            joinDetailDto.setErrorDetail(stringBuffer.toString());
            joinDetailDto.setErrorFlg(ErrorFlgEnum.Error.getAbbreviation());
            joinDetailDto.setCheckFlg(CheckFlgEnum.Checked.getAbbreviation());
            errorExistFlag = false;
        }
        if (check10) {
            stringBuffer.append(messageMap.get(BregMessageCodes.W00502));
        }
        // check 2.3.9
        JoinMasterCommon joinMasterCommon = new JoinMasterCommon();
        joinMasterCommon.setPrmSetDtlNo1(Integer.parseInt(joinDetailDto.getPrmSetDtlNo1().split("：")[0]));
        joinMasterCommon.setPartsMakerCd(makerCd);
        joinMasterCommon.setTbsPartsCode(Integer.parseInt(joinDetailDto.getTbsPartsCode().split("：")[0]));
        joinMasterCommon.setJoinSourceMakerCode(Integer.parseInt(joinDetailDto.getJoinSourceMakerCode().split("：")[0]));
        joinMasterCommon.setPrmSetDtlNo2(Integer.parseInt(joinDetailDto.getPrmSetDtlNo2().split("：")[0]));
        joinMasterCommon.setJoinSourPartsNoWithH(joinDetailDto.getJoinSourPartsNoWithH().split("：")[0]);
        joinMasterCommon.setJoinDispOrder(joinDetailDto.getJoinDispOrder());
        List<JoinMasterCommon> joinMasterCommons = joinMasterCommonDao.findByExample(joinMasterCommon);
        if (!joinMasterCommons.isEmpty()) {
            for (JoinMasterCommon common : joinMasterCommons) {
                if (common.getDeleteFlg() != null
                        && common.getDeleteFlg().intValue() == DeleteFlgEnum.Delete.getValue()) {
                    stringBuffer.append(messageMap.get(BregMessageCodes.W00501));
                    joinDetailDto.setErrorDetail(stringBuffer.toString());
                    break;
                }
            }
        }
        return errorExistFlag;
    }

    /**
     * 結合マスタ(メーカー)案のチェック.
     * 
     * @param joinDetailDto
     *            結合マスタ
     * @param messageMap
     *            messageMap
     *            @param mode mode
     *            @param makerCd makerCd
     * @return チェック結果
     */
    private String checkJoin0102(JoinDetailDto joinDetailDto, Map<String, String> messageMap, int mode, int makerCd) {
        StringBuffer stringBuffer = new StringBuffer();
        // check 2.2.3 商品マスタ存在チェック
        if (!BroadleafStringUtils.isEmpty(joinDetailDto.getJoinDestPartsNo())) {

          NumberFilter ftLogicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV,
              DeleteFlgEnum.NoDelete.getValue());
          NumberFilter ftPrmSetDtlNo1 = NumberFilterBuilder.equals(GoodsMasterMaker.PRM_SET_DTL_NO_1,
              Integer.parseInt(joinDetailDto.getPrmSetDtlNo1().split("：")[0]));
          NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD,
              makerCd);
          StringFilter ftPrimePartsNoWithH = StringFilterBuilder.equals(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H,
              joinDetailDto.getJoinDestPartsNo().split("：")[0]);
          NumberFilter blCode = NumberFilterBuilder.equals(GoodsMasterMaker.BL_ENTRY_FLG,
              BlEntryFlgEnum.Registered.getValue());
          Filter filter = new AndFilter(ftLogicalDelDiv, ftPrmSetDtlNo1, ftPartsMakerCd, ftPrimePartsNoWithH, blCode);
          List<GoodsMasterMaker> goods = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
          if (!BroadleafUtils.isEmpty(goods)) {
            GoodsMasterMaker goodsMasterMaker = goods.get(0);
            if (joinDetailDto.getJoinDestPartsNo().split("：")[0].equals(goodsMasterMaker.getPrimePartsNoWithH())
                && goodsMasterMaker.getApplyCondition() != null
                && goodsMasterMaker.getApplyCondition() == ApplyConditionEnum.NoApply.getValue()) {
              stringBuffer.append(messageMap.get(BregMessageCodes.E00702)
                  .replace(REPLACE, joinDetailDto.getJoinDestPartsNo().split("：")[0]).replace(REPLACE2, NO_APPLY));
            }
            if (joinDetailDto.getJoinDestPartsNo().split("：")[0].equals(goodsMasterMaker.getPrimePartsNoWithH())
                && goodsMasterMaker.getApplyCondition() != null
                && goodsMasterMaker.getApplyCondition() == ApplyConditionEnum.Apply.getValue()) {
              stringBuffer.append(messageMap.get(BregMessageCodes.E00702)
                  .replace(REPLACE, joinDetailDto.getJoinDestPartsNo().split("：")[0]).replace(REPLACE2, APPLY));
            }

          } else {
            stringBuffer.append(messageMap.get(BregMessageCodes.E00701));
          }
        }
        // check 2.3.11 削除申請理由チェック
        if (joinDetailDto.getDeleteFlg() != null
                && joinDetailDto.getDeleteFlg().equals(DeleteFlgEnum.Delete.getAbbreviation())
                && (joinDetailDto.getDeleteReason() == null || joinDetailDto.getDeleteReason().isEmpty())) {
            stringBuffer.append(messageMap.get(BregMessageCodes.E00017));
        }
        // check2.3.12セレクトコード、分類コード、BLコード存在チェック

        if (SELECT_CODE_SET.add(Integer.parseInt(joinDetailDto.getPrmSetDtlNo1().split("：")[0]))
                ? SELECT_CODE_SET.remove(Integer.parseInt(joinDetailDto.getPrmSetDtlNo1().split("：")[0])) : false) {
            stringBuffer.append(messageMap.get(BregMessageCodes.E00008).replace(REPLACE, PRM_SET_DTL_NO_1));
        }
        if (BL_CODE_SET.add(Integer.parseInt(joinDetailDto.getTbsPartsCode().split("：")[0]))
                ? BL_CODE_SET.remove(Integer.parseInt(joinDetailDto.getTbsPartsCode().split("：")[0])) : false) {
            stringBuffer.append(messageMap.get(BregMessageCodes.E00008).replace(REPLACE, TBS_PARTS_CODE));
        }
        if (joinDetailDto.getGoodsMGroup() != null
                && GOODS_RATE_CODE.add(Integer.parseInt(joinDetailDto.getGoodsMGroup().split("：")[0]))
                        ? GOODS_RATE_CODE.remove(joinDetailDto.getGoodsMGroup().split("：")[0]) : false) {
            stringBuffer.append(messageMap.get(BregMessageCodes.E00008).replace(REPLACE, GOODS_M_GROUP));
        }
        NumberFilter makerCdFilter = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, makerCd);
        NumberFilter prmSetDtlNo1Filter = NumberFilterBuilder.equals(JoinMasterMaker.PRM_SET_DTL_NO_1,
                Integer.parseInt(joinDetailDto.getPrmSetDtlNo1().split("：")[0]));
        NumberFilter tbsPartsCodeFilter = NumberFilterBuilder.equals(JoinMasterMaker.TBS_PARTS_CODE,
                Integer.parseInt(joinDetailDto.getTbsPartsCode().split("：")[0]));
        NumberFilter joinSourceMakerCodeFilter = NumberFilterBuilder.equals(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE,
                Integer.parseInt(joinDetailDto.getJoinSourceMakerCode().split("：")[0]));
        StringFilter joinSourPartsNoWithHFilter = StringFilterBuilder.equals(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H,
                joinDetailDto.getJoinSourPartsNoWithH());
        Filter joinMasterMakerFilter = new AndFilter(makerCdFilter, prmSetDtlNo1Filter, tbsPartsCodeFilter,
                joinSourceMakerCodeFilter, joinSourPartsNoWithHFilter);
        List<JoinMasterMaker> joinMasterMaker = joinMasterMakerDao.findByFilter(joinMasterMakerFilter, Limit.NO_LIMIT);
        // 新規の場合とエラー修正の場合
        if (mode == MODE_ADD || mode == MODE_ERROR) {
            if (joinMasterMaker != null && !joinMasterMaker.isEmpty()) {
                stringBuffer.append(messageMap.get(BregMessageCodes.E00705));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 結合詳細の必須項目チェックと未入力項目チェック
     * 
     * @param joinDetailDto JoinDetailDto
     * @return チェック結果
     */
    private StringBuffer checkMustInput(JoinDetailDto joinDetailDto) {
      StringBuffer stringBuffer = new StringBuffer();
      // 1: 必須項目チェック
      if (joinDetailDto.getPrmSetDtlNo1() == null || joinDetailDto.getPrmSetDtlNo1().isEmpty()) {
        stringBuffer.append(messageService.getMessage("セレクトコード", "", BregMessageCodes.E00001));
      }
      if (joinDetailDto.getJoinSourceMakerCode() == null || joinDetailDto.getJoinSourceMakerCode().isEmpty()) {
        stringBuffer.append(messageService.getMessage("カーコード", "", BregMessageCodes.E00001));
      }
      if (joinDetailDto.getJoinSourPartsNoWithH() == null || joinDetailDto.getJoinSourPartsNoWithH().isEmpty()) {
        stringBuffer.append(messageService.getMessage("純正品番", "", BregMessageCodes.E00001));
      }
      if (joinDetailDto.getTbsPartsCode() == null || joinDetailDto.getTbsPartsCode().isEmpty()) {
        stringBuffer.append(messageService.getMessage("BLコード", "", BregMessageCodes.E00001));
      }
      if (joinDetailDto.getPrmSetDtlNo2() == null || joinDetailDto.getPrmSetDtlNo2().isEmpty()) {
        stringBuffer.append(messageService.getMessage("種別コード", "", BregMessageCodes.E00001));
      }
      if (joinDetailDto.getJoinDispOrder() == null) {
        stringBuffer.append(messageService.getMessage("表示順位", "", BregMessageCodes.E00001));
      }
      if (joinDetailDto.getJoinDestPartsNo() == null || joinDetailDto.getJoinDestPartsNo().isEmpty()) {
        stringBuffer.append(messageService.getMessage("優良品番", "", BregMessageCodes.E00001));
      }
      if (joinDetailDto.getJoinQty() == null) {
        stringBuffer.append(messageService.getMessage("QTY", "", BregMessageCodes.E00002));
      }
      if (joinDetailDto.getStartTime() == null || joinDetailDto.getStartTime().isEmpty()) {
        stringBuffer.append(messageService.getMessage("適用日時", "", BregMessageCodes.E00001));
      }
      return stringBuffer;
    }

    /**
     * 結合詳細の文字桁数チェック
     * 
     * @param joinDetailDto JoinDetailDto
     * @return チェック結果
     */
    private String checkDigit(JoinDetailDto joinDetailDto) {
      StringBuffer stringBuffer = new StringBuffer();
      if (joinDetailDto.getPrmSetDtlNo2().length() > 4) {
        stringBuffer.append(messageService.getMessage("種別コード", "4", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getJoinDispOrder().toString().length() > 4) {
        stringBuffer.append(messageService.getMessage("表示順位", "4", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getJoinDestPartsNo() != null && joinDetailDto.getJoinDestPartsNo().length() > 24) {
        stringBuffer.append(messageService.getMessage("優良品番", "24", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getPrimePartsName() != null && joinDetailDto.getPrimePartsName().length() > 60) {
        stringBuffer.append(messageService.getMessage("優良品名", "60", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getJoinQty() != null && joinDetailDto.getJoinQty().toString().length() > 5) {
        stringBuffer.append(messageService.getMessage("QTY", "5", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getJoinSpecialNote() != null && joinDetailDto.getJoinSpecialNote().length() > 80) {
        stringBuffer.append(messageService.getMessage("規格/特記", "80", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getPrimePartsSpecialNoteC() != null && joinDetailDto.getPrimePartsSpecialNoteC().length() > 80) {
        stringBuffer.append(messageService.getMessage("規格/特記（一般）", "80", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getDeleteFlg() != null && joinDetailDto.getDeleteFlg().length() > 8) {
        stringBuffer.append(messageService.getMessage("削除依頼区分", "8", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getDeleteReason() != null && joinDetailDto.getDeleteReason().length() > 80) {
        stringBuffer.append(messageService.getMessage("削除理由", "80", BregMessageCodes.E00003));
      }
      if (joinDetailDto.getStartTime() != null && joinDetailDto.getStartTime().length() > 16) {
        stringBuffer.append(messageService.getMessage("適用日時", "16", BregMessageCodes.E00003));
      }
      return stringBuffer.toString();
    }

    /**
     * 結合マスタ(メーカー)案のチェック.
     * 
     * @param puregoodsMasterCommon
     *            純正商品マスタ(共有)
     * @param joinDetailDto
     *            結合マスタ
     * @return チェック結果
     */
    private boolean check10(PuregoodsMasterCommon puregoodsMasterCommon, JoinDetailDto joinDetailDto) {
        if (puregoodsMasterCommon.getTbsPartsCode() != null
                && puregoodsMasterCommon.getTbsPartsCode().equals(joinDetailDto.getTbsPartsCode().split("：")[0])) {

            BlCodeMasterCommonId blCodeMasterCommonId = new BlCodeMasterCommonId(
                    puregoodsMasterCommon.getJoinSourceMakerCode(), puregoodsMasterCommon.getTbsPartsCode());
            if (blCodeMasterCommonDao.findById(blCodeMasterCommonId) == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 表示順位重複チェック
     * 
     * @param joinDetailDtoList
     *            JoinDetailDto
     * @return チェック結果
     */
    @Override
    public boolean checkDispOrder(List<JoinDetailDto> joinDetailDtoList) {
        boolean errorFlag = false;

        for (int i = 0; i < joinDetailDtoList.size(); i++) {

            for (int count = i + 1; count < joinDetailDtoList.size(); count++) {

                if (joinDetailDtoList.get(i).getJoinDispOrder()
                        .equals(joinDetailDtoList.get(count).getJoinDispOrder())) {

                    errorFlag = true;

                    if (joinDetailDtoList.get(i).getErrorDetail()
                            .indexOf(messageService.messageInfo(BregMessageCodes.E00707)) < 0) {
                        joinDetailDtoList.get(i).setErrorDetail(joinDetailDtoList.get(i).getErrorDetail()
                                .concat(messageService.messageInfo(BregMessageCodes.E00707)));
                    }

                    if (joinDetailDtoList.get(count).getErrorDetail()
                            .indexOf(messageService.messageInfo(BregMessageCodes.E00707)) < 0) {
                        joinDetailDtoList.get(count).setErrorDetail(joinDetailDtoList.get(count).getErrorDetail()
                                .concat(messageService.messageInfo(BregMessageCodes.E00707)));
                    }
                }
            }

        }

        return errorFlag;
    }

    /**
     * 商品マスタ存在チェック
     * 
     * @param joinDetailDtoList
     *            JoinDetailDto
     * @return チェック結果
     */
    @Override
    public boolean checkGoodsMasterIsExist(List<JoinDetailDto> joinDetailDtoList) {
        boolean errorFlag = false;
        for (int i = 0; i < joinDetailDtoList.size(); i++) {

            for (int count = i + 1; count < joinDetailDtoList.size(); count++) {

                if (joinDetailDtoList.get(i).getJoinDestPartsNo()
                        .equals(joinDetailDtoList.get(count).getJoinDestPartsNo())) {

                    errorFlag = true;

                    if (joinDetailDtoList.get(i).getErrorDetail()
                            .indexOf(messageService.messageInfo(BregMessageCodes.E00301)) < 0) {
                        joinDetailDtoList.get(i).setErrorDetail(joinDetailDtoList.get(i).getErrorDetail()
                                .concat(messageService.messageInfo(BregMessageCodes.E00301)));
                    }

                    if (joinDetailDtoList.get(count).getErrorDetail()
                            .indexOf(messageService.messageInfo(BregMessageCodes.E00301)) < 0) {
                        joinDetailDtoList.get(count).setErrorDetail(joinDetailDtoList.get(count).getErrorDetail()
                                .concat(messageService.messageInfo(BregMessageCodes.E00301)));
                    }
                }
            }

        }

        return errorFlag;
    }

    /** 商品DAO */
    private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao;
    /** 結合DAO */
    private GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao;
    /** 結合DAO **/
    private GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao;
    /** 純正商品マスタDAO **/
    private GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> puregoodsMasterCommonDao;
    /** BLコードDAO **/
    private GenericDao<BlCodeMasterCommon, BlCodeMasterCommonId> blCodeMasterCommonDao;
    /** セレクトコードDAO **/
    private GenericDao<SelectCodeMasterCommon, SelectCodeMasterCommonId> selectCodeMasterCommonDao;
    /** BLコードDAO **/
    private GenericDao<GoodsRateMasterCommon, Integer> goodsRateMasterCommonDao;
    /** BLコードSet **/
    private static final HashSet<Integer> BL_CODE_SET = new HashSet<Integer>();
    /** セレクトコードSet **/
    private static final HashSet<Integer> SELECT_CODE_SET = new HashSet<Integer>();
    /** 分類コードSet **/
    private static final HashSet<Integer> GOODS_RATE_CODE = new HashSet<Integer>();
    /** セット親品番 **/
    public static final String SET_MAIN_PARTS_NO = "セット親品番";
    /** セット表示順位 **/
    public static final String SET_DISP_ORDER = "セット表示順位";
    /** 結合元メーカーコード **/
    public static final String JOIN_SOURCE_MAKER_CODE = "結合元メーカーコード";
    /** 優良設定詳細コード２ **/
    public static final String PRM_SET_DTL_NO_2 = "優良設定詳細コード２";
    /** 優良設定詳細コード２ **/
    public static final String PRM_SET_DTL_NO_1 = "セレクトコード";
    /** 結合表示順位 **/
    public static final String JOIN_DISP_ORDER = "結合表示順位";
    /** 商品中分類コード **/
    private static final String GOODS_M_GROUP = "商品中分類コード";
    /** BLコード **/
    private static final String TBS_PARTS_CODE = "BLコード";
    /** 未申請 **/
    private static final String NO_APPLY = "未申請";
    /** 申請中 **/
    private static final String APPLY = "申請中";
    /** 画像数 **/
    public static final String IMAGE_NO = "画像数";
    /** 適用日時 **/
    public static final String START_TIME = "適用日時";
    /** 追加モード **/
    private static final int MODE_ADD = 4;
    /** エラーモード **/
    private static final int MODE_ERROR = 3;
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
    /** 文字列：半角スペース. */
    public static final String REPLACE3 = "$3";
    /** Messageサービス */
    private MessageService messageService;

    /**
     * 【goodsMasterMakerDao】を設定する。
     * 
     * @param goodsMasterMakerDao
     *            【goodsMasterMakerDao】
     */
    @Resource
    public void setGoodsMasterMakerDao(GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao) {
        this.goodsMasterMakerDao = goodsMasterMakerDao;
    }

    /**
     * 結合DAOを設定する.
     * 
     * @param joinMasterCommonDao
     *            結合DAO
     */
    @Resource
    public void setJoinMasterCommonDao(GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao) {
        this.joinMasterCommonDao = joinMasterCommonDao;
    }

    /**
     * BLコードDAOを設定する.
     * 
     * @param blCodeMasterCommonDao
     *            BLコードDAO
     */
    @Resource
    public void setBlCodeMasterCommonDao(GenericDao<BlCodeMasterCommon, BlCodeMasterCommonId> blCodeMasterCommonDao) {
        this.blCodeMasterCommonDao = blCodeMasterCommonDao;
    }

    /**
     * 純正商品マスタDAOを設定する.
     * 
     * @param puregoodsMasterCommonDao
     *            純正商品マスタDAO
     */
    @Resource
    public void setPuregoodsMasterCommonDao(
            GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> puregoodsMasterCommonDao) {
        this.puregoodsMasterCommonDao = puregoodsMasterCommonDao;
    }

    /**
     * Messageサービス.
     * 
     * @param messageService
     *            サービス
     */
    @Resource
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * <pre>
     * 結合マスタ(メーカー)DAOを設定する.
     * </pre>
     * 
     * @param joinMasterMakerDao
     *            セットマスタ(メーカー)DAO
     */
    @Resource
    public void setJoinMasterMakerDao(GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao) {
        this.joinMasterMakerDao = joinMasterMakerDao;
    }

    /**
     * 【selectCodeMasterCommonDao】を設定する。
     * 
     * @param selectCodeMasterCommonDao
     *            【selectCodeMasterCommonDao】
     */
    @Resource
    public void setSelectCodeMasterCommonDao(
            GenericDao<SelectCodeMasterCommon, SelectCodeMasterCommonId> selectCodeMasterCommonDao) {
        this.selectCodeMasterCommonDao = selectCodeMasterCommonDao;
    }

    /**
     * 【goodsRateMasterCommonDao】を設定する。
     * 
     * @param goodsRateMasterCommonDao
     *            【goodsRateMasterCommonDao】
     */
    @Resource
    public void setGoodsRateMasterCommonDao(GenericDao<GoodsRateMasterCommon, Integer> goodsRateMasterCommonDao) {
        this.goodsRateMasterCommonDao = goodsRateMasterCommonDao;
    }

}
