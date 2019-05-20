app.controller("studyRecordController", function($scope, $http) {

	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.page = 1;
	// 一页展示多少行
	$scope.numPerPage = 1000;

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
		//正则  输入长度必须为32位  只能由数字和大小写字母组成
		var re = new RegExp(/^[0-9A-Za-z]{32}$/);
		if ($scope.videoId != null && !re.test($scope.videoId) && "7461" != $scope.videoId.slice(-4)) {
			alert("视频Id格式不正确");
			return;
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
	//选中单行
	$scope.checkList = function (list) {
		if ($scope.selected == null) {
			$scope.selected = list;
		}else {
			$scope.selected = null;
		}
	}
	
	$scope.mobile=null;
	$scope.videoId=null;
	var finStatementExcelExport=function(){
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
		//正则  输入长度必须为32位  只能由数字和大小写字母组成
		var re = new RegExp(/^[0-9A-Za-z]{32}$/);
		if ($scope.videoId != null && !re.test($scope.videoId) && "7461" != $scope.videoId.slice(-4)) {
			alert("视频Id格式不正确");
			return;
		}
		window.location.href = "/api/video?page="+$scope.page+"&numPerPage="+$scope.numPerPage+
		"&mobile="+$scope.mobile+"&videoId="+$scope.videoId+"&date="+changeDate($scope.date)+"&types="+3;
	  }
	$scope.ExcelExport=function(){
		finStatementExcelExport();
	}
});