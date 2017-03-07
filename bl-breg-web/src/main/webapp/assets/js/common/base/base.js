/**
 * @fileoverview 共通定義
 * @requires underscore
 */

/* jshint ignore:start */

/** @namespace BL */
var BL = BL || {};

/** @namespace BLUI */
var BLUI = BLUI || {};

/** @namespace BLUtil */
var BLUtil = BLUtil || {};

/* jshint ignore:end */

/**
 * window.onerror時の処理
 * @param {string} message エラーメッセージ
 * @param {string} source エラーが発生したファイルのURL
 * @param {number} line - 行番号
 */
BL.onerror = function(message, source, line) {
    // TODO エラー処理
    console.log(message);
    console.log(source);
    console.log(line);
    return true;
};

// 環境別の処理
switch(BL.env) {
    case 'local':
        break;
    case 'development':
        break;
    case 'staging':     // not break
    case 'production':
        window.onerror = BL.onerror;
        break;
    default:
        break;
}

//=============================================================================
// console.log wrapper
//=============================================================================
// consoleメソッド一覧
console.patterns = [
    'log',
    'info',
    'warn',
    'error',
    'trace',
    'group',
    'groupEnd',
    'groupCollapsed',
    'time',
    'timeEnd',
    'assert',
    'dir'
];

// consoleメソッド無効化
if (BL.debug === false) {
    $.each(console.patterns, function(funcName) {
        console[funcName] = function() {};
    });
}


//=============================================================================
// utility
//=============================================================================

/**
 * jQueryオブジェクト判定
 * @param {*} obj
 * @returns {boolean}
 */
BLUtil.isJquery = function(obj) {
    return (obj instanceof jQuery);
};

/**
 * 文字列を描画したときの領域サイズを返す
 * @param {string} text
 * @param {{}|jQuery=} cssOrJQuery
 * @returns {{width: (number|JQuery), height: (number|JQuery)}}
 */
BLUtil.getTextRect = function(text, cssOrJQuery) {
    var css = {};
    if (_.isUndefined(cssOrJQuery)) {
        css = $('body');
    }
    if (cssOrJQuery instanceof jQuery) {
        css = cssOrJQuery.css(['font-size','font-weight','font-family']);
    }
    css = _.defaults({
        position: 'absolute',
        top: '-1000px',
        opacity: 0,
        display: 'inline'
    }, css);
    var $testLabel = $('<span></span>').text(text).css(css);
    $('body').append($testLabel);
    var rect = {
        width: $testLabel.outerWidth(),
        height: $testLabel.outerHeight()
    };
    $testLabel.remove();
    return rect;
};
