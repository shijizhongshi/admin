app.controller("teacherController", function($scope, $http){

	
	/*function getObject(objectId){
		if(document.getElementById && ){
			return document.getElementById(objectId);
		}else if(document.all && document.all(objectId)){
			return document.all(objectId);
		}else if(document.layers && document.layers[objectId]){
			return document.layers[objectId];
		}else{
			return false;
		}
	}*/
	$scope.typeId=1;
	/*$scope.showHide=function(objname,typeId){
		var obj=document.getElementById("#"+objname);
		if(obj.style.display == "none"){
			obj.style.display = "block";
			e.className="minus";
			
			//////////查询列表也
			$scope.typeId=typeId;
			$scope.typeBases();
			
		}else{
			obj.style.display = "none";
			e.className="plus";
		}
	}*/

	
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":1}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass1=data.data;
				
			}
		})
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":2}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass2=data.data;
				
			}
		})
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":3}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass3=data.data;
				
			}
		})
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":4}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass4=data.data;
				
			}
		})
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":5}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass5=data.data;
				
			}
		})
	
	
	
    $scope.subtypeselected = [];
    var updateSelected = function(action, subName) {
	      if(action == 'add' & $scope.subtypeselected.indexOf(subName) == -1) $scope.subtypeselected.push(subName);
	      if(action == 'remove' && $scope.subtypeselected.indexOf(subName) != -1) $scope.subtypeselected.splice($scope.subtypeselected.indexOf(subName), 1);
	    };
    $scope.updateSelection = function($event, subName) {
	      var checkbox = $event.target;
	      var action = (checkbox.checked ? 'add' : 'remove');
	      updateSelected(action, subName);
	    
	    };
    
    $scope.isSelected = function(subName) {
	    return $scope.subtypeselected.indexOf(subName) >= 0;
	};  
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    $scope.teacher=null;
	$scope.teacherBases=function(){
		$http.get("/api/courseteacher/select",{"params": {"page":$scope.current,"teacherName":$scope.teacherName}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.teacherlist=data.data;
				$scope.total=data.count;
			}
		})
	}
	
	$scope.test=function(){
		alert("$scope.");
	}
	$scope.teacherBases();
	
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
	    	$scope.teacher.imgUrl=data.data;
		})
	};
	
	////////////////以上是通过不同的条件查章节的集合的	
	$scope.teacherId=null;
	
	$scope.addteacher=function(){
		$scope.teacher.typename=$scope.subtypeselected;
		if($scope.teacherId==null){
			$http.post("/api/courseteacher/save",$scope.teacher,{'Content-Type': 'application/json;charset=UTF-8'})
		    .success(function(data){
		    	if(data.status=="0"){
		    		alert("保存成功~");
		    		document.getElementById('add').style.display="none"; 
		    		$scope.teacherBases();
		    	}else{
		    		alert(data.message)
		    	}
			})
		}else{
			/////修改
			$scope.teacher.id=$scope.teacherId;
			$http.post("/api/courseteacher/update",$scope.teacher,{'Content-Type': 'application/json;charset=UTF-8'})
		    .success(function(data){
		    	if(data.status=="0"){
		    		alert("更新成功~");
		    		document.getElementById('add').style.display="none"; 
		    		$scope.teacherBases();
		    	}else{
		    		alert(data.message)
		    	}
			})
			
			
		}
		
	};
	///////做选中的时候用
	$scope.checkedTeacher=function(t){
		$scope.selected=t;
		$scope.teacher=t;
		$scope.teacherId=t.id;
	}
	$scope.add=function(){
		$scope.teacher=null;
		$scope.teacherId=null;
		document.getElementById('add').style.display="block"; 
		
		
	}
	////点击修改的按钮先看看是否已经选中了
	$scope.update=function(){
		if($scope.teacherId!=null){
			$scope.subtypeselected=$scope.teacher.typename;
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
		
	}
	
	$scope.deleteTeacher=function(){
		if($scope.teacherId!=null){
			////删除课程/
			if(confirm("您确定要删出这个教师信息吗")){
				$http.get("/api/courseteacher/delete",{"params": {"id":$scope.teacherId}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.teacherBases();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}

	$scope.reset=function(){
		
		location.reload();
	}
});