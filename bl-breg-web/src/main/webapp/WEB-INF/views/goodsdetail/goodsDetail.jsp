<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <!--lib css-->
  <link rel="stylesheet" href="${baseurl}/lib/reset/reset.css"/>
  <link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css"/>
  <!--common css-->
  <link rel="stylesheet" href="${baseurl}/css/common/common/common.css"/>
  <!-- 個別css -->
  <link rel="stylesheet" href="${baseurl}/css/goodsdetail/item.css"/>
  <title>商品詳細</title>
</head>
<body class="overflow">
<div class="page-header">
  <h2>商品詳細</h2>
</div>
<div class="container all">
  <div class="panel panel-primary slide-panel search-scope item-info flow-x">
    <div class="panel-heading head bl-flex-row">
      <h3 class="panel-title">基本情報<span class="required-title">*は必須入力項目です</span></h3>
    </div>
    <div class="panel-body">
      
      <div class="bl-flex-column first-column">
        <br />  
        <!--<div class="input-group maker-cd">-->
        <!--<span class="input-group-addon">部品メーカーコード</span>-->
        <!--<input name="makerCd" type="text" class="form-control" maxlength="24" />-->
        <!--</div>-->
        <div class="input-group select-cd">
          <span class="input-group-addon">セレクトコード</span>
          <select name="selectCd" class="form-control">
            <option value="9999">9999：指定無し</option>
            <c:forEach items="${selectCodeNameMap}" var="item">
              <option value="${item.key}">${item.value}</option>
            </c:forEach>  
          </select>
        </div>

        <div class="input-group classify-cd">
          <span class="input-group-addon">分類コード</span>
          <input name="classifyCd" type="text" class="form-control" maxlength="4"
          id="classifyCd" oninput="numInput(this.id)"/>
          <div class="code-guide">
          <button type="button" class="glyphicon glyphicon-star-empty btn btn-default classifyCdGuide" ></button>
          </div>
        </div>

        <div class="input-group bl-cd init-hide">
          <span class="input-group-addon">BLコード</span>
          <select name="blCd" class="form-control">
            <option value=""></option>
            <c:forEach items="${blCodeNameMap}" var="item">
              <option value="${item.key}">${item.value}</option>
            </c:forEach>
          </select>
        </div>

        <div class="input-group exce-pro-no init-hide">
          <span class="input-group-addon">優良品番</span>
          <input name="exceProNo" type="text" class="form-control" maxlength="24"
          id="goodsCd" oninput="charAndBarInput(this.id)"/>
        </div>

        <div class="input-group pro-name-half init-hide">
          <span class="input-group-addon">品名（半角）</span>
          <input name="proNameHalf" type="text" class="form-control" maxlength="60"
          id="proNameHalf"/>
        </div>
        <div class="input-group pro-name-full init-hide">
          <span class="input-group-addon">品名（全角）</span>
          <input name="proNameFull" type="text" class="form-control" maxlength="60"/>
        </div>

        <div class="input-group price init-hide">
          <span class="input-group-addon">価格（税抜）</span>
          <input name="price" type="text" class="form-control text-right" maxlength="12"
          id="price" oninput="numInput(this.id)"/>
        </div>

        <!---->
        <div class="input-group open-price init-hide">
          <span class="input-group-addon">OPENプライス</span>
          <select name="openPrice" class="form-control">
            <option value=""></option>
            <option value="0">通常</option>
            <option value="1">オープン価格</option>
          </select>
        </div>
        <div class="input-group JAN init-hide">
          <span class="input-group-addon">JAN</span>
          <input name="jan" type="text" class="form-control" maxlength="13"
          id="jan" oninput="numInput(this.id)"/>
        </div>
        <div class="input-group classify init-hide">
          <span class="input-group-addon">層別</span>
          <select name="classify" class="form-control">
            <option value=""></option>
            <c:forEach items="${partsNameMap}" var="item">
              <option value="${item.key}">${item.value}</option>
            </c:forEach>
          </select>
        </div>
        <div class="input-group equipment init-hide">
          <span class="input-group-addon">装備</span>
          <input name="equipment" type="text" class="form-control" maxlength="60"/>
        </div>


      </div>
      <div class="bl-flex-column second-column">
        <br />

        <div class="input-group special-matter">
          <span class="input-group-addon">規格/特記</span>
          <input type="text" class="form-control" name="specialMatter" maxlength="80"/>
        </div>
        <div class="input-group special-matter-common">
          <span class="input-group-addon">規格/特記（一般）</span>
          <input type="text" class="form-control" name="specialMatterCommon" maxlength="80"/>
        </div>
        <div class="input-group product-detail init-hide">
          <span class="input-group-addon ">商品詳細</span>
          <textarea name="productDetail" class="form-control" maxlength="512"></textarea>
        </div>
        <div class="input-group product-detail-common init-hide">
          <span class="input-group-addon ">商品詳細（一般）</span>
          <textarea name="productDetailCommon" class="form-control" maxlength="512"></textarea>
        </div>
        <div class="input-group url1 init-hide">
          <span class="input-group-addon">URL1</span>
          <input name="url1" type="text" class="form-control" maxlength="512"/>
        </div>
        <div class="input-group url2 init-hide">
          <span class="input-group-addon">URL2</span>
          <input name="url2" type="text" class="form-control" maxlength="512"/>
        </div>
        <div class="input-group url3 init-hide">
          <span class="input-group-addon">URL3</span>
          <input name="url3" type="text" class="form-control" maxlength="512"/>
        </div>
      </div>
      <div class="bl-flex-column third-column">
        <div class="input-group deal-distinction">
          <span class="input-group-addon">処理区分</span>
          <input type="text" class="form-control" name="dealDistinction" maxlength="2"/>
        </div>
        <div class="input-group size-unit">  
          <span class="input-group-addon form-unit">長さ</span>
          <span class="input-group-addon form-unit">幅</span>
          <span class="input-group-addon form-unit">高さ</span>
        </div>    
        <div class="input-group pro-length">
          <span class="input-group-addon">商品サイズ</span>
          <input name="proLenA" type="text" class="form-control text-right" maxlength="6"
          id="proLenA" oninput="numInput(this.id)"/>
          <input name="proLenB" type="text" class="form-control text-right" maxlength="6"
          id="proLenB" oninput="numInput(this.id)"/>
          <input name="proLenC" type="text" class="form-control text-right" maxlength="6"
          id="proLenC" oninput="numInput(this.id)"/>
        </div>
        <div class="input-group pack-length">
          <span class="input-group-addon">梱包サイズ</span>
          <input name="packLenA" type="text" class="form-control text-right" maxlength="6"
          id="packLenA" oninput="numInput(this.id)"/>
          <input name="packLenB" type="text" class="form-control text-right" maxlength="6"
          id="packLenB" oninput="numInput(this.id)"/>
          <input name="packLenC" type="text" class="form-control text-right" maxlength="6"
          id="packLenC" oninput="numInput(this.id)"/>
        </div>

        <div class="input-group length-unit init-hide">
          <span class="input-group-addon">梱包単位</span>
          <select name="lengthUnit" class="form-control">
            <option value="00"></option>
            <option value="01">mm</option>
            <option value="02">cm</option>
            <option value="03">m</option>
          </select>    
        </div>

        <div class="input-group weight init-hide">
          <span class="input-group-addon">重量</span>
          <input type="text" class="form-control text-right" name="weight" maxlength="6"
          id="weight" oninput="numInput(this.id)"/>
        </div>
        <div class="input-group weight-unit init-hide">
          <span class="input-group-addon">重量単位</span>
          <select name="weightUnit" class="form-control">
            <option value="00"></option>
            <option value="01">g</option>
            <option value="02">kg</option>
            <option value="03">t</option>
          </select>    
        </div>


        <div class="input-group setdate init-hide">
          <span class="input-group-addon">作成日時</span>
          <input name="setDate" type="text" class="form-control text-right" maxlength="16"/>
        </div>
        <div class="input-group updatedate init-hide">
          <span class="input-group-addon">更新日時</span>
          <input name="updateDate" type="text" class="form-control text-right" maxlength="16"/>
        </div>
        <div class="input-group apply-date init-hide">
          <span class="input-group-addon">適用日時</span>
          <input name="applyDate" type="text" class="form-control text-right" maxlength="16"/>
        </div>


        <div class="input-group error-distinction init-hide">
          <span class="input-group-addon">エラー区分</span>
          <input type="text" class="form-control" name="errorDistinction" maxlength="2"/>    
        </div>
        <div class="input-group del-distinction init-hide">
          <span class="input-group-addon">削除依頼区分</span>
          <select name="delDistinction" class="form-control">
            <option value="0"></option>
            <option value="1">削除する</option>
          </select>
        </div>
        <div class="input-group del-reason init-hide">
          <span class="input-group-addon">削除理由</span>
          <input type="text" class="form-control" name="delReason" maxlength="80">
        </div>
        <div class="input-group apply-state init-hide">
          <span class="input-group-addon">申請状態</span>
          <input type="text" class="form-control" name="applyState" maxlength="6"/>
        </div>


      </div>
    </div>
  </div>
  <div class="panel panel-primary item-image">
    <div class="panel-heading">
      <h3 class="panel-title">画像情報</h3>
    </div>
    <div class="panel-body">
      <div class="bl-flex-column">
          
        <div class="bl-flex-row">
            <button class="btn bl-btn-panel btn-all-image">全画像</button>
        </div>    
        
        <div class="bl-flex-row">
            
          <div>
            <button class="btn-left glyphicon glyphicon-arrow-left"></button>
          </div>
            
            <div class="bl-flex-row image-list">
              <input type="file" class="hide" accept="image/*" name="upload"/>   
              <div class="img1 image-container text-right">
                <div class="text-center"><span>全体</span></div>    
                <div>
                  <img id="item" class="img01 img-rounded img-responsive" name="img1" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>
                <div class="bl-flex-row">    
                  <button class="btn bl-btn-panel image-select" name="button1">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>

              <div class="img2 image-container text-right">
                <div class="text-center"><span>正面</span></div>  
                <div>
                  <img id="item" class="img02 img-rounded img-responsive" name="img2" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>  
                <div class="bl-flex-row">   
                  <button class="btn bl-btn-panel image-select" name="button2">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>

              <div class="img3 image-container text-right">
                <div class="text-center"><span>側面</span></div>  
                <div>
                  <img id="item" class="img03 img-rounded img-responsive" name="img3" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>  
                <div class="bl-flex-row">   
                  <button class="btn bl-btn-panel image-select" name="button3">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>
                
              <div class="img4 image-container text-right">
                <div class="text-center"><span>上</span></div>  
                <div>
                  <img id="item" class="img04 img-rounded img-responsive" name="img4" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>  
                <div class="bl-flex-row">   
                  <button class="btn bl-btn-panel image-select" name="button4">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>
                
              <div class="img5 image-container text-right">
                <div class="text-center"><span>下</span></div>  
                <div>
                  <img id="item" class="img05 img-rounded img-responsive" name="img5" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>  
                <div class="bl-flex-row">   
                  <button class="btn bl-btn-panel image-select" name="button5">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>
                
              <div class="img6 image-container text-right">
                <div class="text-center"><span>左</span></div>  
                <div>
                  <img id="item" class="img06 img-rounded img-responsive" name="img6" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>  
                <div class="bl-flex-row">   
                  <button class="btn bl-btn-panel image-select" name="button6">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>
                
              <div class="img7 image-container text-right">
                <div class="text-center"><span>右</span></div>  
                <div>
                  <img id="item" class="img07 img-rounded img-responsive" name="img7" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>  
                <div class="bl-flex-row">   
                  <button class="btn bl-btn-panel image-select" name="button7">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>
                
              <div class="img8 image-container text-right">
                <div class="text-center"><span>後ろ</span></div>  
                <div>
                  <img id="item" class="img08 img-rounded img-responsive" name="img8" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>  
                <div class="bl-flex-row">   
                  <button class="btn bl-btn-panel image-select" name="button8">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>
                
              <div class="img9 image-container text-right">
                <div class="text-center"><span>備考</span></div>  
                <div>
                  <img id="item" class="img09 img-rounded img-responsive" name="img9" alt="" src=""/>
                </div>
                <div class="text-center file-name"><span>画像名</span></div>  
                <div class="bl-flex-row">   
                  <button class="btn bl-btn-panel image-select" name="button9">選択</button>    
                  <button class="btn bl-btn-panel image-delete">削除</button>
                </div>
              </div>                
            </div>
            
          <div>
            <button class="btn-right glyphicon glyphicon-arrow-right"></button>
          </div>
            
        　
          <div class="hide">
            <span>画像アップロード:</span>
            <!-- <input type="text" class="form-control file-name" readonly/> -->
            <div class="file-select">  
              <button class="btn-link btn-all-select">ファイルを選択</button>
            </div>   
          </div>
        　
            
        </div>
          
      </div>
    </div>
  </div>
  <div class="button col-sm-12" style="text-align:center;">
    
      <button class="btn bl-btn-panel bl-btn-5 bl-btn-wiz-prev  back">
        <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>戻る
      </button>
    
    
      <button id="confirm" class="btn bl-btn-panel bl-btn-5 bl-btn-wiz-prev save">確定</button>
    
  </div>
</div>
<div class="imageupload">
<!--#############################################################-->
<div class="page-header">
      <h2 class="image-header">全画像</h2>
    </div>

<div class="container">
  
  <div class="panel panel-primary bl-flex-row">
    
      
        
      <!--9-->  
      <div class="image-left">
        <div class="bl-flex-row panel-primary">
          <div class="image-container img11">
            <div class="text-center">
              <span>全体</span>    
            </div>
            <div class="">
              <img name="img11" id="imageupload" class="imga img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name1">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button11">選択</button>
              <button class="btn bl-btn-panel image-delete">削除</button>
            </div>
          </div>
          <div class="div2 image-container text-right">
            <div class="text-center">
              <span>正面</span>    
            </div>
            <div>
              <img name="img12" id="imageupload" class="imgb img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name2">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button12">選択</button>
              <button class="btn bl-btn-panel image-delete delete-b">削除</button>
            </div>
          </div>
          <div class="image-container text-right">
            <div class="text-center">
              <span>側面</span>    
            </div>
            <div>
              <img name="img13" id="imageupload" class="imgc img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name3">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button13">選択</button>
              <button class="btn bl-btn-panel image-delete delete-c">削除</button>
            </div>
          </div>   
        </div>
        <div class="bl-flex-row">
          <div class="image-container text-right">
            <div class="text-center">
              <span>上</span>    
            </div>
            <div>
              <img name="img14" id="imageupload" class="imgd img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name4">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button14">選択</button>
              <button class="btn bl-btn-panel image-delete delete-d">削除</button>
            </div>
          </div>
          <div class="image-container text-right">
            <div class="text-center">
              <span>下</span>    
            </div>
            <div>
              <img name="img15" id="imageupload" class="imge img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name5">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button15">選択</button>
              <button class="btn bl-btn-panel image-delete delete-e">削除</button>
            </div>
          </div>
          <div class="image-container text-right">
            <div class="text-center">
              <span>左</span>    
            </div>
            <div>
              <img name="img16" id="imageupload" class="imgf img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name6">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button16">選択</button>
              <button class="btn bl-btn-panel image-delete delete-f">削除</button>
            </div>
          </div>   
        </div>
        <div class="bl-flex-row">
          <div class="image-container text-right">
            <div class="text-center">
              <span>右</span>    
            </div>
            <div>
              <img  name="img17" id="imageupload" class="imgg img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name7">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button17">選択</button>
              <button class="btn bl-btn-panel image-delete delete-g">削除</button>
            </div>
          </div>
          <div class="image-container text-right">
            <div class="text-center">
              <span>後ろ</span>    
            </div>
            <div>
              <img name="img18" id="imageupload" class="imgh img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name8">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button18">選択</button>
              <button class="btn bl-btn-panel image-delete delete-h">削除</button>
            </div>
          </div>
          <div class="image-container text-right">
            <div class="text-center">
              <span>備考</span>    
            </div>
            <div class="">
              <img name="img19" id="imageupload" class="imgi img-rounded img-responsive" alt="" src=""/>
            </div>
            <div class="text-center file-name"><span class="span-name9">画像名</span></div>
            <div class="bl-flex-row">
              <button class="btn bl-btn-panel image-select" name="button19">選択</button>
              <button class="btn bl-btn-panel image-delete delete-i">削除</button>
            </div>
          </div>   
        </div>
      </div>
        
        
        
      <!--1-->
      <div class="image-right">
        <div class="magnify">
          <div class="magnify_glass magnify_toggle"> </div>
	        <div class = "element_to_magnify">
		        <img name="1" class="img-max img-rounded img-responsive" src="" draggable="false"/>
	         </div>	         
        </div>
        <div class="file-all bl-flex-row">
            <span>画像アップロード:</span>
            <input type="file" class="hide" accept="image/*" multiple="multiple" name="multiUpload"/>
            <div class="file-select">  
              <button class="btn-link btn-all-select">ファイルを選択</button>
            </div>   
          </div>
        
          
        
          
          
       </div> 
        
      
  </div>
  <div class="text-center">
    <div class="text-center">
      <button class="btn bl-btn-panel bl-btn-5 bl-btn-wiz-prev imageupload-save">閉じる</button>
    </div>
  </div>
</div>
  <!--###########################################################-->>
</div>


<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="${baseurl}/lib/moment/moment-with-locales.min.js"></script>
<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
<script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>   
<script type="text/javascript" src="${baseurl}/js/applyperiodguide/applyperiodguide.js"></script>
<script type="text/javascript" src="${baseurl}/js/classifycodeguide/showClassifyCodeGuide.js"></script>
<script type="text/javascript" src="${baseurl}/js/common/inputFormat.js"></script>
<script type="text/javascript" src="${baseurl}/js/common/check.js"></script>
<script type="text/javascript">
  window.baseurl = "${baseurl}";
</script>
<!--personal js-->
<script type="text/javascript" src="${baseurl}/js/goodsdetail/item.js"></script>

</body>
</html>