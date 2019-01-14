app.controller("CourseClassTemplateController",function($scope,$http){
	
	
	
   $scope.total = 0;
   //当前的页数
   $scope.page = 1;
   //一页显示多少条
   $scope.pageSize = 20;
   
   $scope.id=null;
   
   /////查询
   $scope.templateBases=function(){
	   
		$http.get("/api/classtemplate/select",{"params": {"id":$scope.id,"page":$scope.page}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.templatelist=data.data;
			}
		})
	}
	$scope.templateBases();
	
	$scope.classUrl=null;
	
	$scope.uploadmainimage = function(file){
		
		
		if(!file.files || file.files.length < 1) return;

		
	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/single",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	        
	    })
	    .success(function(data){
	    	$scope.classUrl=data.data;
	    	
		})
	};
	
	
	////保存
	$scope.addTemplate=function(){
		$http.post("/api/classtemplate/save",$scope.courseClassTemplate,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		
	    			alert("保存成功~");
	    			document.getElementById('add').style.display="none"; 
		    		$scope.templateBases();
	    	}else{
	    		alert("保存失败~");
	    	}
	    	
	    })
	}
	
	///////做选中的时候用
	$scope.checkedtemplate=function(t){
		$scope.selected=t;
		$scope.courseClassTemplate=t;
		$scope.id=t.id;
	}
	////点击修改的按钮先看看是否已经选中了
	$scope.update=function(){
		if($scope.id!=null){
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
		
	}
////修改
	$scope.updateTemplate=function(){
		$http.post("/api/classtemplate/update",$scope.courseClassTemplate,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		
	    			alert("修改成功~");
	    			document.getElementById('add').style.display="none"; 
	    			$scope.id=null;
		    		$scope.templateBases();
	    	}else{
	    		alert("修改失败~");
	    	}
	    })
	}
	
	$scope.deletetemplate=function(){
		if($scope.id!=null){
			////删除课程/
			if(confirm("您确定要删出这个模板吗")){
				$http.get("/api/classtemplate/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.id=null;
						$scope.templateBases();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	
	$scope.add=function(){
		$scope.courseClassTemplate=null;
		$scope.id=null;
		document.getElementById('add').style.display="block"; 
		
		
	}
	//////刷新
	$scope.refresh=function(){
		
			$scope.classUrl=null;
			$scope.templateBases();
		
	}
});