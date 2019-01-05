app.controller("CourseClassTemplateController",function($scope,$http){
	
	
	
   $scope.total = 0;
   //当前的页数
   $scope.page = 1;
   //一页显示多少条
   $scope.pageSize = 20;
   
   $scope.templateName=null;
   
   $scope.courseBases=function(){
	   
		$http.get("/api/classtemplate/select",{"params": {"templateName":$scope.templateName,"page":$scope.page}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.templatelist=data.data;
			}
		})
	}
	$scope.courseBases();
	
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
	
	$scope.addTemplate=function(){
		$http.post("/api/classtemplate/save",$scope.courseClassTemplate,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		
	    			alert("保存成功~");
	    		
	    	}else{
	    		alert("保存失败~");
	    	}
	    })
	}
});