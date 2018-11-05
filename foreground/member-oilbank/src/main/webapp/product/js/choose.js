$('#cityDanXuan').hsCheckData({
	isShowCheckBox: false, //默认为false
	data: cityData
});
$('#cityDuoXuan').hsCheckData({
	isShowCheckBox: true, //默认为false
	minCheck: 1,//默认为0，不限最少选择个数
	maxCheck: 0,//默认为0，不限最多选择个数
	data: cityData
});
$('#cityMR').hsCheckData({
	isShowCheckBox: true,
	data: cityData
});
