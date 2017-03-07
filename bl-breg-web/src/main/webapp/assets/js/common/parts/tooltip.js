/**
 * @file 情報表示補助用部品定義
 *
 * * BLUI.HoverLabel
 */

(function() {

    'use strict';

    /**
     * @class
     * @classdesc 文字列表示あふれ管理部品
     * 表示領域に文字列が収まりきれない場合、表示上は文字列を見切れさせて表示するが、マウスホバーで文字列全体を表示させたい場合に本部品を利用する。
     * 本部品は、静的クラス（オブジェクトリテラル）として定義。 newは不要。
     *
     * #### 使い方
     * ツールチップを利用したい要素を、デリゲート方式で指定するだけ。
     * 指定されたデリゲート要素内の対象セレクタ要素が文字列見切れしている場合、本部品が自動的にツールチップ表示を行う。
     */
    BLUtil.TextOverflowManager = {
        /**
         * 登録済みデリゲート配列
         * @private
         */
        _delegates: [],

        /**
         * デリゲート登録
         * @param $target
         * @param selector
         */
        addDelegate: function($target, selector) {
            var delegate = {
                target: $target,
                selector: selector
            };
            var find = this._findDelegate(delegate);
            if (!_.isUndefined(find)) {
                return;
            }

            $target.on('mouseover', selector, this.onMouseOverTarget);
            $target.on('mouseout', selector, this.onMouseOutTarget);
            this._delegates.push(delegate);
        },

        /**
         * デリゲート登録解除
         * @param $target
         * @param selector
         */
        removeDelegate: function($target, selector) {
            var find = this._findDelegate({
                target: $target,
                selector: selector
            });
            if (_.isUndefined(find)) {
                return;
            }

            $target.off('mouseover', selector, this.onMouseOverTarget);
            $target.off('mouseout', selector, this.onMouseOutTarget);

            this._delegates = _.reject(this._delegates, find);
        },

        _findDelegate: function(delegate) {
            return _.find(this._delegates, function(obj) {
                return _.isEqual(obj, delegate);
            });
        },

        /**
         * マウスオーバー時処理
         * 文字列の見切れが発生する場合、ツールチップ用の設定を行い、表示する
         * @param event
         * @private
         */
        onMouseOverTarget: function(event) {
            var $target = $(event.target);
            if (!BLUtil.TextOverflowManager.isOverflow($target)) {
                return;
            }
            if ($target.attr('title') !== $target.text() && !$target.attr('data-original-title')) {
                $target.attr({
                    title: $target.text(),
                    'data-trigger': 'manual'
                });
            }
            $target.tooltip().tooltip('show');
        },

        /**
         * マウスアウト時処理
         * ツールチップを表示にする
         * @param event
         * @private
         */
        onMouseOutTarget: function(event) {
            $(event.target).tooltip('hide');
        }
    };

    /**
     * 文字列が表示領域からはみだすかどうかの判定
     * @param $el
     * @returns {boolean}
     */
    BLUtil.TextOverflowManager.isOverflow = function($el) {
        var textWidth = BLUtil.getTextRect($el.text(), $el).width;
        return ($el.width() < textWidth);
    };
}());
