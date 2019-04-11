app.controller("questionbankH5Controller", function($scope, $http) {

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
	// 一页显示多少条
	$scope.pageSize = 20;
	
	//页面展示
	$scope.loaddata = function () {
		console.log("realname = "+$scope.realname);
		console.log("courseTypeSubclassName = "+$scope.courseTypeSubclassName);
		console.log("status = "+$scope.status);
		http.get("/api/questionbank/questionbankList",{"params":{"page":$scope.page,"realname":$scope.realname,"courseTypeSubclassName":$scope.courseTypeSubclassName,"status":$scope.status}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success.function (result) {
			if (result.status == 0) {
				alert("成功");
			}else {
				alert(result.message);
			}
		}
	};
});