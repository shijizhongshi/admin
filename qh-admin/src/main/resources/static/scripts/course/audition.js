app.controller("CourseNofreeController",function($scope,$http){
	
	
	
   $scope.total = 0;
   //当前的页数
   $scope.page = 1;
   //一页显示多少条
   $scope.pageSize = 20;
   
   $scope.id=null;
   
   /////查询
   $scope.auditionBases=function(){
	   
		$http.get("/api/coursenofree/select",{"params": {"courseTypeName":$scope.courseTypeName,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"page":$scope.page}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.auditionlist=data.data;
			}
		})
	};
	
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
	$scope.auditionBases();

	$scope.auditionSub=function(typename,sub){
		////////查课程的集合
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.auditionBases();
	
	$scope.imgUrl=null;
	
	/////分页
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
	    	$scope.imgUrl=data.data;
	    	
		})
	};
	
	
	////保存
	$scope.addAudition=function(){
		$http.post("/api/coursenofree/insert",$scope.courseNofree,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		
	    			alert("保存成功~");
	    			document.getElementById('add').style.display="none"; 
		    		$scope.auditionBases();
	    	}else{
	    		alert("保存失败~");
	    	}
	    	
	    })
	};
	
	$scope.add=function(){
		$scope.courseNofree=null;
		$scope.id=null;
		document.getElementById('add').style.display="block"; 
		
		
	};
	///////做选中的时候用
	$scope.checkedAudition=function(t){
		$scope.selected=a;
		$scope.courseClassTemplate=a;
		$scope.id=a.id;
	};
	////点击修改的按钮先看看是否已经选中了
	$scope.update=function(){
		if($scope.id!=null){
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
		
	};
////修改
	$scope.updateAudition=function(){
		$http.post("/api/coursenofree/update",$scope.courseNofree,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		
	    			alert("修改成功~");
	    			document.getElementById('add').style.display="none"; 
	    			$scope.id=null;
		    		$scope.auditionBases();
	    	}else{
	    		alert("修改失败~");
	    	}
	    })
	};
	
	$scope.deleteAudition=function(){
		if($scope.id!=null){
			////删除课程/
			if(confirm("您确定要删出这个模板吗")){
				$http.get("/api/coursenofree/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.id=null;
						$scope.auditionBases();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	}
});
app.controller("CourseController", function($scope, $http){
	
	$scope.active=1;
	$scope.typeId=1;
	$scope.typeList=function(typeId){
			$scope.active=typeId;
			$scope.typeId=typeId;
			$scope.typeBases();
	};
	$scope.typeBases=function(){
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
			}
		})
	};
	
	   $scope.total = 0;
	   //当前的页数
	   $scope.page = 1;
	   //一页显示多少条
	   $scope.pageSize = 20;
	
	
	$scope.courseBases=function(){
		$scope.pageNo=( $scope.current-1)*$scope.pageSize;
		$http.get("/api/course/courseList",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
			"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.courselist=data.data;
				}
		})
};

	
});
