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
biostimeMKTApp.controller("searchController", function($scope, $http) {
	//加载默认查询值
	loadUserList($scope, $http,1);
	// 查询
	$scope.onSearchUser = function(event){
		loadUserList($scope, $http,1);
		stopBubble(event);
	};
});

/**
 * 查询
 * @param $scope
 * @param $http
 */
function loadUserList($scope, $http, pageNo) {
	function searchAtionPackge(pageNo) {
		var searchUserAction = BASE_API_URL + "/user/searchUser";
		var userName = $scope.userName;
		var mobile = $scope.mobile;
		var searchUserData = {
			"sourceSystem": "mkt",
			"request": {
				"pageNo": pageNo,
				"pageSize": 20,
				"entity":{"userName": userName,
					"mobile": mobile}
			}
		};
		return {searchUserAction: searchUserAction, searchUserData: searchUserData};
	}

	var mySearchAtion = searchAtionPackge(pageNo);
	var searchUserAction = mySearchAtion.searchUserAction;
	var searchUserData = mySearchAtion.searchUserData;
	consolePrint(searchUserData);
	$http({
		method: 'POST',
		data: searchUserData,
		url: searchUserAction
	}).success(function (data, status, headers, config) {
		Biostime.common.hideLoading();
		//按钮处理是否显示
		if(data.response.otherAttribute['login']!=null){
			$scope.searchUser = false;
		}

		if (data.code == "100") {
			$scope.users = data.response.datas;
			var pageSize = data.response.pageSize;
			var pageCount = data.response.totalPage;
			$scope.noPager = (pageCount > 1);
			//分页逻辑
			$scope.pagination = {
				numPages: pageCount,
				page: pageNo,
				//第一页
				firstPage: function () {
					this.page = 1;
					getPageData(this.page);
				},
				//最后一页
				lastPage: function () {
					this.page = this.numPages;
					getPageData(this.page);
				},
				//是第一页
				isFirstPage: function () {
					if (this.page == 1) {
						return true;
					}
				},
				//是最后一页
				isLastPage: function () {
					if (this.page == this.numPages) {
						return true;
					}
				},
				//上一页
				prevPage: function () {
					if (this.page > 1) {
						this.page -= 1;
						getPageData(this.page);
					}
				},
				//下一页
				nextPage: function () {
					if (this.page < this.numPages) {
						this.page += 1;
						getPageData(this.page);
					}
				}
			};
			/*
			 *获取分页数据
			 * @param page, 页码
			 */
			function getPageData(pageNo) {
				var mySearchAtion = searchAtionPackge(pageNo);
				var searchUserAction = mySearchAtion.searchUserAction;
				var searchUserData = mySearchAtion.searchUserData;
				consolePrint(searchUserData);
				$http({
					method: 'POST',
					data: searchUserData,
					url: searchUserAction
				}).success(function (data, status, headers, config) {
					if (data.code == "100") {
						Biostime.common.hideLoading();
						$scope.users = data.response.datas;
					} else {
						Biostime.common.toastMessage(data.desc);
					}
				}).error(function (data, status, headers, config) {
					Biostime.common.hideLoading();
					Biostime.common.showErrorMessage(status, data);
				});
			}
		} else {
			Biostime.common.toastMessage(data.desc);
		}
	}).error(function (data, status, headers, config) {
		Biostime.common.hideLoading();
		Biostime.common.showErrorMessage(status, data);
	});
}

