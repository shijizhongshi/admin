app.controller("CourseNofreeController",function($scope,$http,$sce){
	
	
	$scope.total = 0;
   //当前的页数
   $scope.page = 1;
   //一页显示多少条
   $scope.pageSize = 20;
   
   $scope.total1 = 0;
   //当前的页数
   $scope.page1 = 1;
   //一页显示多少条
   $scope.pageSize1 = 20;
   
   $scope.id=null;
   $scope.video=null;
   $scope.teachers=null;
   $scope.courseName=null;
   $scope.teacher=null;
   $scope.teacherName=null;
   
   $scope.typeBases=function(){
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
				$scope.typeSelected=$scope.courseTypeSubclass[0].courseTypeSubclassName;
				$scope.courseTypeSubclassName=$scope.courseTypeSubclass[0].courseTypeSubclassName;
				$scope.auditionBases();
				
			}
		})
	};
	$scope.active=1;
	$scope.typeId=1;
	$scope.typesList=function(){
		
		$http.get("/api/course/courseTypeList", {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeList=data.data;
				
			}
		})
		
	}
	$scope.typesList();
	$scope.typeBases();
	$scope.typeList=function(typename,typeId){
			$scope.active=typeId;
			$scope.typeId=typeId;
			$scope.typeBases();
			$scope.courseTypeName=typename;
	};
   
   /////查询
   $scope.auditionBases=function(){
		$http.get("/api/coursenofree/select",{"params": {"courseTypeName":$scope.courseTypeName,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,
			"page":$scope.page,"teachers":$scope.teachers,"courseName":$scope.courseName}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.auditionlist=data.data;
				$scope.total=data.count;
				angular.forEach($scope.auditionlist, function(audition){  
					
					if(audition.isremmend==0){
						
						audition.remmend="不推荐";
					}
					else if(audition.isremmend==1){
						audition.remmend="推荐";
					}
					
				})
			}
			else{
				$scope.auditionlist=null;
			}
		})
	};
	
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
	$scope.auditionBases();

	$scope.auditionSub=function(typename,sub,$event){
		////////查课程的集合
		$event.stopPropagation();
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		$scope.auditionBases();
		$scope.typeSelected=sub.courseTypeSubclassName;
	
}
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
	
	
/*	$scope.uploadmainimage1 = function(file){
		if(!file.files || file.files.length < 1) return;
		var formData = new FormData();
		formData.append('Filedata', $('#file')[0].files[0]);
		formData.append("writetoken",$scope.video.writetoken);
		formData.append("JSONRPC","{'title': '标题', 'tag':'标签','desc':'描述'}");
		$.ajax({
		    url: 'https://v.polyv.net/uc/services/rest?method=uploadfile',
		    type: 'POST',
		    data: formData,
		    cache: false,
		    processData: false,
		    contentType: false
		}).success(function(res) {
			
			if(res.error=="0"){
				alert("上传成功~");
				console.log(res);
				$scope.courseNofree.videoId=res.data[0].vid;
				$scope.courseNofree.videoUrl=res.data[0].mp4;
				document.getElementById('polyved').style.display="block";
				$scope.polyv($scope.courseNofree.videoId);
				
			}else{
				alert(res.error);		
			}
		}).fail(function(res) {
			
		});
	};*/
	/*$scope.polyvnew=function(videoId){
		document.getElementById('polyved').style.display="block"; 
		$scope.polyv(videoId);
	};
	*//*$scope.uploadmainimage1 = function(file){
		if(!file.files || file.files.length < 1) return;
	    var formData={
	    		"title" : "测试11",
				"filename" : file.files[0],
				"filesize" : file.files[0].size,
				"description":"很好很好",
				"userid":"91DD94C27B488135",
				"tag":"教育"
				
	    }
		formData.append('filename', file.files[0]);
		formData.append("userid","91DD94C27B488135");
		formData.append("title","测试11");
		formData.append("tag","教育");
		formData.append("description","很好很好");
		formData.append("filesize",file.files[0].size);
		
		
		$.ajax({
		    url: 'http://spark.bokecc.com/api/video/create/v2',
		    type: 'GET',
			data : {
	    		"title" : "测试11",
				"filename" : file.files[0],
				"filesize" : file.files[0].size,
				"description":"很好很好",
				"userid":"91DD94C27B488135",
				"tag":"教育"
				
	    },
			cache : false,
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		}).success(function(res) {
			
			console.log(res);
			
		}).fail(function(res) {
			
		});
		
		
	};*/
	
	////保存
	$scope.addAudition=function(){
		$scope.courseNofree.imgUrl=$scope.imgUrl;
		$scope.courseNofree.teachers=$scope.teacher;
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
		$scope.imgUrl=null;
		$scope.courseNofree=null;
		$scope.id=null;
		//$scope.polyv();
		document.getElementById('add').style.display="block"; 
	};
	
	///////做选中的时候用
	$scope.checkedAudition=function(a){
		$scope.imgUrl=a.imgUrl;
		$scope.selected=a;
		$scope.courseNofree=a;
		$scope.id=a.id;
		$scope.videoId=a.videoId;
		
		$scope.ccvideo=true;
		$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+$scope.videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
		$scope.trustSrc = function() {
	         return $sce.trustAsResourceUrl($scope.scriptss1);
	     }
		$scope.scriptss2="cciframe_"+$scope.videoId;
	};
	
	/*$scope.polyv=function(videoId){
		$http.get("/api/coursenofree/polyv",{"params": {"vid":videoId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.video=data.data;
				if($scope.video.videoId!=null){
					var player = polyvObject('#polyved').videoPlayer({
					    'width':'400',
					    'height':'400',
					    'vid':$scope.video.videoId,
					    'ts':$scope.video.ts,
					    'sign':$scope.video.sign
					});
				}else{
					document.getElementById('polyved').style.display="none"; 
				}
				
			}
		})
	}
	
	$scope.polyv();*/
	$scope.ccvideo=false;
	////点击修改的按钮先看看是否已经选中了
	$scope.update=function(){
		if($scope.id!=null){
			//document.getElementById('polyved').style.display="block";
			document.getElementById('add').style.display="block"; 
			//$scope.polyv($scope.videoId);
		}else{
			alert("请选中信息~");
		}
		
	};
	
	
	
	$scope.ccnew=function(videoId){
		$scope.ccvideo=true;
		/*document.getElementById('polyved').style.display="block"; */
		$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
		$scope.trustSrc = function() {
	         return $sce.trustAsResourceUrl($scope.scriptss1);
	     }
		$scope.scriptss2="cciframe_"+videoId;
	};
////修改
	$scope.updateAudition=function(){
		$scope.courseNofree.imgUrl=$scope.imgUrl;
		$scope.courseNofree.videoUrl=$scope.videoUrl;
		$scope.courseNofree.teachers=$scope.teacher;
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
			if(confirm("您确定要删出这个试听课程吗")){
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
	
	//////教师的集合
	$scope.teacherList=function(){
		$http.get("/api/courseteacher/select",{"params": {"courseTypeName":$scope.courseTypeName,"teacherName":$scope.teacherName,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"page":$scope.page1}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=='0'){
				$scope.teacherlist=data.data;
				$scope.total1=data.count;
			}
		})
			
		}
	
	///////点击搜索老师的框
	$scope.showteacher=function(){
		document.getElementById('revise').style.display="block"; 
		$scope.teacherList();
	}
	
	///////做选中的时候用
	$scope.courseTeacher=null;
	
	$scope.checkteacher=function(t){
		if($scope.selected!=t){
		$scope.teacher=t.name;
		$scope.courseTeacher=t;
		$scope.selected=t
		}else{
			$scope.teacher=null;
			$scope.courseTeacher=null;
			$scope.selected=null;
			
		}
	};
	
	/////教师的提交按钮
	$scope.addteacher=function(){
		if($scope.courseTeacher!=null){
			document.getElementById('revise').style.display="none"; 
		}
		else{
			alert("请选择教师");
		}
	};
	
	
	$scope.refresh=function(){
		
			location.reload();
		
	};
	
	$scope.reset=function(){
		$scope.teacher=null;
		$scope.courseTeacher=null;
		$scope.selected=null;
		$scope.courseNofree=null;
		$scope.id=null;
		$scope.videoUrl=null;
		document.getElementById('revise').style.display="none"; 
		document.getElementById('add').style.display="none"; 
	
};
	$scope.auditionMoveApi=function(operateType){
	$http.get("/api/course/subclass/sectionOrders",{"params": {"id":$scope.id,
		"orders":$scope.courseNofree.orders,"operateType":operateType,"tables":"audition","comment":$scope.courseTypeSubclassName}}, 
		{'Content-Type': 'application/json;charset=UTF-8'})
	.success(function(data){
		if(data.status=='0'){
			$scope.course.orders = data.data;
		}else{
			alert("移动失败~");
		}
	})
}

	$scope.auditionmove=function(types){
	if($scope.id!=null){
		if(types==1){
			/////上移
			 var index=$scope.auditionlist.indexOf($scope.courseNofree);
			  var tmp=angular.copy($scope.auditionlist[index-1]);
			  if(index==0){
			  alert('已经是第一个了，不能再向上移动了！');
			  location.reload() ;
			  }
			  $scope.auditionlist[index-1]=$scope.auditionlist[index];
			  $scope.auditionlist[index]=tmp;
			  
			$scope.auditionMoveApi("up");
		}
		if(types==2){
			/////下移
			var index=$scope.auditionlist.indexOf($scope.courseNofree);
			 
			  if(index==$scope.auditionlist.length-1){
			  alert('已经是最后一个了，不能再向下移动了！');
			  location.reload() ;
			  }
			  var tmp=angular.copy($scope.auditionlist[index+1]);
			 
			  $scope.auditionlist[index+1]=$scope.auditionlist[index];
			  $scope.auditionlist[index]=tmp;
			  $scope.auditionMoveApi("down");
		}
		
	}else{
		alert("请选中信息~");
	}
	
}

});
	
	

