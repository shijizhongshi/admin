app.controller("ChapterController", function($scope, $http){
	
	
	if($("#typeId").val()!=''){
		////说明是在课程点进章来的
		$scope.active=$("#typeId").val();
		$scope.typeId=$("#typeId").val();
		$scope.courseId=$("#courseId").val();
		$scope.courseTypeName=$("#courseTypeName").val();
		$scope.courseTypeSubclassName=$("#courseTypeSubclassName").val();
		$scope.typeSelected=$scope.courseTypeSubclassName;
		
	}else{
		$scope.typeSelected="临床(执业)助理医师";
		$scope.courseTypeName="医师资格";
		$scope.courseTypeSubclassName="临床(执业)助理医师";
		$scope.active=1;
		$scope.typeId=1;
	}
	
	$scope.typeList=function(typename,typeId){
			
			$scope.active=typeId;
			$scope.typeId=typeId;
			$scope.typeBases();
			$scope.courseTypeName=typename;
	};
	$scope.typeBases=function(){
		
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
				$scope.typeSelected=$scope.courseTypeSubclass[0].courseTypeSubclassName;
				$scope.courseTypeSubclassName=$scope.courseTypeSubclass[0].courseTypeSubclassName;
				$scope.chapterBases();
			}
		})
		
	};
	$scope.typeBases2=function(){
		
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
				$scope.chapterBases();
			}
		})
		
	};
	$scope.typesList=function(){
		
		$http.get("/api/course/courseTypeList", {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeList=data.data;
				
			}
		})
		
	}
	$scope.typesList();
	if($("#typeId").val()!=''){
		////说明是在课程点进章来的
		
		$scope.typeBases2();//////保证已经来有默认的参数
	}else{
		
		$scope.typeBases();//////保证已经来有默认的参数
	}
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
	
	$scope.chapterBases=function(){
		$scope.pageNo=($scope.page-1)*$scope.pageSize;
		$http.get("/api/course/subclass/courseChapterList",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,
			"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName,"courseId":$scope.courseId
			,"courseChapterName":$scope.courseChapterName}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.chapterlist=data.data;
				$scope.total=data.count;
			}
		})
	}
	
	
	$scope.chapterBases();
	
	/////点击专业的事件
	$scope.typeSub=function(typename,sub,$event){
		////////查章节的集合
		$scope.page = 1;
		$event.stopPropagation();
		$scope.courseId=null;
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.chapterBases();
		$scope.typeSelected=$scope.courseTypeSubclassName;
		
	}
	
	$scope.saveChapter=function(){
		$scope.chapter=null;
		$scope.chapterId=null;
		document.getElementById('add').style.display="block"; 
		$http.get("/api/course/courseList",{"params": {"pageNo":0,"pageSize":0,
			"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courselist=data.data;
			}
		})
	}
	////////////////以上是通过不同的条件查章节的集合的	
	$scope.chapter=null;
	$scope.chapterId=null;
	
	$scope.addChapter=function(){
		$scope.chapter.id=$scope.chapterId;
		$scope.chapter.courseTypeName=$scope.courseTypeName;
	    $scope.chapter.courseTypeSubclassName=$scope.courseTypeSubclassName;
		$http.post("/api/course/subclass/courseChapter/saveUpdate",$scope.chapter,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		if($scope.chapterId!=null){
	    			alert("更新成功~");
	    		}else{
	    			alert("保存成功~");
	    		}
	    		
	    		document.getElementById('add').style.display="none"; 
	    		$scope.chapterBases();
	    	}else{
	    		alert(data.message)
	    	}
		})
	};
	///////做选中的时候用
	$scope.checkedChapter=function(c){
		$scope.selected=c;
		$scope.chapter=c;
		$scope.chapterId=c.id;
	}
	////点击修改的按钮先看看是否已经选中了
	$scope.updateChapter=function(){
		if($scope.chapterId!=null){
			$http.get("/api/course/courseList",{"params": {"pageNo":0,"pageSize":0,
				"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}}, {'Content-Type': 'application/json;charset=UTF-8'})
			.success(function(data){
				if(data.status=="0"){
					$scope.courselist=data.data;
				}
			})
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
		
	}
	
	$scope.deleteChapter=function(){
		if($scope.chapterId!=null){
			////删除课程/
			if(confirm("您确定要删出这个课程吗")){
				$http.get("/api/course/subclass/courseChapter/delete",{"params": {"chapterId":$scope.chapterId}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.chapterBases();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	
	$scope.section=function(){
		if($scope.chapterId!=null){
			location.href="/web/course/section?chapterId="+$scope.chapterId+"&chapterName="+$scope.chapter.courseChapterName;
		}else{
			alert("请选中信息~");
		}
	}
	$scope.reset=function(){
		
		location.reload();
	}

	
	$scope.cancel=function(){
		
		$scope.chapterId=null;
		$scope.chapter=null;
		$scope.selected = null;
		document.getElementById('add').style.display="none"; 
	}
	
	$scope.chapterMoveApi=function(operateType){
		$http.get("/api/course/subclass/sectionOrders",{"params": {"id":$scope.chapterId,
			"orders":$scope.chapter.orders,"operateType":operateType,"tables":"cc","comment":$scope.courseTypeSubclassName}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=='0'){
				$scope.chapter.orders = data.data;
			}else{
				alert("移动失败~");
			}
		})
	}
	
	$scope.chaptermove=function(types){
		if($scope.chapterId!=null){
			if(types==1){
				/////上移
				 var index=$scope.chapterlist.indexOf($scope.chapter);
				  var tmp=angular.copy($scope.chapterlist[index-1]);
				  if(index==0){
				  alert('已经是第一个了，不能再向上移动了！');
				  location.reload() ;
				  }
				  $scope.chapterlist[index-1]=$scope.chapterlist[index];
				  $scope.chapterlist[index]=tmp;
				  
				$scope.chapterMoveApi("up");
			}
			if(types==2){
				/////下移
				var index=$scope.chapterlist.indexOf($scope.chapter);
				 
				  if(index==$scope.chapterlist.length-1){
				  alert('已经是最后一个了，不能再向下移动了！');
				  location.reload() ;
				  }
				  var tmp=angular.copy($scope.chapterlist[index+1]);
				 
				  $scope.chapterlist[index+1]=$scope.chapterlist[index];
				  $scope.chapterlist[index]=tmp;
				  $scope.chapterMoveApi("down");
			}
			
		}else{
			alert("请选中信息~");
		}
		
	}
	
});