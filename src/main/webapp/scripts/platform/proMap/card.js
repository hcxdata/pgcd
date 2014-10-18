//获取元素到视口的左边距离
function getElementViewLeft(event) {
	return event.target.getMouse().mouseX;
}
// 获取元素到视口的上边距离
function getElementViewTop(event) {
	return event.target.getMouse().mouseY;
}

// 绑定事件
function showCard(event) {

	var vTop = getElementViewTop(event);
	var vLeft = getElementViewLeft(event);

	var thisHeight = $(this).height(), thisWidth = $(this).width();
	var $uCard = $("#uCard");
	// top 需要加上滚动条的高度，因为名片box是相对于window的定位
	var uCardPosTop = vTop, uCardPosLeft = vLeft, uCardHeight = $uCard.height();
	var $layerArrow = $("#uCard .layerArrow");
	$layerArrow.removeClass().addClass("layerArrow");
	if (uCardHeight > vTop) {
		uCardPosTop = uCardPosTop;
		$layerArrow.addClass("layerArrow_t"); // 名片尖角在上
	} else {
		uCardPosTop = uCardPosTop;
		$layerArrow.addClass("layerArrow_b"); // 名片尖角在下
	}
	$uCard.css({
		"top" : uCardPosTop + "px",
		"left" : uCardPosLeft + "px"
	});

	$uCard.show();
};

function hideCard() {
	$("#uCard").hide();
}
$("#uCard").mouseover(function() {
	$(this).show();
});
$("#uCard").mouseout(function() {
	$(this).hide();
});
// 更新微博内容
function updateCardContent(obj) {

	$('#weibourl2').attr('href', obj.url).attr('title', obj.title).text(
			obj.title);

}