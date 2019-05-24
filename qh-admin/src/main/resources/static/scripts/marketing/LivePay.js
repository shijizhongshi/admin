app.controller("livePayController", function($scope, $http) {

	// 总条数
	$scope.total = 0;
	// 当前的页数
	$scope.page = 1;
	// 一页展示多少行
	$scope.pageSize = 20;

	$scope.status="";
	$scope.livename="";
	$scope.startTime=null;
	var changeDate = function (date) { 
		if(date){
		  	var y = date.getFullYear();  
		    var m = date.getMonth() + 1;  
		    m = m < 10 ? '0' + m : m;  
		    var d = date.getDate();  
		    d = d < 10 ? ('0' + d) : d;
		    var h = date.getHours();  
		    h = h < 10 ? ('0' + h) : h;
		    var M = date.getMinutes();  
		    M = M < 10 ? ('0' + M) : M;
		    return y + '-' + m + '-' + d+" "+h+":"+M+":"+"00";
		} else{
			return '';
		}
	      
	};
	$scope.LivePayList=function(){
		
		$scope.pageNo=($scope.page-1)*$scope.pageSize
		$http.get("/api/livepay/list",{"params":{"status":$scope.status,"startTime":changeDate($scope.startTime),"livename":$scope.livename,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}},{'Content-Type' : 'application/json;charset=UTF-8'})
		.success(function (data) {
			if(data.status=="0"){
				$scope.LivePaylist=data.data;
				$scope.total=data.count;
				angular.forEach($scope.LivePaylist,function(livepay){
					if(livepay.status==1){
						livepay.status="支付成功"
					}else if(livepay.status==0){
						livepay.status="生成订单"
					}
					livepay.livename=livepay.livename+livepay.startTime
					
				})
			}else{
				alert("出错了~")
			}
		})
		
	}
	$scope.LivePayList();
	//选中单行
	$scope.checkList = function (livepay) {
		if ($scope.selected != livepay) {
			$scope.selected = livepay;
		}else {
			$scope.selected = null;
		}
	}
	var finStatementExcelExport=function(){
		if($scope.startTime!=null){
		window.location.href = "/api/livepay?status="+1+"&startTime="+changeDate($scope.startTime)+"&types="+5+"&livename="+$scope.livename;
		}else{
			alert("请选择时间");
		}
	  }
	$scope.ExcelExport=function(){
		finStatementExcelExport();
	}
	
});