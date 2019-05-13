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
	//一页展示多少行
	$scope.pageSize = 1000;
	
	//页面展示
	$scope.loaddata = function () {
		$http.get("/api/questionbank/questionbankList",{"params":{"page":$scope.page,"realname":$scope.realname,"courseTypeSubclassName":$scope.courseTypeSubclassName,"status":$scope.status}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function (result) {
			if (result.status == 0) {
				$scope.questionbankList = result.data;
				angular.forEach($scope.questionbankList,function(questionbank){
					
					if(questionbank.status=="0"){
						questionbank.statuss="app注册用户";
					}else if(questionbank.status=="1"){
						questionbank.statuss="游客用户";
					}
					if(questionbank.updatetime==null){
						questionbank.time=questionbank.addtime;
					}else {
						questionbank.time=questionbank.updatetime;
					}
					if(questionbank.banktotal - questionbank.nobank != 0){
						questionbank.probability=parseInt(questionbank.banktrue / (questionbank.banktotal - questionbank.nobank) *100)+"%";
					}else {
						questionbank.probability="用户未开始做题";
					}
				})
				$scope.total = result.count;
			}else {
				alert(result.message);
			}
		})
	};
	$scope.loaddata();
	
	//选中单行
	$scope.checkQuestionbank = function(questionbank) {
		$scope.selected = questionbank;
	}
});