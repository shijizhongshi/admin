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
	var changeDate = function (date) { 
		if(date){
		  	var y = date.getFullYear();  
		    var m = date.getMonth() + 1;  
		    m = m < 10 ? '0' + m : m;  
		    var d = date.getDate();  
		    d = d < 10 ? ('0' + d) : d;  
		    return y + '-' + m + '-' + d;
		} else{
			return '';
		}
	      
	};
	$scope.typeBases(); // 加载页面时调用
	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.page = 1;
	//一页展示多少行
	$scope.pageSize = 20;
	
	$scope.liveVerifyLists=[];
	//页面展示
	$scope.loaddata = function () {
		$scope.pageNo=( $scope.page-1)*$scope.pageSize;
		$http.get("/api/questionbank/liveVerifyList",{"params":{"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
			"fromdate":changeDate($scope.fromdate),"todate":changeDate($scope.todate),"mobile":$scope.mobile,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"roomId":$scope.roomId}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
	$scope.fromdate="";
	$scope.todate="";
	$scope.mobile="";
	$scope.roomId="";
	$scope.courseTypeSubclassName="";
	var finStatementExcelExport=function(){
	    window.location.href = "/api/excel/exportTest?pageNo="+$scope.pageNo+"&pageSize="+0+
	    "&fromdate="+changeDate($scope.fromdate)+"&todate="+changeDate($scope.todate)+"&mobile="+
	    $scope.mobile+"&courseTypeSubclassName="+$scope.courseTypeSubclassName+"&roomId="+$scope.roomId;
	  }
	$scope.ExcelExport=function(){
		finStatementExcelExport();
	}
});