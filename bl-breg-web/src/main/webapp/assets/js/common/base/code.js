/**
 * @fileoverview コード項目定義
 * 
 * このファイルは、05_コード項目定義書.xlsx から自動生成されたファイルです。
 * 直接変更しないでください。
 * 変更が必要な場合は、上記定義書を更新し、ツールを使用して再生成してください。
 */

/* jshint ignore:start */

/** @namespace BLENUM */
var BLENUM = BLENUM || {};

/* jshint ignore:end */

/** 論理削除区分 */
BLENUM.LogicDeleteDivEnum = {
    Valid: 0,    // 有効
    LogicDelete: 1,    // 論理削除
};

/** 本社機能区分 */
BLENUM.MainOfficeFuncDivEnum = {
    Office: 0,    // 事業所
    MainOffice: 1,    // 本社
};

/** 性別コード */
BLENUM.SexCodeEnum = {
    Male: 0,    // 男
    Female: 1,    // 女
    None: 2,    // なし
};

/** 受付担当者区分 */
BLENUM.ReceptionChargeDivEnum = {
    NoReceptioncharge: 0,    // 受付担当者でない
    IsReceptionCharge: 1,    // 受付担当者
};

/** 営業担当者区分 */
BLENUM.BusinessChargeDivEnum = {
    NoBusinessCharge: 0,    // 営業担当者ではない
    IsBusinessCharge: 1,    // 営業担当者
};

/** 社内・社外区分 */
BLENUM.InOutDivEnum = {
    InHouse: 0,    // 社内
    OutHouse: 1,    // 社外
};

/** アカウント登録区分 */
BLENUM.AccountRegDivEnum = {
    NormalCustomerReg: 1,    // 一般顧客登録
    StaffProxyReg: 2,    // 社員代行登録
    AgencyReg: 3,    // 代理店登録
    AgencyProxyReg: 4,    // 代理店代行登録
};

/** ログインロック区分 */
BLENUM.LoginLockDivEnum = {
    LoginAble: 0,    // ログイン可能アカウント
    LoginUnable: 1,    // ログイン不可アカウント
};

/** パスワード必須変更区分 */
BLENUM.PasswordChangeRequiredDivEnum = {
    NoNeedChange: 0,    // パスワード変更不要アカウント
    NeedChange: 1,    // パスワード必須変更アカウント
};

/** パスワード強度区分 */
BLENUM.PasswordStrengthDivEnum = {
    None: 0,    // 入力無し
    Best: 1,    // 安全度：最高
    High: 2,    // 安全度：高
    middle: 3,    // 安全度：中
    low: 4,    // 安全度：低
    LowestBelow8Char: 5,    // 安全度：最低（8文字以下）
    LowestNoCombChar: 6,    // 安全度：最低（文字種組み合わせ無し）
    LowestSimpleChar: 7,    // 安全度：最低（予測しやすい文字列が含まれる）
    LowestSameWithLoginid: 8,    // 安全度：最低（ログインIDと同一）
    LowestInvalidChar: 9,    // 安全度：最低（使用できない文字が含まれる）
};

/** 都道府県区分 */
BLENUM.PrefecturesOfDivEnum = {
    Hokkaido: 1,    // 北海道
    Aomori: 2,    // 青森県
    Iwate: 3,    // 岩手県
    Miyagi: 4,    // 宮城県
    Akita: 5,    // 秋田県
    Yamagata: 6,    // 山形県
    Fukushima: 7,    // 福島県
    Ibaraki: 8,    // 茨城県
    Tochigi: 9,    // 栃木県
    Gunma: 10,    // 群馬県
    Saitama: 11,    // 埼玉県
    Chiba: 12,    // 千葉県
    Tokyo: 13,    // 東京都
    Kanagawa: 14,    // 神奈川県
    Niigata: 15,    // 新潟県
    Toyama: 16,    // 富山県
    Ishikawa: 17,    // 石川県
    Fukui: 18,    // 福井県
    Yamanashi: 19,    // 山梨県
    Nagano: 20,    // 長野県
    Gifu: 21,    // 岐阜県
    Shizuoka: 22,    // 静岡県
    Aichi: 23,    // 愛知県
    Mie: 24,    // 三重県
    Shiga: 25,    // 滋賀県
    Kyoto: 26,    // 京都府
    Osaka: 27,    // 大阪府
    Hyogo: 28,    // 兵庫県
    Nara: 29,    // 奈良県
    Wakayama: 30,    // 和歌山県
    Tottori: 31,    // 鳥取県
    Shimane: 32,    // 島根県
    Okayama: 33,    // 岡山県
    Hiroshima: 34,    // 広島県
    Yamaguchi: 35,    // 山口県
    Tokushima: 36,    // 徳島県
    Kagawa: 37,    // 香川県
    Ehime: 38,    // 愛媛県
    Kochi: 39,    // 高知県
    Fukuoka: 40,    // 福岡県
    Saga: 41,    // 佐賀県
    Nagasaki: 42,    // 長崎県
    Kumamoto: 43,    // 熊本県
    Oita: 44,    // 大分県
    Miyazaki: 45,    // 宮崎県
    Kagoshima: 46,    // 鹿児島県
    Okinawa: 47,    // 沖縄県
};

/** 国区分 */
BLENUM.CountryDivEnum = {
    Japan: 1,    // 日本
};

/** 敬称区分 */
BLENUM.HonorificnameDivEnum = {
    Dear: 0,    // 様
    Esq: 1,    // 殿
    Messrs: 2,    // 御中
};

/** 単位コード */
BLENUM.UnitCdEnum = {
    None: 0,    // 単位なし
    Piece: 1,    // 個
    Set: 2,    // 一式
    Sheet: 3,    // 枚
    Box: 4,    // 箱
    L: 5,    // L
};


/** 提供データ区分 */
BLENUM.OfferDataDivEnum = {
    UserRegist: 0,    // ユーザ登録
    BLOfferData: 1,    // BL提供データ
};

/** 画面モード区分 */
BLENUM.ModeEnum = {
    New: 0,         //検索入力モード
    Update: 1,      // 選択モード
    Readonly: 2,    // 詳細（参照）
    Error: 3,       // エラー編集
    DetailAdd:4		//詳細新規
};
/** 画面区分 */
BLENUM.MenuEnum={
	loginmaker:0,    		// ログイン
	forgotpwdmaker:1, 		// ログインパスワード忘れ画面
	loginsendmaker:2,		// ログイン送信完了
	TopMenu:3,				// メインメニュー「メニュー(共通)、トップページ」
    Item:4,         		// 商品一覧
    goodsdetail:5,			// 商品詳細
    Set:6,          		// セット一覧
    setdetail:7,			// セット詳細
    Union:8,        		// 結合一覧
    joindetail:9,			// 結合詳細
    checklist:10,			// チェックリスト
    classifycodeguide:11, 	// 商品中分類コードガイド
    changecommon:12,		// 置換画面
    imageupload:13,			// 全画像
    goodsguide:14,			// 商品ガイド
    fileselect:15,			// ファイル選択
    importresult:16,		// インポート結果
    readresult:17,			// 取込完了
    applycommon:18,			// 申請
    applydetail:19,			// 申請詳細
    applied:20,				// 申請完了
    applynewcategory:21,	// 申請(新規品目)
    applyhistory:22,		// 申請履歴一覧
    applyperiodguide:23,	// 申請期間ガイド
    totalinfo:24,			// 累積情報
    companysetting:25,		// 会社情報
    usersetting:26,			// 担当者情報
    goodscorrect:27,		// 商品一覧(エラー修正)
    setcorrect:28,			// セット一覧(エラー修正)
    joincorrect:29,			// 結合一覧(エラー修正)
}

/** 画面区分 */
BLENUM.EMPEnum={
    ComInfo:0,         // 会社情報
    SetInfo:1,          // 設定情報
}

/** 申請履歴一覧画面区分 */
BLENUM.HISTORYEnum={
    MODE1:1,         // トップページから
    MODE2:2,          // 申請処理メニューから
    MODE3:3,          // 申請(新規品目)から
    MODE4:4,          // 申請メニューから
    MODE5:5           // 申請６メニューから
}

/** 申請(新規品目)画面区分 */
BLENUM.ApplyNewEnum={
    MODE1:1,         	// メインメニューから
    MODE2:2,          	// 申請履历から(申請中 承認済)
    MODE3:3,          	// 申請履历から (申請戻し)
    MODE4:4          	// 申請履历から (申請再提出)
}
/**申請状態*/
BLENUM.ApplyConditionEnum={
		NoApply:0,
		Apply:1,
		Approval:2,
		Applyagain:3
}
/**処理区分 */
BLENUM.ManageKbnEnum={
		Add:0,
		Update:1,
		Delete:2
}
/**対象区分 */
BLENUM.ObjectKbnEnum={
		GoodsKbn:1,
		SetKbn:2,
		JoinKbn:3
}
/**状態の判断*/
BLENUM.JudgeEnum={
		Add:0,
		UnChange:1,
		Update:2,
		Delete:3
}