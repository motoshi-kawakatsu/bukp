//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : chenlong
// 作 成 日       2017/02/08     修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.setlist.facade.impl;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMakerId;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.SelectCodeMasterCommonService;
import jp.co.broadleaf.breg.goodsguide.service.GoodsGuideService;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterMakerService;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.selectmaker.service.SelectMakerService;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterId;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerIdList;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterSearchModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterWorkMakerModel;
import jp.co.broadleaf.breg.setlist.facade.SetMasterFacade;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterSearchDto;
import jp.co.broadleaf.breg.setlist.service.SetMasterService;
import jp.co.broadleaf.breg.setlist.service.SetMasterWorkService;
import jp.co.broadleaf.breg.setlist.utils.DataConvert;
import jp.co.broadleaf.breg.setlistcommon.service.SetMasterCommonService;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.framework.security.SecurityContextHolder;

import com.google.api.client.util.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <pre>
 * セットマスタ(メーカー)Facadeクラス.
 * </pre>
 */
public class SetMasterFacadeImpl implements SetMasterFacade {
  
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

  /** セットマスタ(work)サービス */
  private SetMasterWorkService setMasterWorkService;

 
  /**
   * <pre>
   * セットマスタ(work)サービス.
   * </pre>
   * 
   * @param setMasterWorkService セットマスタ(メーカー)サービス
   */
  @Resource
  public void setSetMasterWorkService(SetMasterWorkService setMasterWorkService) {
    this.setMasterWorkService = setMasterWorkService;
  }
  /** セットマスタ(common)サービス */
  private SetMasterCommonService setMasterCommonService;
  
  
  /**
   * <pre>
   * セットマスタ(work)サービス.
   * </pre>
   * 
   * @param setMasterCommonService セットマスタ(メーカー)サービス
   */
  @Resource
  public void setSetMasterCommonService(SetMasterCommonService setMasterCommonService) {
    this.setMasterCommonService = setMasterCommonService;
  }

  /** 商品マスタ(メーカー)サービス */
  private GoodsMasterMakerService goodsMasterService;
  
  /**
   * <pre>
   * 商品マスタ(メーカー)サービス.
   * </pre>
   * 
   * @param goodsMasterService 商品マスタ(メーカー)サービス
   */
  @Resource
  public void setGoodsMasterService(GoodsMasterMakerService goodsMasterService) {
    this.goodsMasterService = goodsMasterService;
  }

  /**
   * <pre>
   * BLコードマスタのService
   * </pre>
   */
  private BlCodeMasterCommonService blCodeMasterCommonService;

  /**
   * <pre>
   * 【BLコードマスタのService】を設定する。
   * </pre>
   *
   * @param blCodeMasterCommonService 【BLコードマスタのService】
   */
  @Resource
  public void setBlCodeMasterCommonService(BlCodeMasterCommonService blCodeMasterCommonService) {
    this.blCodeMasterCommonService = blCodeMasterCommonService;
  }

  /**
   * <pre>
   * セレクトコードマストのService
   * </pre>
   */
  private SelectCodeMasterCommonService selectCodeMasterCommonService;

  /**
   * <pre>
   * 【セレクトコードマストのService】を設定する。
   * </pre>
   *
   * @param selectCodeMasterCommonService 【セレクトコードマストのService】
   */
  @Resource
  public void setSelectCodeMasterCommonService(SelectCodeMasterCommonService selectCodeMasterCommonService) {
    this.selectCodeMasterCommonService = selectCodeMasterCommonService;
  }
  
  /** 優良品番検索サビース **/
  private GoodsGuideService goodsGuideService;

  /**
   * <pre>
   * 優良品番検索サビースを設定する。
   * </pre>
   *
   * @param goodsGuideService 優良品番検索サビース
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
   * <pre>
   * セットマスタ(メーカー/work)の取得.  page true
   * </pre>
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchDto 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param isPage pageMark
   * @param table db検索
   * @return セットマスタDto
   */
  @Override
  public SetMasterInfoDto searchSetMasterInfoDto(SetMasterSearchDto setMasterSearchDto, boolean isPage, String table,int fileSelectMode, int mode) {
    SetMasterInfoDto setMasterInfoDto = new SetMasterInfoDto();
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();
    long searchCounts = 0L;
    if ("maker".equals(table)) {
      // makerdb 検索
      if (isPage) {
        // do page
        SetMasterMakerModel searchSetMasterMakerList = setMasterService.searchSetMasterMakerList(
            convertToSetMasterSearchModel(setMasterSearchDto), setMasterSearchDto.getSkipRows(),
            setMasterSearchDto.getMaxRows(),fileSelectMode, mode);
        searchCounts = searchSetMasterMakerList.getSearchCounts();
        setMasterDtoList = convertToSetMasterDtoList(searchSetMasterMakerList);
      } else {
        // do not page
        SetMasterMakerModel searchSetMasterMakerList = setMasterService
            .searchSetMasterMakerList(convertToSetMasterSearchModel(setMasterSearchDto), fileSelectMode,  mode);
        searchCounts = searchSetMasterMakerList.getSearchCounts();
        setMasterDtoList = convertToSetMasterDtoList(searchSetMasterMakerList);
      }

    } else if ("work".equals(table)) {

      // workmakerdb 検索
      if (isPage) {
        // do page
        SetMasterWorkMakerModel searchSetMasterWorkMakerList = setMasterWorkService.searchSetMasterWorkMakerList(
            convertToSetMasterSearchModel(setMasterSearchDto), setMasterSearchDto.getSkipRows(),
            setMasterSearchDto.getMaxRows(),fileSelectMode, mode);
        searchCounts = searchSetMasterWorkMakerList.getSearchCounts();
        setMasterDtoList = convertToSetMasterDtoList(searchSetMasterWorkMakerList);
      } else {
        // do not page
        SetMasterWorkMakerModel searchSetMasterWorkMakerList = setMasterWorkService
            .searchSetMasterWorkMakerList(convertToSetMasterSearchModel(setMasterSearchDto),fileSelectMode, mode);
        searchCounts = searchSetMasterWorkMakerList.getSearchCounts();
        setMasterDtoList = convertToSetMasterDtoList(searchSetMasterWorkMakerList);
      }
    } else {
      // common db 検索
    }
    setMasterInfoDto.setSearchCounts(searchCounts);
    setMasterInfoDto.setSetMasterDtoList(setMasterDtoList);
    return setMasterInfoDto;
  }

  /**
   * <pre>
   * セットマスタ(メーカー/work)の全データ取得.
   * </pre>
   * @param makerCd makerCd
   * @param fileSelectMode   0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param table db検索
   * @return セットマスタDto
   */
  @Override
  public SetMasterInfoDto searchSetMasterInfoDto(String table,int fileSelectMode,int makerCd,int mode) {
    SetMasterInfoDto setMasterInfoDto = new SetMasterInfoDto();
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();
    if ("maker".equals(table)) {
      // makerdb 検索
      setMasterDtoList = convertToSetMasterDtoList(setMasterService.searchSetMasterMakerList(fileSelectMode,makerCd, mode));
    } else if ("work".equals(table)) {
      // workmakerdb 検索
      setMasterDtoList = convertToSetMasterDtoList(setMasterWorkService.searchSetMasterWorkMakerList(fileSelectMode,makerCd, mode));
    } else {

    }
    setMasterInfoDto.setSetMasterDtoList(setMasterDtoList);
    return setMasterInfoDto;
  }

  /**
   * <pre>
   * セットマスタ(メーカー/work)の登録.
   * </pre>
   * 
   * @param setMasterInfoDto セットマスタDto
   * @param table db検索
   * @return 登録件数
   */
  @Override
  public int insertSetMasterInfoDto(SetMasterInfoDto setMasterInfoDto, String table) {
    int updateNum = 0;
    if ("maker".equals(table)) {
      // insert into makerdb return updateNum
      updateNum = setMasterService
          .insertSetMasterMakerList(convertToSetMasterMakerModel(setMasterInfoDto.getSetMasterDtoList()));
    } else if ("work".equals(table)) {
      // insert into makerworkdb return updateNum
      updateNum = setMasterWorkService
          .insertSetMasterWorkMakerList(convertToSetMasterWorkMakerModel(setMasterInfoDto.getSetMasterDtoList()));
    } else {
      // insert into common db
    }
    return updateNum;
  }

  /**
   * <pre>
   * セットマスタ(メーカー/work)情報更新.
   * </pre>
   * 
   * @param table db検索
   * @param setMasterDto セットマスタDto
   */
  @Override
  public void updateSetMaster(SetMasterDto setMasterDto, String table) {
    List<SetMasterDto> setMasterDtoList = new ArrayList<>();
    setMasterDtoList.add(setMasterDto);
    if ("maker".equals(table)) {
      // update makerdb
      setMasterService
          .updateSetMasterMaker(convertToSetMasterMakerModel(setMasterDtoList).getSetMasterMakerList().get(0));
    } else if ("work".equals(table)) {
      // update workmakerdb
      setMasterWorkService.updateSetMasterWorkMaker(
          convertToSetMasterWorkMakerModel(setMasterDtoList).getSetMasterWorkMakerList().get(0));
    } else {
      // common db
    }
  }

  /**
   * <pre>
   * blCode 検索
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return blCodelist
   */
  @Override
  public List<BlCodeMasterDto> searchBlCode(int makerCd) {
    List<BlCodeMasterDto> arrayList = new ArrayList<BlCodeMasterDto>();
    List<BlCodeMasterCommon> searchBlCode = blCodeMasterCommonService.getBlCodeMasterInfo(makerCd);
    for (BlCodeMasterCommon blCodeMasterCommon : searchBlCode) {
      BlCodeMasterDto dto = new BlCodeMasterDto();
      dto.setBlCode(blCodeMasterCommon.getBlCode());
      dto.setBlHalfName(blCodeMasterCommon.getBlHalfName());
      dto.setBlFullName(blCodeMasterCommon.getBlFullName());
      arrayList.add(dto);
    }
    return arrayList;
  }

  /**
   * <pre>
   * SelectCode 検索
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return selectCodelist
   */
  @Override
  public List<SelectCodeMasterDto> searchSelectCode(int makerCd) {
    ArrayList<SelectCodeMasterDto> arrayList = new ArrayList<SelectCodeMasterDto>();
    List<SelectCodeMasterCommon> searchSelectCode = selectCodeMasterCommonService.getSelectCodeInfo(makerCd);
    for (SelectCodeMasterCommon selectCodeMasterCommon : searchSelectCode) {
      SelectCodeMasterDto selectCodeMasterDto = new SelectCodeMasterDto();
      selectCodeMasterDto.setPrmSetDtlNo1(selectCodeMasterCommon.getPrmSetDtlNo1());
      selectCodeMasterDto.setPrmSetDtlName(selectCodeMasterCommon.getPrmSetDtlName());
      arrayList.add(selectCodeMasterDto);
    }
    return arrayList;
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
   * 優良品番検索結果
   *  
   * @param makerCd メーカーコード
   * 
   * @return 優良品番検索結果
   */
  @Override
  public Map<String, String> getGoodsCodeNameMap(int makerCd) {
    String primeCode="";
    int logical = LogicDeleteDivEnum.Valid.getValue();
    List<GoodsMasterMaker> primeByCode = goodsGuideService.getPrimeByCodeAll(logical, makerCd, primeCode);
    Map<String, String> goodsNameMap = new HashMap<String, String>();
    
    if (primeByCode != null && !primeByCode.isEmpty()) {
      for (GoodsMasterMaker goodsMasterMaker : primeByCode) {
        String primePartsNoWithH = goodsMasterMaker.getPrimePartsNoWithH();
        if (!goodsNameMap.containsKey(primePartsNoWithH)) {
          goodsNameMap.put(primePartsNoWithH, primePartsNoWithH.concat("：").concat(goodsMasterMaker.getPrimePartsName()));
        }
      }
    }
    return goodsNameMap;
    
  }
  

  /**
   * <pre>
   * セットマスタ(メーカー)キーのチェック（登録前チェック）.
   * </pre>
   * 
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return チェック結果
   */
  @Override
  public boolean checkIdBeforeInsert(SetMasterSearchDto setMasterSearchDto) {
    SetMasterMaker setMasterMaker = new SetMasterMaker();
    DataConvert.dtoOrModelconvertToSetMasterMaker(setMasterSearchDto, setMasterMaker);
    if (null != setMasterService.searchBySetMasterId(setMasterMaker)) {
      return false;
    }
    return true;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)のチェックデータ取得.
   * </pre>
   * 
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return 親商品の連係情報
   */
  @Override
  public GoodsMasterMaker searchMainGoodsForCheck(SetMasterSearchDto setMasterSearchDto) {
    GoodsMasterMakerId goodsMasterId = new GoodsMasterMakerId();
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPartsMakerCd(setMasterSearchDto.getPartsMakerCd());
    // セット親品番 = 引数.セット親品番
    goodsMasterId.setPrimePartsNoWithH(setMasterSearchDto.getSetMainPartsNo());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(setMasterSearchDto.getPrmSetDtlNo1());
    return goodsMasterService.searchGoodsById(goodsMasterId);
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)のチェックデータ取得.
   * </pre>
   * 
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return 子商品の連係情報
   */
  @Override
  public GoodsMasterMaker searchSubGoodsForCheck(SetMasterSearchDto setMasterSearchDto) {
    GoodsMasterMakerId goodsMasterId = new GoodsMasterMakerId();
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPartsMakerCd(setMasterSearchDto.getPartsMakerCd());
    // セット子品番 = 引数.セット子品番
    goodsMasterId.setPrimePartsNoWithH(setMasterSearchDto.getSetSubPartsNo());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(setMasterSearchDto.getPrmSetDtlNo1());
    return goodsMasterService.searchGoodsById(goodsMasterId);
  }

  /**
   * <pre>
   * セットマスタ(メーカー/work)delete.
   * </pre>
   * 
   * @param table セットマスタ(メーカー)ID
   * @param setMasterInfoDto セットマスタ(メーカー)ID
   */
  @Override
  public void deleteSetMaster(String table, SetMasterInfoDto setMasterInfoDto) {
    if ("maker".equals(table)) {
      setMasterService.deleteSetMasterMaker(convertToSetMasterMakerModel(setMasterInfoDto.getSetMasterDtoList()));
    } else if ("work".equals(table)) {
      setMasterWorkService
          .deleteSetMasterWorkMaker(convertToSetMasterWorkMakerModel(setMasterInfoDto.getSetMasterDtoList()));
    } else {

    }
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param setMasterMakerModel セットマスタ(メーカー)Model
   * @return セットマスタ(メーカー)Dto
   */
  private List<SetMasterDto> convertToSetMasterDtoList(SetMasterMakerModel setMasterMakerModel) {
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();
    if (null != setMasterMakerModel.getSetMasterMakerList() && setMasterMakerModel.getSetMasterMakerList().size() > 0) {
      for (SetMasterMaker setMasterMaker : setMasterMakerModel.getSetMasterMakerList()) {
        SetMasterDto setMasterDto = new SetMasterDto();
        DataConvert.setMasterMakerconvertToDtoOrModel(setMasterMaker, setMasterDto);
        setMasterDtoList.add(setMasterDto);
      }
    }
    return setMasterDtoList;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param setMasterWorkMakerModel セットマスタ(メーカー)Model
   * @return セットマスタ(メーカー)Dto
   */
  private List<SetMasterDto> convertToSetMasterDtoList(SetMasterWorkMakerModel setMasterWorkMakerModel) {
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();
    if (null != setMasterWorkMakerModel.getSetMasterWorkMakerList()
        && setMasterWorkMakerModel.getSetMasterWorkMakerList().size() > 0) {
      for (SetMasterWorkMaker setMasterWorkMaker : setMasterWorkMakerModel.getSetMasterWorkMakerList()) {
        SetMasterDto setMasterDto = new SetMasterDto();
        DataConvert.setMasterWorkMakerconvertToDtoOrModel(setMasterWorkMaker,setMasterDto);
        setMasterDtoList.add(setMasterDto);
      }
    }
    return setMasterDtoList;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param setMasterDtoList セットマスタ(メーカー)Dto
   * @return セットマスタ(メーカー)Model
   */
  private SetMasterWorkMakerModel convertToSetMasterWorkMakerModel(List<SetMasterDto> setMasterDtoList) {
    SetMasterWorkMakerModel setMasterWorkMakerModel = new SetMasterWorkMakerModel();
    if (null != setMasterDtoList && setMasterDtoList.size() > 0) {
      List<SetMasterWorkMaker> setMasterWorkList = new ArrayList<SetMasterWorkMaker>();
      for (SetMasterDto setMasterDto : setMasterDtoList) {
        SetMasterWorkMaker setMasterWork = new SetMasterWorkMaker();
        DataConvert.dtoOrModelconvertToSetMasterWorkMaker(setMasterDto, setMasterWork);
        setMasterWorkList.add(setMasterWork);
      }
      setMasterWorkMakerModel.setSetMasterWorkMakerList(setMasterWorkList);
    }
    return setMasterWorkMakerModel;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param setMasterDtoList セットマスタ(メーカー)Dto
   * @return セットマスタ(メーカー)Model
   */
  private SetMasterMakerModel convertToSetMasterMakerModel(List<SetMasterDto> setMasterDtoList) {
    SetMasterMakerModel setMasterMakerModel = new SetMasterMakerModel();
    if (null != setMasterDtoList && setMasterDtoList.size() > 0) {
      List<SetMasterMaker> setMasterMakerlist = new ArrayList<SetMasterMaker>();
      for (SetMasterDto setMasterDto : setMasterDtoList) {
        SetMasterMaker setMasterWork = new SetMasterMaker();
        DataConvert.dtoOrModelconvertToSetMasterMaker(setMasterDto, setMasterWork);
        setMasterMakerlist.add(setMasterWork);
      }
      setMasterMakerModel.setSetMasterMakerList(setMasterMakerlist);
    }
    return setMasterMakerModel;
  }

  /**
   * <pre>
   * DtoからModelへの転換.
   * </pre>
   * 
   * @param setMasterSearchDto セットマスタ検索Dto
   * @return セットマスタ検索Model
   */
    SetMasterSearchModel setMasterSearchModel = new SetMasterSearchModel();
    private SetMasterSearchModel convertToSetMasterSearchModel(SetMasterSearchDto setMasterSearchDto) {
    if (null != setMasterSearchDto) {
      DataConvert.searchConvert(setMasterSearchModel, setMasterSearchDto);
    }
    return setMasterSearchModel;
  }
  
  
  

  /**
   * <pre>
   * セット選択モードのセットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @param setMasterInfoDto 未編集の結合マスタリスト
   * @param idList SetMasterMakerIdList
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public SetMasterInfoDto searchSetMasterInfoList(SetMasterInfoDto setMasterInfoDto,SetMasterMakerIdList idList) {
    List<SetMasterDto> setMasterDtoList = new ArrayList<>();
    for (SetMasterDto setMasterDto : setMasterInfoDto.getSetMasterDtoList()) {
      List<SetMasterDto> editList = new ArrayList<>();
      editList.add(setMasterDto);
      SelectMaker selectMaker= selectMakerService.searchSetById(setMasterDto.getPrmSetDtlNo1(), 
          setMasterDto.getPartsMakerCd(), setMasterDto.getSetMainPartsNo(), setMasterDto.getSetDispOrder());
      setMasterDto.setCheck(selectMaker!= null);
      idCompare(setMasterDto, idList);
      List<SetMasterCommon> setMasterCommonList = setMasterCommonService.searchSetMasterCommonList(convertToSetMasterMakerModel(editList).getSetMasterMakerList().get(0));
      if(null!=setMasterCommonList&& !setMasterCommonList.isEmpty()){
        SetMasterCommon setMasterCommon = setMasterCommonList.get(0);
        StringBuffer compareFlag = new StringBuffer();
        compareFlag.append("0");//check 
        compareFlag.append(",0"); //no
        compareFlag.append(",0");//申請状態
        compareFlag.append(",0");//処理区分
        compareFlag.append(",0");//セレクトコード名称
        dataJudge(setMasterDto.getGoodsMGroup(), setMasterCommon.getGoodsMGroup(), compareFlag);//分類コード名称
        dataJudge(setMasterDto.getTbsPartsCode(), setMasterCommon.getTbsPartsCode(), compareFlag);//BLコード名称
        compareFlag.append(",0");//セット親品番
        compareFlag.append(",0");//表示順位
        dataJudge(setMasterDto.getSetSubPartsNo(), setMasterCommon.getSetSubPartsNo(), compareFlag);//セット子品番
        dataJudge(setMasterDto.getSetKanaName(), setMasterCommon.getSetKanaName(), compareFlag);//セット品名（半角）', binding: 'setKanaName', width: 160},
        dataJudge(setMasterDto.getSetName(), setMasterCommon.getSetName(), compareFlag);//セット品名（全角）
        dataJudge(setMasterDto.getSetQty(), setMasterCommon.getSetQty(), compareFlag);//QTY
        dataJudge(setMasterDto.getSetSpecialNote(), setMasterCommon.getSetApecialNote(), compareFlag);//規格/特記
        dataJudge(setMasterDto.getPrimePartsSpecialNoteC(), setMasterCommon.getPrimePartsSpecialNoteC(), compareFlag);//規格/特記(一般)
        dataJudge(setMasterDto.getDeleteFlg(), setMasterCommon.getDeleteFlg(), compareFlag);//削除依頼区分
        dataJudge(setMasterDto.getDeleteReason(), setMasterCommon.getDeleteReason(), compareFlag);//削除理由
        dataJudge(setMasterDto.getInsDtTime(), setMasterCommon.getInsDtTime(), compareFlag);//作成日時
        dataJudge(setMasterDto.getUpdDtTime(), setMasterCommon.getUpdDtTime(), compareFlag); //更新日時
        dataJudge(setMasterDto.getStartTime(), setMasterCommon.getStartTime(), compareFlag);//適用日時
        compareFlag.append(",0");// チェック区分
        dataJudge(setMasterDto.getBlEntryFlg(), setMasterCommon.getBlEntryFlg(), compareFlag);//BL登録区分
        dataJudge(setMasterDto.getErrorFlg(), setMasterCommon.getErrFlg(), compareFlag);//エラー区分
        compareFlag.append(",0");//エラー内容
        setMasterDto.setCompareFlag(compareFlag.toString());
      }
      
      
      setMasterDtoList.add(setMasterDto);
    }
    SetMasterInfoDto setMaster = new SetMasterInfoDto();
    setMaster.setSetMasterDtoList(setMasterDtoList);
    setMaster.setSearchCounts(setMasterInfoDto.getSearchCounts());
    return setMaster;
    
    
  }
  /**
   * <pre>
   * dto&id's property isEquals
   * </pre>
   *
   * @param setMasterDto SetMasterDto
   * @param idList SetMasterMakerIdList
   */
  private void idCompare(SetMasterDto setMasterDto,SetMasterMakerIdList idList){
   List<SetMasterId> setMasterIdList = idList.getIdList();
    for (SetMasterId setMasterId : setMasterIdList) {
      if(setMasterDto.isEqualsId(DataConvert.createSetMasterMakerId(setMasterId))){
        setMasterDto.setCheck(true);
        break;
      }
    }
    return;
  }
  
  /**
   * dataJudge
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
   * メッセージを取得します.
   * 
   * @param messageMap messageMap
   */
  @Override
  public void getMessage(HashMap<String, String> messageMap) {
    messageMap.put(BregMessageCodes.I00001, messageService.messageInfo(BregMessageCodes.I00001) == null ? ""
        : messageService.messageInfo(BregMessageCodes.I00001));
    messageMap.put(BregMessageCodes.Q00001, messageService.messageInfo(BregMessageCodes.Q00001) == null ? ""
        : messageService.messageInfo(BregMessageCodes.Q00001));
    messageMap.put(BregMessageCodes.Q00002, messageService.messageInfo(BregMessageCodes.Q00002) == null ? ""
        : messageService.messageInfo(BregMessageCodes.Q00002));
    messageMap.put(BregMessageCodes.E00004, messageService.getMessage("セット品名(半角)", "", BregMessageCodes.E00004));
    messageMap.put(BregMessageCodes.E00008, messageService.getMessage("セット", "", BregMessageCodes.E00008));
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
    messageMap.put(BregMessageCodes.E00011, messageService.getMessage("申請中", "セット", BregMessageCodes.E00011));
    messageMap.put(BregMessageCodes.E00012.concat("1"),
        messageService.getMessage("申請中", "セット", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.E00012.concat("2"),
        messageService.getMessage("インポート", "セット", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.E00012.concat("3"),
        messageService.getMessage("承認済", "セット", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.E00006.concat("1"),
        messageService.getMessage("適用日", "適用日", BregMessageCodes.E00006));
    messageMap.put(BregMessageCodes.E00006.concat("2"),
        messageService.getMessage("作成日", "作成日", BregMessageCodes.E00006));
    messageMap.put(BregMessageCodes.E00006.concat("3"),
        messageService.getMessage("更新日", "更新日", BregMessageCodes.E00006));
    
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
  public void setJoinMasterService(GoodsMasterMakerService goodsMasterMakerService) {
    this.goodsMasterMakerService = goodsMasterMakerService;
  }
  
  /**
   * 画面初期ロード時、機能制御の判断
   * 
   * @return 「True」：機能制御なし、「False」：機能制御あり
   */
  @Override
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

  /**
   * SelectMakerの処理
   * 
   * @param setMasterDto セットマスタ
   * @param makerCd メーカーコード
   */
  @Override
  public void manegeSelectMaker(SetMasterDto setMasterDto, int makerCd) {
    int sPrmSetDtlNo1 = setMasterDto.getPrmSetDtlNo1();
    if (setMasterDto.getCheck()) {
      SelectMaker selectMaker = convertSelectMaker(setMasterDto, makerCd);
        selectMakerService.insertSetById(selectMaker);
    } else {
      selectMakerService.deleteSetById(sPrmSetDtlNo1, makerCd, setMasterDto.getSetMainPartsNo(),setMasterDto.getSetDispOrder());
    }
  }
  /**
   * SelectMakerのSession処理
   * 
   * @param setMasterMakerIdList セットマスタ
   */
  @Override
  public void manegeSelectMakerInSession(List<SetMasterId> setMasterMakerIdList) {
    for (SetMasterId setMasterId : setMasterMakerIdList) {
       SelectMaker convertSelectMaker = convertSelectMaker(DataConvert.createSetMasterMakerId(setMasterId));
       selectMakerService.insertSetById(convertSelectMaker);
    }
  }
  
 

  /**
   * SelectMakerの取得
   * 
   * @param id SetMasterMakerId
   * @return SelectMaker
   */
  private SelectMaker convertSelectMaker(SetMasterMakerId id) {
    SelectMaker selectMaker = new SelectMaker();
    int initValue = 0;
    String empty = "";
    int sPrmSetDtlNo1 = id.getPrmSetDtlNo1();
    int sPartsMakerCd= id.getPartsMakerCd();
    int sSetDispOrder=id.getSetDispOrder();
    String sSetMainPartsNo=id.getSetMainPartsNo();
    selectMaker.setGPartsMakerCd(initValue);
    selectMaker.setGPrimePartsNoWithH(empty);
    selectMaker.setGPrmSetDtlNo1(initValue);
    selectMaker.setJJoinDispOrder(initValue);
    selectMaker.setJJoinSourceMakerCode(initValue);
    selectMaker.setJJoinSourPartsNoWithH(empty);
    selectMaker.setJPartsMakerCd(initValue);
    selectMaker.setJPrmSetDtlNo1(initValue);
    selectMaker.setJPrmSetDtlNo2(initValue);
    selectMaker.setJTbsPartsCode(initValue);
    selectMaker.setSPartsMakerCd(sPartsMakerCd);
    selectMaker.setSPrmSetDtlNo1(sPrmSetDtlNo1);
    selectMaker.setSSetDispOrder(sSetDispOrder);
    selectMaker.setSSetMainPartsNo(sSetMainPartsNo);
    selectMaker.setSelectKbn(1);
    return selectMaker;
  }
  /**
   * SelectMakerの取得
   * 
   * @param setMasterDto SetMasterDto
   * @param makerCd makerCd
   * @return SelectMaker
   */
  private SelectMaker convertSelectMaker(SetMasterDto setMasterDto, int makerCd) {
    SelectMaker selectMaker = new SelectMaker();
    int initValue = 0;
    String empty = "";
    int sPrmSetDtlNo1 = setMasterDto.getPrmSetDtlNo1()==null?initValue:setMasterDto.getPrmSetDtlNo1();
    int sPartsMakerCd= setMasterDto.getPartsMakerCd()==null?initValue:setMasterDto.getPartsMakerCd();
    int sSetDispOrder=setMasterDto.getSetDispOrder()==null?initValue:setMasterDto.getSetDispOrder();
    String sSetMainPartsNo=setMasterDto.getSetMainPartsNo()==null?empty:setMasterDto.getSetMainPartsNo();
    selectMaker.setGPartsMakerCd(initValue);
    selectMaker.setGPrimePartsNoWithH(empty);
    selectMaker.setGPrmSetDtlNo1(initValue);
    selectMaker.setJJoinDispOrder(initValue);
    selectMaker.setJJoinSourceMakerCode(initValue);
    selectMaker.setJJoinSourPartsNoWithH(empty);
    selectMaker.setJPartsMakerCd(initValue);
    selectMaker.setJPrmSetDtlNo1(initValue);
    selectMaker.setJPrmSetDtlNo2(initValue);
    selectMaker.setJTbsPartsCode(initValue);
    selectMaker.setSPartsMakerCd(sPartsMakerCd);
    selectMaker.setSPrmSetDtlNo1(sPrmSetDtlNo1);
    selectMaker.setSSetDispOrder(sSetDispOrder);
    selectMaker.setSSetMainPartsNo(sSetMainPartsNo);
    selectMaker.setSelectKbn(1);
    return selectMaker;
  }

  /**
   * <pre>
   * チェック選択に情報取得.
   * </pre>
   * 
   * @return チェック選択情報
   */
  @Override
  public int searchSelectBySelectKbn(int partsMakerCd) {
    return selectMakerService.searchBySelectKbn(1, partsMakerCd, 0).size();
  }
  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @return セットマスタ(メーカー)Dto
   */
  @Override
  public SetMasterInfoDto searchSetMasterInfoBySelect(int partsMakerCd) {
    SetMasterInfoDto setMasterInfoDto = new SetMasterInfoDto();
    List<SelectMaker> selectMakerList = selectMakerService.searchBySelectKbn(1, partsMakerCd, 0);
    List<SetMasterDto> setMasterDtoList = new ArrayList<>();
    for (SelectMaker selectMaker : selectMakerList) {
      SetMasterMakerId setMasterId = new SetMasterMakerId();
      setMasterId.setPrmSetDtlNo1(selectMaker.getSPrmSetDtlNo1());
      setMasterId.setPartsMakerCd(selectMaker.getSPartsMakerCd());
      setMasterId.setSetMainPartsNo(selectMaker.getSSetMainPartsNo());
      setMasterId.setSetDispOrder(selectMaker.getSSetDispOrder());
      SetMasterMaker setMasterMaker = setMasterService.searchSetById(setMasterId);
      if (setMasterMaker != null) {
        SetMasterDto dto=new SetMasterDto();
        DataConvert.setMasterMakerconvertToDtoOrModel(setMasterMaker, dto);
        setMasterDtoList.add(dto);
      }
    }
    setMasterInfoDto.setSetMasterDtoList(setMasterDtoList);
    return setMasterInfoDto;
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public SetMasterMaker searchById(SetMasterMakerId setMasterId) {
    return setMasterService.searchById(setMasterId);
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public List<SetMasterDto> searchSetMasterById(SetMasterMakerId setMasterId) {
    SetMasterMaker setMasterMaker = setMasterService.searchById(setMasterId);
    List<SetMasterDto> setMasterDtoList = new ArrayList<>();
    if(setMasterMaker!=null){
      SetMasterDto dto=new SetMasterDto();
      DataConvert.setMasterMakerconvertToDtoOrModel(setMasterMaker, dto);
      setMasterDtoList.add(dto);
    }
    return setMasterDtoList;
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public List<SetMasterDto> searchSetMasterWorkById(SetMasterWorkMakerId setMasterId) {
    SetMasterWorkMaker setMasterMaker = setMasterWorkService.searchById(setMasterId);
    List<SetMasterDto> setMasterDtoList = new ArrayList<>();
    if(setMasterMaker!=null){
      SetMasterDto dto=new SetMasterDto();
      DataConvert.setMasterWorkMakerconvertToDtoOrModel(setMasterMaker, dto);
      setMasterDtoList.add(dto);
    }
    return setMasterDtoList;
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * @param partsMakerCd int
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public List<SelectMaker>  findAll(int partsMakerCd){
    return selectMakerService.searchBySelectKbn(1, partsMakerCd, LogicDeleteDivEnum.Valid.getValue());
   }
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * @param setMasterMaker setMasterMaker
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public SelectMaker searchSelectMaker(SetMasterMaker setMasterMaker){
    SelectMaker selectMaker = selectMakerService.searchSetById(setMasterMaker.getPrmSetDtlNo1(), setMasterMaker.getPartsMakerCd(),
        setMasterMaker.getSetMainPartsNo(), setMasterMaker.getSetDispOrder());
    return selectMaker;
  }


}
