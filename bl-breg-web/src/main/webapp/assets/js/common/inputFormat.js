var newNum;

//url
var strRegex = '((http|ftp|https)://)(([a-zA-Z0-9\._-]+\.[a-zA-Z]{2,6})|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\&%_\./-~-]*)?';
var urlRegex = new RegExp(strRegex);
// 半角
var gaHankaku = new Array("ｶﾞ", "ｷﾞ", "ｸﾞ", "ｹﾞ", "ｺﾞ", "ｻﾞ", "ｼﾞ", "ｽﾞ", "ｾﾞ",
		"ｿﾞ", "ﾀﾞ", "ﾁﾞ", "ﾂﾞ", "ﾃﾞ", "ﾄﾞ", "ﾊﾞ", "ﾋﾞ", "ﾌﾞ", "ﾍﾞ", "ﾎﾞ", "ﾊﾟ",
		"ﾋﾟ", "ﾌﾟ", "ﾍﾟ", "ﾎﾟ", "ｳﾞ", 'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ', 'ｬ', 'ｭ', 'ｮ',
		'ｱ', 'ｲ', 'ｳ', 'ｴ', 'ｵ', 'ｶ', 'ｷ', 'ｸ', 'ｹ', 'ｺ', 'ｻ', 'ｼ', 'ｽ', 'ｾ',
		'ｿ', 'ﾀ', 'ﾁ', 'ﾂ', 'ﾃ', 'ﾄ', 'ﾅ', 'ﾆ', 'ﾇ', 'ﾈ', 'ﾉ', 'ﾊ', 'ﾋ', 'ﾌ',
		'ﾍ', 'ﾎ', 'ﾏ', 'ﾐ', 'ﾑ', 'ﾒ', 'ﾓ', 'ﾔ', 'ﾕ', 'ﾖ', 'ﾗ', 'ﾘ', 'ﾙ', 'ﾚ',
		'ﾛ', 'ﾜ', 'ﾝ', 'ｦ', 'ｰ', 'ｯ', '･', '－', '&', '<', '>', '\"', '\'',
		'\\\\', '～', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
		'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
		'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
		'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
		'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

// 全角
var gaZenkaku = new Array('ガ', 'ギ', 'グ', 'ゲ', 'ゴ', 'ザ', 'ジ', 'ズ', 'ゼ', 'ゾ',
		'ダ', 'ヂ', 'ヅ', 'デ', 'ド', 'バ', 'ビ', 'ブ', 'ベ', 'ボ', 'パ', 'ピ', 'プ', 'ペ',
		'ポ', 'ヴ', 'ァ', 'ィ', 'ゥ', 'ェ', 'ォ', 'ャ', 'ュ', 'ョ', 'ア', 'イ', 'ウ', 'エ',
		'オ', 'カ', 'キ', 'ク', 'ケ', 'コ', 'サ', 'シ', 'ス', 'セ', 'ソ', 'タ', 'チ', 'ツ',
		'テ', 'ト', 'ナ', 'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'ヒ', 'フ', 'ヘ', 'ホ', 'マ', 'ミ',
		'ム', 'メ', 'モ', 'ヤ', 'ユ', 'ヨ', 'ラ', 'リ', 'ル', 'レ', 'ロ', 'ワ', 'ン', 'ヲ',
		'ー', 'ッ', '・', '―', '＆', '＜', '＞', '”', '’', '￥', '―', "０", "１", "２",
		"３", "４", "５", "６", "７", "８", "９", 'Ａ', 'Ｂ', 'Ｃ', 'Ｄ', 'Ｅ', 'Ｆ', 'Ｇ',
		'Ｈ', 'Ｉ', 'Ｊ', 'Ｋ', 'Ｌ', 'Ｍ', 'Ｎ', 'Ｏ', 'Ｐ', 'Ｑ', 'Ｒ', 'Ｓ', 'Ｔ', 'Ｕ',
		'Ｖ', 'Ｗ', 'Ｘ', 'Ｙ', 'Ｚ', 'ａ', 'ｂ', 'ｃ', 'ｄ', 'ｅ', 'ｆ', 'ｇ', 'ｈ', 'ｉ',
		'ｊ', 'ｋ', 'ｌ', 'ｍ', 'ｎ', 'ｏ', 'ｐ', 'ｑ', 'ｒ', 'ｓ', 'ｔ', 'ｕ', 'ｖ', 'ｗ',
		'ｘ', 'ｙ', 'ｚ');

// 半角を全角に変換する
function hankaku2Zenkaku(str) {
	var re;
	var sret = str;
	if (sret != '') {
		for (j = 0; j < gaHankaku.length; j++) {
			eval("re=/" + gaHankaku[j] + "/g");
			str = str.replace(re, gaZenkaku[j]);
		}
	}
	return sret;
}

// 全角を半角に変換する
function zenkaku2Hankaku(str) {
	var re;
	var sret = str;
	if (sret != '') {
		for (j = 0; j < gaZenkaku.length; j++) {
			eval("re=/" + gaZenkaku[j] + "/g");
			sret = sret.replace(re, gaHankaku[j]);
		}
	}
	return sret;
}

// 数字だけ入力可
function numInput(id) {
	var num = document.getElementById(id).value;
	document.getElementById(id).onpaste = function() {
		newNum = document.getElementById(id).value;
	}
	if ("undefined" != typeof newNum) {
		if (num.length != num.replace(/[^\d]/g, '').length) {
			document.getElementById(id).value = newNum;
			newNum = undefined;
			return true;
		}
	}
	document.getElementById(id).value = num.replace(/[^\d]/g, '');
}

// 金額
function moneyInput(id) {
	var num = document.getElementById(id).value;
	document.getElementById(id).onpaste = function() {
		newNum = document.getElementById(id).value;
	}
	if ("undefined" != typeof newNum) {
		if (num.length != num.replace(/[^\d|.]/g, '').length) {
			document.getElementById(id).value = newNum;
			newNum = undefined;
			return true;
		}
	}
	document.getElementById(id).value = num.replace(/[^\d|.]/g, '');
}

// QTY
function doubleInputForQTY(id) {
	var num = document.getElementById(id).value;
	var patt = /^\d{1,3}(\.\d{0,2}){0,1}/;
	document.getElementById(id).value = patt.exec(num) == null ? "" : patt
			.exec(num)[0];
}

// 半角だけ
function smallInput(id) {
	var value = document.getElementById(id).value;
	document.getElementById(id).onpaste = function() {
		newNum = document.getElementById(id).value;
	}
	if (undefined != newNum) {
		if (!checkHankaku(value)) {
			value = newNum;
		} else {
			value = zenkaku2Hankaku(value);
		}
		newNum = undefined;
		return true;
	}
	if (!checkHankaku(value)) {
		value = value.substr(0, value.length - 1);
	} else {
		value = zenkaku2Hankaku(value);
	}
	document.getElementById(id).value = value;
}

// 0-9 a-zA-Z .-/_
function charInput(id) {
	var num = document.getElementById(id).value;
	document.getElementById(id).onpaste = function() {
		newNum = document.getElementById(id).value;
	}
	if ("undefined" != typeof newNum) {
		if (num.length != num.replace(/[^(0-9)|(a-zA-Z)|(.-/_)]/g, '').length) {
			document.getElementById(id).value = newNum;
			newNum = undefined;
			return true;
		}
	}
	document.getElementById(id).value = num.replace(
			/[^((0-9)|(a-zA-Z)|(./-_))]/g, '');
}

// 0-9 a-z A-Z -
function charAndBarInput(id) {
	var num = document.getElementById(id).value;
	document.getElementById(id).onpaste = function() {
		newNum = document.getElementById(id).value;
	}
	if ("undefined" != typeof newNum) {
		if (num.length != num.replace(/[^(0-9)|(a-zA-Z)|(\-)]/g, '').length) {
			document.getElementById(id).value = newNum;
			newNum = undefined;
			return true;
		}
	}
	document.getElementById(id).value = num.replace(
			/[^((0-9)|(a-zA-Z)|(\-))]/g, '');
}

// 0-9 a-z A-Z
function charOnlyInput(id) {
	var num = document.getElementById(id).value;
	document.getElementById(id).onpaste = function() {
		newNum = document.getElementById(id).value;
	}
	if ("undefined" != typeof newNum) {
		if (num.length != num.replace(/[^(0-9)|(a-zA-Z)]/g, '').length) {
			document.getElementById(id).value = newNum;
			newNum = undefined;
			return true;
		}
	}

    document.getElementById(id).value = num.replace(/[^((0-9)|(a-zA-Z))]/g, '');
}

//数値フォーマット
function format_number(id) {
	var num = document.getElementById(id).value;
	if (num == null || num == "") {
		document.getElementById(id).value = "";
	} else {
		var b = parseInt(num).toString();
		var len = b.length;
		if (len <= 3) {
			document.getElementById(id).value = b;
		} else {
			var r = len % 3;
			document.getElementById(id).value = r > 0
					? b.slice(0, r) + "," + b.slice(r, len).match(/\d{3}/g).join(",")
					: b.slice(r, len).match(/\d{3}/g).join(",");
		}
	}
}