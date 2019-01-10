app.controller("CourseNofreeController",function($scope,$http,$sce){
	
	
	$scope.total = 0;
   //当前的页数
   $scope.page = 1;
   //一页显示多少条
   $scope.pageSize = 20;
   
   $scope.id=null;
   
   $scope.typeBases=function(){
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
			}
		})
	};
	$scope.active=1;
	$scope.typeId=1;
	$scope.typeBases();
	$scope.typeList=function(typeId){
			$scope.active=typeId;
			$scope.typeId=typeId;
			$scope.typeBases();
	};
   
   /////查询
   $scope.auditionBases=function(){
	   
		$http.get("/api/coursenofree/select",{"params": {"courseTypeName":$scope.courseTypeName,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"page":$scope.page}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.auditionlist=data.data;
			}
			else{
				$scope.auditionlist=null;
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
	
}
	$scope.imgUrl=null;
	$scope.aliyunId=null;
	
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
	
	
	$scope.uploadmainimage1 = function(file){
		
		
		if(!file.files || file.files.length < 1) return;

		
	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/single",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	        
	    })
	    .success(function(data){
	    	
	    	$scope.aliyunId=data.data;
	    	
		})
	};
$scope.news=$sce.trustAsResourceUrl;
$scope.teachersName=null;
	
	////保存
	$scope.addAudition=function(){
		$scope.courseNofree.teachers=$scope.teachersName;
		$scope.courseNofree.aliyunId=$scope.aliyunId;
		$scope.courseNofree.courseTypeName=$scope.courseTypeName;
		$scope.courseNofree.courseTypeSubclassName=$scope.courseTypeSubclassName;
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
	$scope.checkedAudition=function(a){
		
		$scope.selected=a;
		$scope.courseNofree=a;
		$scope.teachers=a.teachers;
		$scope.teachersName=a.teachers;
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
		$scope.courseNofree.teachers=$scope.teachersName;
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
	
	
	$scope.teacherList=function(){
		$http.get("/api/courseteacher/select",{"params": {"courseTypeName":$scope.courseTypeName,"teacherName":$scope.teacherName,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"page":$scope.page}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=='0'){
				$scope.teacherlist=data.data;
				$scope.courseTeacher=$scope.teacherlist;
			}
		})
			
		}
	
	$scope.showteacher=function(courseTypeSubclassName){
		
		document.getElementById('revise').style.display="block"; 
		
		$scope.teacherList();
		
	}
	
///////做选中的时候用
	$scope.checkteacher=function(t){
		
		$scope.selected=t;
		$scope.courseTeacher=t;
		$scope.courseTeacher.id=t.id;
		$scope.courseTeacher.name=t.name;
		
		$scope.teachersName=t.name;
		
	};
	$scope.teachersName=null;
	
	$scope.addteacher=function(){
		
	
		if($scope.courseTeacher.id!=null){
			document.getElementById('revise').style.display="none"; 
			
		}
		else{
			
			alert("请选择教师");
		}
	};
});
	
	

