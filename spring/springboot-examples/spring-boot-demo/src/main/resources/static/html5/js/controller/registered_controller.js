/*******************************************************************************
 * @jsname:login_controller.js
 * @author:肖雄
 * @date:2015-10-11
 * @use:登陆js
 ******************************************************************************/
var biostimeMKTApp = angular.module('biostimeMKTApp', []);
biostimeMKTApp.config([ '$interpolateProvider', function($interpolateProvider) {
	$interpolateProvider.startSymbol('[[');
	$interpolateProvider.endSymbol(']]');
} ]);
biostimeMKTApp.config([ '$httpProvider', function($httpProvider) {
	//为请求添加X-Requested-With请求头
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
} ]);
biostimeMKTApp.controller("registeredController", function($scope, $http) {
	// 登陆
	$scope.registered = function(event){
		registered($scope, $http);
		stopBubble(event);
	};
});

/**
 * 注册
 * @param $scope
 * @param $http
 */
function registered($scope, $http) {
	var registeredAction=BASE_API_URL+"/user/registered";
	var userId = $scope.userId;
	var password = $scope.password;
	var username = $scope.username;
	var mobile =  $scope.mobile;
	var birth = $('#birth').val();
	if (Biostime.common.isEmpty(userId)) {
		Biostime.common.toastMessage("用户名不能为空!");
		return;
	}
	if (Biostime.common.isEmpty(password)) {
		Biostime.common.toastMessage("密码不能为空!");
		return;
	}
	if (Biostime.common.isEmpty(username)) {
		Biostime.common.toastMessage("用户昵称不能为空!");
		return;
	}
	if (Biostime.common.isEmpty(mobile)) {
		Biostime.common.toastMessage("手机号不能为空!");
		return;
	}
	if(isNaN(mobile) || mobile.length>11){
		Biostime.common.toastMessage("手机号不合法!");
		return;
	}
	if (Biostime.common.isEmpty(birth)) {
		Biostime.common.toastMessage("生日不能为空!");
		return;
	}
	var registeredData={
		"sourceSystem":"mkt",
		"seqNo":"12360",
		"request":{
			"userId":userId,
			"password":password,
			"userName":username,
			"mobile":mobile,
			"birthDay":birth
		}
	};
    consolePrint(registeredData);
	$http({
		method: 'POST',
		data:registeredData,
		url : registeredAction
	}).success(function(data, status, headers, config) {
		Biostime.common.hideLoading();
		if(data.code=="100"){
			alert("注册成功");
			window.location.href=loginUrl;
		} else{
			Biostime.common.toastMessage(data.desc);
		}
	}).error(function(data, status, headers, config) {
		Biostime.common.hideLoading();
		Biostime.common.showErrorMessage(status, data);
	});
}

