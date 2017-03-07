/**
 * パレル関連共通クラス
 *
 * * BLUI.SystemFooter
 */


$(function () {
    /**
     * Slideパレル
     */
    BLUI.SlidePanel = function () {
        var head = $(".slide-panel .panel-heading");
        head.addClass("panel-heading-down");
        head.append("<span class='glyphicon glyphicon-chevron-down'></span>");

        head.click(function () {
            $(this).next().slideToggle(function(){$(window).trigger("resize");});
            $(this).parent().toggleClass("slide-down");
        });
    };
    
    // スライドパレル作成
    var panel = new BLUI.SlidePanel();
});
