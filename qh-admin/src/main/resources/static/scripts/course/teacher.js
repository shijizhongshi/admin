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

	$scope.typesList=function(){
		
		$http.get("/api/course/courseTypeList", {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeList=data.data;
				
			}
		})
		
	}
	$scope.typesList();
	
	$scope.courseTypeId="1";
	
	$scope.showCourseTypeSubclassList=function(courseTypeId){
		
		$scope.courseTypeId=courseTypeId;
		$scope.courseTypeSubclassList();
		$scope.ddshow=courseTypeId;
		
	}
	
	
	$scope.courseTypeSubclassList=function(){
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.courseTypeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
				
			}
		})
	}
	
	
	
    $scope.subtypeselected = [];
    $scope.typeselected = [];
    var updateSelected = function(action,typeNames,subName) {
	      if(action == 'add' && $scope.subtypeselected.indexOf(subName) == -1){
	    	  $scope.subtypeselected.push(subName);
	    	  if($scope.typeselected.indexOf(typeNames) == -1){
	    		  $scope.typeselected.push(typeNames);
	    	  }
	    	  
	      }
	      if(action == 'remove' && $scope.subtypeselected.indexOf(subName) != -1){
	    	  $scope.subtypeselected.splice($scope.subtypeselected.indexOf(subName), 1);
	      }
	    };
    $scope.updateSelection = function($event,typeNames, subName) {
	      var checkbox = $event.target;
	      var action = (checkbox.checked ? 'add' : 'remove');
	      updateSelected(action,typeNames,subName);
	    
	    };
    
    $scope.isSelected = function(subName) {
    	return $scope.subtypeselected.indexOf(subName)>=0;
	};  
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    $scope.teacher=null;
    $scope.imgUrl=null;
	$scope.teacherBases=function(){
		$scope.pageNo=( $scope.page-1)*$scope.pageSize;
		$http.get("/api/courseteacher/select",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"teacherName":$scope.teacherName}}, 
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
	    	$scope.imgUrl=data.data;
		})
	};
	
	////////////////以上是通过不同的条件查章节的集合的	
	$scope.teacherId=null;
	
	$scope.addteacher=function(){
		$scope.teacher.typename=$scope.subtypeselected;
		$scope.teacher.names=$scope.typeselected;
		$scope.teacher.imgUrl=$scope.imgUrl;
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
			$scope.teacher.typename=$scope.subtypeselected;
			$scope.teacher.names=$scope.typeselected;
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
		$scope.imgUrl=t.imgUrl;
		$scope.subtypeselected=t.typename;
		$scope.typeselected=t.names;
		$scope.orders = t.orders;
	}
	$scope.add=function(){
		$scope.imgUrl=null;
		$scope.teacher=null;
		$scope.teacherId=null;
		$scope.subtypeselected=[];
		$scope.typeselected=[];
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
			if(confirm("您确定要删除这个教师信息吗")){
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
	
	$scope.cancel=function(){
		
		$scope.teacherId=null;
		$scope.teacher=null;
		$scope.selected = null;
		document.getElementById('add').style.display="none"; 
	}
	//测试  上移下移
	$scope.Knowledgepmove=function(types){

			if(types==1){
				/////上移
				 var index=$scope.teacherlist.indexOf($scope.teacher);
				  var tmp=angular.copy($scope.teacherlist[index-1]);
				  if(index==0){
				  alert('已经是第一个了，不能再向上移动了！');
				  location.reload() ;
				  }
				  $scope.teacherlist[index-1]=$scope.teacherlist[index];
				  $scope.teacherlist[index]=tmp;
				  
				$scope.teacherApi("up");
				return;
			}else if(types==2){
				/////下移
				var index=$scope.teacherlist.indexOf($scope.teacher);
				 
				  if(index==$scope.teacherlist.length-1){
				  alert('已经是最后一个了，不能再向下移动了！');
				  location.reload() ;
				  }
				  var tmp=angular.copy($scope.teacherlist[index+1]);
				 
				  $scope.teacherlist[index+1]=$scope.teacherlist[index];
				  $scope.teacherlist[index]=tmp;
				  $scope.teacherApi("down");
				  return;
			}else {
				alert("请选中信息~");
			}
		
	}
	//上移下移
	$scope.teacherApi=function(operateType){
		$http.get("/api/courseteacher/teacherApi",{"params": {"id":$scope.teacherId,
			"orders":$scope.orders,"operateType":operateType}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=='0'){
				$scope.orders = data.data;
				$scope.teacherBases();
			}else{
				if(data.message!=null){
					alert(data.message);
				}else{
				alert("移动失败~");
				}
			}
		})
	}
	
});