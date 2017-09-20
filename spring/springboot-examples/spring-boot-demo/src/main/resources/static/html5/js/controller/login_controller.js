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
biostimeMKTApp.controller("loginController", function($scope, $http) {
	// 登陆
	$scope.login = function(event){
		login($scope, $http);
		stopBubble(event);
	};

	// 点击编辑
	$scope.toRegisterdUrl = function(event) {
		toRegisterdUrl($scope, $http);
		stopBubble(event);
	};
});

/**
 * 登陆
 * @param $scope
 * @param $http
 */
function login($scope, $http) {
	var loginAction=BASE_API_URL+"/user/login";
	//Biostime.common.showLoading();

	var userId = $scope.userId;
	var password = $scope.password;
	if (Biostime.common.isEmpty(userId)) {
		Biostime.common.toastMessage("用户名不能为空!");
		return;
	}
	if (Biostime.common.isEmpty(password)) {
		Biostime.common.toastMessage("密码不能为空!");
		return;
	}

	var loginData={
		"sourceSystem":"mkt",
		"seqNo":"12360",
		"request": {
			"userId": userId,
			"password": password
			}
	    };
    consolePrint(loginData);
	$http({
		method: 'POST',
		url : loginAction,
		data:loginData
	}).success(function(data, status, headers, config) {
		Biostime.common.hideLoading();
		if(data.code=="100"){
			window.location.href=searchUserUrl;
		} else{
			Biostime.common.toastMessage(data.desc);
		}
	}).error(function(data, status, headers, config) {
		Biostime.common.hideLoading();
		Biostime.common.showErrorMessage(status, data);
	});
}
/**
 * 跳转到注册页面
 * @param $scope
 * @param $http
 */
function toRegisterdUrl($scope, $http) {
	window.location.href=registeredUrl;
}

