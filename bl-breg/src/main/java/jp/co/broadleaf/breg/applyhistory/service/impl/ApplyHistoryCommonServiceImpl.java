//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : tangyp
// 作 成 日       2017/02/14   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyhistory.service.impl;

import jp.co.broadleaf.breg.applyhistory.model.ApplyHistoryCommonModel;
import jp.co.broadleaf.breg.applyhistory.model.ApplyHistorySearchModel;
import jp.co.broadleaf.breg.applyhistory.service.ApplyHistoryCommonService;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommonId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.ApplyTypeEnum;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.persistence.jdbc.SqlCommand;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 申請履歴一覧サービスクラス.
 * </pre>
 */
public class ApplyHistoryCommonServiceImpl implements ApplyHistoryCommonService {
  
  /** Jdbcテンプレート */ 
  private JdbcTemplate jdbcTemplateCommon; 
  
  /** 申請ヘッダマスタDAO */
  private GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao;

  /**
   * JdbcCommonテンプレートを設定する
   * 
   * @param jdbcTemplateCommon
   *            JdbcCommonテンプレート
   */    
  @Resource
  public void setJdbcTemplateCommon(JdbcTemplate jdbcTemplateCommon) {
    this.jdbcTemplateCommon = jdbcTemplateCommon;
} 
  
  /**
   * <pre>
   * 申請ヘッダマスタDAOを設定する.
   * </pre>
   * 
   * @param applyHeadMasterCommonDao
   *            申請ヘッダマスタDAO
   */
  @Resource
  public void setApplyHeadMasterCommonDao(GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao) {
      this.applyHeadMasterCommonDao = applyHeadMasterCommonDao;
  }

  /**
   * <pre>
   * 申請履歴情報取得.
   * </pre>
   * 
   * @param applyHistorySearchModel 申請履歴検索Model
   * 
   * @return 申請履歴情報
   */
  @Override
  public List<ApplyHistoryCommonModel> searchApplyHistoryCommonInfoList(ApplyHistorySearchModel applyHistorySearchModel) {
    
    List<ApplyHistoryCommonModel> resultList = new ArrayList<ApplyHistoryCommonModel>();
    SqlCommand command = new SqlCommand();
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    
    SqlCommand commandTemp = prepareSql(applyHistorySearchModel);
    if (null == commandTemp.getText() || commandTemp.getText().isEmpty()) {
      return resultList;
    } else {
      sql.append(commandTemp.getText());
      for (Object param : commandTemp.getParameters()) {
        params.add(param);
      }
    }
      //表示順
      if(String.valueOf(1).equals(applyHistorySearchModel.getApplySort())) {
        sql.append(" order by apply_date_time asc ");
      } else {
        sql.append(" order by apply_date_time desc ");
      }
      sql.append("limit " + applyHistorySearchModel.getRows() * (applyHistorySearchModel.getPage()-1) + "," + applyHistorySearchModel.getRows());
      command.setText(sql.toString());
      command.setParameters(params.toArray(new Object[0]));
      
      // 検索する
      resultList = jdbcTemplateCommon.query(command.getText(), command.getParameters(),
          new BeanPropertyRowMapper<ApplyHistoryCommonModel>(ApplyHistoryCommonModel.class));

    return resultList;
  }
  
  
  /**
   * 全件数を取得します。
   * 
   * @param applyHistorySearchModel 申請履歴検索Model
   * 
   * @return 全件数
   */
  @Override
  public long getTotalCount(ApplyHistorySearchModel applyHistorySearchModel) {
    NumberFilter makerCd = NumberFilterBuilder.equalsIfNotNull(ApplyHeadMasterCommon.PARTS_MAKER_CD,
        applyHistorySearchModel.getMakerCd());
    NumberFilter logicalDeDiv = NumberFilterBuilder.equalsIfNotNull(ApplyHeadMasterCommon.LOGICAL_DEL_DIV,0);
    Filter filter = new AndFilter(makerCd, logicalDeDiv);
      return applyHeadMasterCommonDao.countByFilter(filter);
  }


  /**
   * <pre>
   * 申請中件数取得.
   * </pre>
   * 
   * @param applyHistorySearchModel 申請履歴検索Model
   * @return 申請中件数
   */
    @Override
    public int searchApplyCount(ApplyHistorySearchModel applyHistorySearchModel) {

      SqlCommand command = new SqlCommand();
      StringBuilder sql = new StringBuilder();
      List<Object> params = new ArrayList<Object>();
      sql.append("select count(1) from (");
      
      SqlCommand commandTemp = prepareSql(applyHistorySearchModel);
      if (null == commandTemp.getText() || commandTemp.getText().isEmpty()) {
        return 0;
      } else {
        sql.append(commandTemp.getText());
        for (Object param : commandTemp.getParameters()) {
          params.add(param);
        }
      }
      
      sql.append(") count_for_apply where count_for_apply.apply_status= ");
      sql.append(BlApplyResultsFlgEnum.Apply.getValue());
      
      command.setText(sql.toString());
      command.setParameters(params.toArray(new Object[0]));   

      // 検索する
      return jdbcTemplateCommon.queryForObject(command.getText(), command.getParameters(),Integer.class);
    }
    
    /**
     * <pre>
     * 承認済件数取得.
     * </pre>
     * 
     * @param applyHistorySearchModel 申請履歴検索Model
     * @return 承認済件数
     */
      @Override
      public int searchApprovalCount(ApplyHistorySearchModel applyHistorySearchModel) {

        SqlCommand command = new SqlCommand();
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        sql.append("select count(1) from (");
        SqlCommand commandTemp = prepareSql(applyHistorySearchModel);
        if (null == commandTemp.getText() || commandTemp.getText().isEmpty()) {
          return 0;
        } else {
          sql.append(commandTemp.getText());
          for (Object param : commandTemp.getParameters()) {
            params.add(param);
          }
        }
        
        sql.append(" ) count_for_approval where (count_for_approval.apply_status = ");
        sql.append(BlApplyResultsFlgEnum.Approval.getValue());
        sql.append(" or count_for_approval.apply_status = ");
        sql.append(BlApplyResultsFlgEnum.ApplyAgain.getValue());
        sql.append(" or count_for_approval.apply_status = ");
        sql.append(BlApplyResultsFlgEnum.Reject.getValue());
        sql.append(") ");
        
        command.setText(sql.toString());
        command.setParameters(params.toArray(new Object[0]));   

        // 検索する
        int count = jdbcTemplateCommon.queryForObject(command.getText(), command.getParameters(),Integer.class);

        return count;
      }
  
 
      /**
       * <pre>
       * sql準備
       * </pre>
       * 
       * @param applyHistorySearchModel 申請履歴検索Model
       * @return sql準備
       */
        private SqlCommand prepareSql(ApplyHistorySearchModel applyHistorySearchModel) {
          SqlCommand command = new SqlCommand();
          StringBuilder sql = new StringBuilder();
          List<Object> params = new ArrayList<Object>();
                
          if (String.valueOf(ApplyTypeEnum.GenerApply.getValue()).equals(applyHistorySearchModel.getApplyType())) {
            //申請一般
              SqlCommand commandGener = sqlForGener(applyHistorySearchModel);
              sql.append(commandGener.getText());
              for (Object param : commandGener.getParameters()) {
                params.add(param);
              }
             
            } else if (String.valueOf(2).equals(applyHistorySearchModel.getApplyType())) {
              if ((null != applyHistorySearchModel.getTbsPartsCode()  && !applyHistorySearchModel.getTbsPartsCode().isEmpty())
                || (null != applyHistorySearchModel.getPrimePartsNo()  && !applyHistorySearchModel.getPrimePartsNo().isEmpty())
                || (null != applyHistorySearchModel.getPartsMakerCd()  && !applyHistorySearchModel.getPartsMakerCd().isEmpty())
                || (null != applyHistorySearchModel.getJoinSourPartsNoWithH()  && !applyHistorySearchModel.getJoinSourPartsNoWithH().isEmpty())) {
              //申請一般
                SqlCommand commandGener = sqlForGener(applyHistorySearchModel);
                sql.append(commandGener.getText());
                for (Object param : commandGener.getParameters()) {
                  params.add(param);
                } 
              }else {
                sql.append("select ");
                sql.append("m_apply_head_common_for_limit.apply_no as apply_no, ");
                sql.append("m_apply_head_common_for_limit.apply_date_time as apply_date_time, ");
                sql.append("m_apply_head_common_for_limit.goods_num as goods_num, ");
                sql.append("m_apply_head_common_for_limit.set_num as set_num, ");
                sql.append("m_apply_head_common_for_limit.join_num as join_num, ");
                sql.append("m_apply_head_common_for_limit.new_item_num as new_item_num, ");
                sql.append("m_apply_head_common_for_limit.apply_status as apply_status, ");
                sql.append("m_apply_head_common_for_limit.apply_comments as apply_comments, ");
                sql.append("m_apply_head_common_for_limit.bl_apply_judgment_date as bl_apply_judgment_date, ");
                sql.append("m_apply_head_common_for_limit.bl_comments as bl_comments from (");
                //新規品目
                SqlCommand commandNewItem = sqlForNewItem(applyHistorySearchModel);
                sql.append(commandNewItem.getText());
                for (Object param : commandNewItem.getParameters()) {
                  params.add(param);
                }
                sql.append(" union all ");
                //申請一般
                SqlCommand commandGener = sqlForGener(applyHistorySearchModel);
                sql.append(commandGener.getText());
                for (Object param : commandGener.getParameters()) {
                  params.add(param);
                }
                sql.append(" ) as m_apply_head_common_for_limit");
                }
              
            } else {
              if (String.valueOf(ApplyTypeEnum.NewItem.getValue()).equals(applyHistorySearchModel.getApplyType()) 
                && ((null != applyHistorySearchModel.getTbsPartsCode()  && !applyHistorySearchModel.getTbsPartsCode().isEmpty())
                    || (null != applyHistorySearchModel.getPrimePartsNo()  && !applyHistorySearchModel.getPrimePartsNo().isEmpty())
                    || (null != applyHistorySearchModel.getPartsMakerCd()  && !applyHistorySearchModel.getPartsMakerCd().isEmpty())
                    || (null != applyHistorySearchModel.getJoinSourPartsNoWithH()  && !applyHistorySearchModel.getJoinSourPartsNoWithH().isEmpty()))) {
              return command;
            } else {
            //新規品目
              SqlCommand commandNewItem = sqlForNewItem(applyHistorySearchModel);
              sql.append(commandNewItem.getText());
              for (Object param : commandNewItem.getParameters()) {
                params.add(param);
              }
            }
            }          
          // SQL文を作成する 
          command.setText(sql.toString());
          command.setParameters(params.toArray(new Object[0]));

          return command;
        }
      
 
    /**
     * <pre>
     * 申請履歴情報取得sql準備(新規品目)
     * </pre>
     * 
     * @param applyHistorySearchModel 申請履歴検索Model
     * 
     * @return 申請履歴情報
     */
      private SqlCommand sqlForNewItem(ApplyHistorySearchModel applyHistorySearchModel) {

        // SQL文を作成する
        SqlCommand command = new SqlCommand();
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
          
        sql.append("select ");
        sql.append("m_apply_head_common.apply_no as apply_no, ");
        sql.append("m_apply_head_common.apply_dt_time as apply_date_time, ");
        sql.append("0 as goods_num, ");
        sql.append("0 as set_num, ");
        sql.append("0 as join_num, ");
        sql.append("1 as new_item_num, ");
        sql.append("m_apply_head_common.bl_apply_results_flg as apply_status, ");
        sql.append("m_apply_head_common.apply_comments as apply_comments, ");
        sql.append("m_apply_head_common.bl_apply_judgment_date as bl_apply_judgment_date, ");
        sql.append("m_apply_head_common.bl_apply_results_comments as bl_comments ");
        sql.append("from m_apply_head_common "); 
        sql.append(" where m_apply_head_common.apply_type = 1 ");
          
        SqlCommand commandTemp = prepareWhere(applyHistorySearchModel);
        sql.append(commandTemp.getText());
        for (Object param : commandTemp.getParameters()) {
          params.add(param);
        }
          
        command.setText(sql.toString());
        command.setParameters(params.toArray(new Object[0]));
          
        return command;
      }
        
    /**
     * <pre>
     * 申請履歴情報取得sql準備(申請一般)
     * </pre>
     * 
     * @param applyHistorySearchModel 申請履歴検索Model
     * 
     * @return 申請履歴情報
     */
      private SqlCommand sqlForGener(ApplyHistorySearchModel applyHistorySearchModel) {

        // SQL文を作成する
        SqlCommand command = new SqlCommand();
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        
        sql.append("select ");
        sql.append("m_apply_head_common.apply_no as apply_no, ");
        sql.append("m_apply_head_common.apply_dt_time as apply_date_time, ");
        sql.append("case when m_apply_head_common_union_for_type.goods_num is null then 0 else m_apply_head_common_union_for_type.goods_num end  as goods_num, ");
        sql.append("case when m_apply_head_common_union_for_type.set_num is null then 0 else m_apply_head_common_union_for_type.set_num end as set_num, ");
        sql.append("case when m_apply_head_common_union_for_type.join_num is null then 0 else m_apply_head_common_union_for_type.join_num end as join_num, ");
        sql.append("0 as new_item_num, ");
        sql.append("m_apply_head_common.bl_apply_results_flg as apply_status, ");
        sql.append("m_apply_head_common.apply_comments as apply_comments, ");
        sql.append("m_apply_head_common.bl_apply_judgment_date as bl_apply_judgment_date, ");
        sql.append("m_apply_head_common.bl_apply_results_comments as bl_comments ");
        sql.append("from m_apply_head_common "); 
        sql.append("inner join (select m_apply_head_common_union_for_num.apply_no, sum(goods_num) as goods_num,sum(set_num) as set_num,sum(join_num) as join_num ");
        sql.append("from ( ");    
        if (null != applyHistorySearchModel && (null != applyHistorySearchModel.getPartsMakerCd() && !applyHistorySearchModel.getPartsMakerCd().isEmpty()
            || (null != applyHistorySearchModel.getJoinSourPartsNoWithH() && !applyHistorySearchModel.getJoinSourPartsNoWithH().isEmpty()))){
          //結合マスタ(共有)
          SqlCommand commandJoin = searchJoinSql(applyHistorySearchModel);
          sql.append(commandJoin.getText());
          for (Object param : commandJoin.getParameters()) {
            params.add(param);
          }
        } else {
          //商品マスタ(共有)
          SqlCommand commandGoods = searchGoodsSql(applyHistorySearchModel);
          sql.append(commandGoods.getText());
          for (Object param : commandGoods.getParameters()) {
            params.add(param);
          }
          //セットマスタ(共有)
          SqlCommand commandSet = searchSetSql(applyHistorySearchModel);
          sql.append(commandSet.getText());
          for (Object param : commandSet.getParameters()) {
            params.add(param);
          }
          //結合マスタ(共有)
          SqlCommand commandJoin = searchJoinSql(applyHistorySearchModel);
          sql.append(commandJoin.getText());
          for (Object param : commandJoin.getParameters()) {
            params.add(param);
          }
        }       
        sql.append(") as m_apply_head_common_union_for_num group by m_apply_head_common_union_for_num.apply_no) m_apply_head_common_union_for_type ");
        sql.append(" on ");
        sql.append(" m_apply_head_common.apply_no = m_apply_head_common_union_for_type.apply_no ");
        SqlCommand commandTemp = prepareWhere(applyHistorySearchModel);
        sql.append(commandTemp.getText());
        for (Object param : commandTemp.getParameters()) {
          params.add(param);
        }
        
        command.setText(sql.toString());
        command.setParameters(params.toArray(new Object[0]));

        return command;
      }
      
   /**
   * <pre>
   * sql条件準備
   * </pre>
   * 
   * @param applyHistorySearchModel 申請履歴検索Model
   * @return sql準備
   */
    private SqlCommand prepareWhere(ApplyHistorySearchModel applyHistorySearchModel) {
      SqlCommand command = new SqlCommand();
      StringBuilder sql = new StringBuilder();
      List<Object> params = new ArrayList<Object>();
            
      sql.append(" and m_apply_head_common.logical_del_div = 0 ");
      sql.append(" and m_apply_head_common.parts_maker_cd  = ? ");
      if (null != applyHistorySearchModel) {
        params.add(applyHistorySearchModel.getMakerCd());
      }
      //申請ID
      if (null != applyHistorySearchModel && null != applyHistorySearchModel.getApplyNo() && !applyHistorySearchModel.getApplyNo().isEmpty()) {
        sql.append(" and m_apply_head_common.apply_no = ? ");
        params.add(applyHistorySearchModel.getApplyNo());
      }//ステータス
      if (null != applyHistorySearchModel && null != applyHistorySearchModel.getBlApplyResultsFlg() && !applyHistorySearchModel.getBlApplyResultsFlg().isEmpty()) {
        if (String.valueOf(ApplyConditionEnum.Apply.getValue()).equals(applyHistorySearchModel.getBlApplyResultsFlg())) {
          sql.append(" and m_apply_head_common.bl_apply_results_flg = ? ");
          params.add(BlApplyResultsFlgEnum.Apply.getValue());
        } else {
          sql.append(" and ( m_apply_head_common.bl_apply_results_flg = ? ");
          params.add(BlApplyResultsFlgEnum.Approval.getValue());
          sql.append(" or m_apply_head_common.bl_apply_results_flg = ? ");
          params.add(BlApplyResultsFlgEnum.ApplyAgain.getValue());
          sql.append(" or m_apply_head_common.bl_apply_results_flg = ? )");
          params.add(BlApplyResultsFlgEnum.Reject.getValue());
        }
      }//申請期間（FROM）
      if(null != applyHistorySearchModel && null != applyHistorySearchModel.getApplyDateTimeFrom() && !applyHistorySearchModel.getApplyDateTimeFrom().isEmpty()) {
        sql.append(" and m_apply_head_common.apply_dt_time >= ? ");
        params.add(applyHistorySearchModel.getApplyDateTimeFrom());
      }//申請期間（TO）
      if(null != applyHistorySearchModel && null != applyHistorySearchModel.getApplyDateTimeTo() && !applyHistorySearchModel.getApplyDateTimeTo().isEmpty()) {
        sql.append(" and date_add(m_apply_head_common.apply_dt_time, interval -1 day) <= ? ");
        params.add(applyHistorySearchModel.getApplyDateTimeTo());
      }
      
      // SQL文を作成する 
      command.setText(sql.toString());
      command.setParameters(params.toArray(new Object[0]));

      return command;
    }
    
    /**
     * <pre>
     * sql準備(商品マスタ(共有)関連)
     * </pre>
     * 
     * @param applyHistorySearchModel 申請履歴検索Model
     * @return sql準備
     */
      private SqlCommand searchGoodsSql(ApplyHistorySearchModel applyHistorySearchModel) {
        SqlCommand command = new SqlCommand();
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        
        //商品マスタ(共有)
        sql.append("select m_apply_head_common.apply_no, count(1) as goods_num, 0 as set_num, 0 as join_num ");
        sql.append("from m_apply_head_common, m_goods_common  ");
        sql.append("where m_apply_head_common.apply_no = m_goods_common.apply_no ");
        sql.append("and m_apply_head_common.parts_maker_cd = m_goods_common.parts_maker_cd ");
        sql.append("and m_apply_head_common.logical_del_div = 0 ");
        sql.append("and m_goods_common.logical_del_div = 0 ");
        //BLコード
        if (null != applyHistorySearchModel && null != applyHistorySearchModel.getTbsPartsCode() && !applyHistorySearchModel.getTbsPartsCode().isEmpty()) {
          sql.append(" and m_goods_common.tbs_parts_code = ? ");
          params.add(applyHistorySearchModel.getTbsPartsCode());
        }//優良品番
        if (null != applyHistorySearchModel && null != applyHistorySearchModel.getPrimePartsNo() && !applyHistorySearchModel.getPrimePartsNo().isEmpty()) {
          sql.append(" and m_goods_common.prime_parts_no_with_h = ? ");
          params.add(applyHistorySearchModel.getPrimePartsNo());
        }
        sql.append("group by m_apply_head_common.apply_no ");    
        sql.append("union all ");
        command.setText(sql.toString());
        command.setParameters(params.toArray(new Object[0]));
        return command;
      }
      
      /**
       * <pre>
       * sql準備(セットマスタ(共有)関連)
       * </pre>
       * 
       * @param applyHistorySearchModel 申請履歴検索Model
       * @return sql準備
       */
        private SqlCommand searchSetSql(ApplyHistorySearchModel applyHistorySearchModel) {
          SqlCommand command = new SqlCommand();
          StringBuilder sql = new StringBuilder();
          List<Object> params = new ArrayList<Object>();
          //セットマスタ(共有)
          sql.append("select m_apply_head_common.apply_no, 0 as goods_num, count(1) as set_num ,0 as join_num ");
          sql.append("from m_apply_head_common, m_set_common ");
          sql.append("where m_apply_head_common.apply_no = m_set_common.apply_no ");
          sql.append("and m_apply_head_common.parts_maker_cd = m_set_common.parts_maker_cd ");
          sql.append("and m_apply_head_common.logical_del_div = 0 ");
          sql.append("and m_set_common.logical_del_div = 0 ");
          //BLコード
          if (null != applyHistorySearchModel && null != applyHistorySearchModel.getTbsPartsCode() && !applyHistorySearchModel.getTbsPartsCode().isEmpty()) {
            sql.append(" and m_set_common.tbs_parts_code = ? ");
            params.add(applyHistorySearchModel.getTbsPartsCode());
          }//優良品番
          if (null != applyHistorySearchModel && null != applyHistorySearchModel.getPrimePartsNo() && !applyHistorySearchModel.getPrimePartsNo().isEmpty()) {
            sql.append(" and m_set_common.set_main_parts_no = ? ");
            params.add(applyHistorySearchModel.getPrimePartsNo());
          }
          sql.append("group by m_apply_head_common.apply_no ");    
          sql.append("union all "); 
          command.setText(sql.toString());
          command.setParameters(params.toArray(new Object[0]));
          return command;
        }
        
        /**
         * <pre>
         * sql準備(結合マスタ(共有)関連)
         * </pre>
         * 
         * @param applyHistorySearchModel 申請履歴検索Model
         * @return sql準備
         */
          private SqlCommand searchJoinSql(ApplyHistorySearchModel applyHistorySearchModel) {
            SqlCommand command = new SqlCommand();
            StringBuilder sql = new StringBuilder();
            List<Object> params = new ArrayList<Object>();
          //結合マスタ(共有)
            sql.append("select m_apply_head_common.apply_no, 0 as goods_num,  0 as set_num, count(1) as join_num ");
            sql.append("from m_apply_head_common, m_join_common ");
            sql.append("where m_apply_head_common.apply_no = m_join_common.apply_no ");
            sql.append("and m_apply_head_common.parts_maker_cd = m_join_common.parts_maker_cd ");
            sql.append("and m_apply_head_common.logical_del_div = 0 ");
            sql.append("and m_join_common.logical_del_div = 0 ");
            //BLコード
            if (null != applyHistorySearchModel && null != applyHistorySearchModel.getTbsPartsCode() && !applyHistorySearchModel.getTbsPartsCode().isEmpty()) {
              sql.append(" and m_join_common.tbs_parts_code = ? ");
              params.add(applyHistorySearchModel.getTbsPartsCode());
            }//優良品番
            if (null != applyHistorySearchModel && null != applyHistorySearchModel.getPrimePartsNo() && !applyHistorySearchModel.getPrimePartsNo().isEmpty()) {
              sql.append(" and m_join_common.join_dest_parts_no = ? ");
              params.add(applyHistorySearchModel.getPrimePartsNo());
            }//カーコード
            if (null != applyHistorySearchModel && null != applyHistorySearchModel.getPartsMakerCd() && !applyHistorySearchModel.getPartsMakerCd().isEmpty()) {
              sql.append(" and m_join_common.join_source_maker_code = ? ");
              params.add(applyHistorySearchModel.getPartsMakerCd());
            }
            //純正品番
            if (null != applyHistorySearchModel && null != applyHistorySearchModel.getJoinSourPartsNoWithH() && !applyHistorySearchModel.getJoinSourPartsNoWithH().isEmpty()) {
              sql.append(" and m_join_common.join_sour_parts_no_with_h = ? ");
              params.add(applyHistorySearchModel.getJoinSourPartsNoWithH());
            }
            sql.append("group by m_apply_head_common.apply_no "); 
            command.setText(sql.toString());
            command.setParameters(params.toArray(new Object[0]));
            return command;
          }
}
