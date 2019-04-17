app.controller("liveVerifyController", function($scope, $http) {

	// 给专业下拉列表提供数据
	$scope.typeBases = function() {
		$http.get("/api/course/selectCourseTypeSubclassNameAll", {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.nameList = result.data;
			}
		})
	};
	$scope.typeBases(); // 加载页面时调用
	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.page = 1;
	//一页展示多少行
	$scope.pageSize = 20;
	
	//页面展示
	$scope.loaddata = function () {
		$http.get("/api/questionbank/liveVerifyList",{"params":{"page":$scope.page,"mobile":$scope.mobile,"courseTypeSubclassName":$scope.courseTypeSubclassName,"roomId":$scope.roomId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == 0) {
				$scope.liveVerifyList = result.data;
				$scope.total = result.count;
			}else {
				alert(result.message);
			}
		})
	};
	$scope.loaddata();
});