app.controller("studyRecordController", function($scope, $http) {

	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.page = 1;
	// 一页展示多少行
	$scope.numPerPage = 20;

	// 格式化日期 yyyy-MM-dd
	var changeDate = function(date) {
		if (date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			m = m < 10 ? '0' + m : m;
			var d = date.getDate();
			d = d < 10 ? ('0' + d) : d;
			return y + '-' + m + '-' + d;
		} else {
			return '';
		}
	};
	// 页面展示
	$scope.loaddata = function() {
		var time = Date.parse(new Date());
		if ($scope.date == null || $scope.date > time) {
			alert("日期错误~");
			return;
		}
		// 重置字符串 能优化吧...
		if ($scope.mobile == null || $scope.mobile.length == 0) {
			$scope.mobile = null;
		}
		if ($scope.videoId == null || $scope.videoId.length == 0) {
			$scope.videoId = null;
		}
		$http.get("/api/questionbank/video", {
			"params" : {
				"page" : $scope.page,
				"numPerPage" : $scope.numPerPage,
				"mobile" : $scope.mobile,
				"videoId" : $scope.videoId,
				"date" : changeDate($scope.date)
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(result) {
			if (result.status == "0") {
				$scope.list = result.data;
				$scope.total = result.count;
				return;
			}else {
				alert(result.message);
			}
		})
	}
});