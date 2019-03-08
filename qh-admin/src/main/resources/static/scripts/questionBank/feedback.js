app.controller("feedbackController", function($scope, $http){
	
	
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    
    $scope.status = 0;
	
	$scope.feedbackList=function(){
		$scope.pageNo=( $scope.current-1)*$scope.pageSize;
		$http.get("/api/feedback/list",{"params": {"pageNo":$scope.pageNo,
			"pageSize":$scope.pageSize,"status":$scope.status,"nickname":$scope.nickname,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"name":$scope.name}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.feedbacklist=data.data;
				$scope.total=data.count;
				
			}
		})
	};
	
	$scope.feedbackList();
	
	$scope.updatefeedback=function(){
		if($scope.id!=null){
		$http.get("/api/feedback/update",{"params": {"id":$scope.id,
			"status":1}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				
			location.reload();
			}
			else{
				alert(data.message);
			}
		})
		}else{
			
			alert("请先选中信息");
		}
	};
	
	$scope.checkfeedback=function(fl){
		
		if($scope.selected!=fl){
		$scope.selected=fl;
		$scope.id=fl.id;
		
		}else{
			$scope.selected=null;
			$scope.id=null;
		}
	}
	
	
	$scope.reset=function(){
		
		document.getElementById('add').style.display="none"; 
		$scope.selected=null;
		$scope.cateId=null;
		$scope.questionCategory=null;
	}
	
	$scope.deletequestion=function(){
		if($scope.cateId!=null){
			
			if(confirm("您确定要删除这个试卷吗")){
				$http.get("/api/questionCategory/delete",{"params": {"id":$scope.cateId}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.id=null;
						location.reload();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	
	$scope.refresh=function(){
		
		location.reload();
	}
	
	
	
})