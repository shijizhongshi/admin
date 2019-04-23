app.controller("LineWhiteController", function($scope, $http,$sce){

	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    
    $scope.lineWhite=null;
    $scope.lineWhitelist=[];
    $scope.lineWhiteBases=function(){
		$scope.pageNo=($scope.page-1)*$scope.pageSize;
		$http.get("/api/LineWhite/list",{"params": {"pageNo":$scope.pageNo,"mobile":$scope.mobile,"pageSize":$scope.pageSize,"liveId":$("#liveId").val()}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.lineWhitelist=data.data;
				$scope.total=data.count;
				
			}
		})
	}
	
	$scope.lineWhiteBases();
	////////////////以上是通过不同的条件查章节的集合的	
	
	$scope.id=null;
	
	$scope.addlineWhite=function(){
		$scope.lineWhite.id=$scope.id;
		$scope.lineWhite.liveId=$("#liveId").val();
		$http.post("/api/LineWhite/insert",$scope.lineWhite,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("保存成功~");
	    		document.getElementById('add').style.display="none"; 
	    		$scope.selected=null;
	    		$scope.lineWhiteBases();
	    	}else{
	    		alert(data.message);
	    	}
		})
	};
	
	$scope.updatelineWhite=function(){
		$http.post("/api/LineWhite/update",$scope.lineWhite,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("更新成功~");
	    		document.getElementById('add').style.display="none"; 
	    		$scope.selected=null;
	    		$scope.lineWhiteBases();
	    	}else{
	    		alert(data.message);
	    	}
		})
	};
	$scope.courseChapterId=null;
	///////做选中的时候用
	$scope.checkedlineWhite=function(l){
		if($scope.selected!=l){
		$scope.selected=l;
		$scope.lineWhite=l;
		$scope.id=l.id;
		}
		else{
			$scope.selected=null;
			$scope.lineWhite=null;
			$scope.id=null;
		}
	}
	
	$scope.add=function(){
		$scope.lineWhite=null;
		$scope.id=null;
		document.getElementById('add').style.display="block"; 
		
		
	}
	////点击修改的按钮先看看是否已经选中了
	$scope.update=function(){
		if($scope.id!=null){
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
		
	}
	
	$scope.uploadL=function(){
		document.getElementById('revise').style.display="block"; 
	}
	$scope.uploadlineWhite=function(){
		var fd = new FormData();
	    fd.append("file", $("#file")[0].files[0]);
	    fd.append("liveId",$("#liveId").val());
	    
	    
	    $http.post("/api/LineWhite/improtExcel",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	alert("上传成功~");
	    	document.getElementById('revise').style.display="none"; 
	    	$scope.lineWhiteBases();
		})
	}
	
	$scope.deletelineWhite=function(){
		if($scope.id!=null){
			////删除白名单/
			if(confirm("您确定要删除这个用户吗")){
				$http.get("/api/LineWhite/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.lineWhiteBases();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	$scope.deleteAll=function(){
		////删除白名单/
			if(confirm("您确定要清空这个白名单吗")){
				$http.get("/api/LineWhite/deleteAll",{"params": {"liveId":$("#liveId").val()}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("清除成功~");
						$scope.lineWhiteBases();
					}else{
						alert("清除失败~");
					}
				})
			}
		
	}
	$scope.reset1=function(){
		$scope.selected=null;
		$scope.lineWhite=null;
		$scope.id=null;
		document.getElementById('add').style.display="none"; 
	}
	$scope.reset=function(){
		
		document.getElementById('revise').style.display="none"; 
	}
	
	
});