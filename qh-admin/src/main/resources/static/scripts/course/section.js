app.controller("sectionController", function($scope, $http,$sce){

	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    $scope.section=null;
    $scope.sectionlist=[];
	$scope.sectionBases=function(){
		$http.get("/api/course/subclass/courseSectionList",{"params": {"page":$scope.current,"courseChapterId":$("#chapterId").val()}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.sectionlist=data.data;
				$scope.total=data.count;
				/*angular.forEach($scope.sectionlist, function(section){  
					
				section.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+section.videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
				$scope.trustSrc = function() {
			         return $sce.trustAsResourceUrl(section.scriptss1);
			     }
				section.scriptss2="cciframe_"+section.videoId;
				})*/
			}
		})
	}
	
	
	$scope.sectionBases();
	//$scope.video=null;
	/*$scope.uploadmainimage = function(file){
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
				$scope.section.videoId=res.data[0].vid;
				$scope.section.videoUrl=res.data[0].mp4;
				document.getElementById('polyved').style.display="block"; 
				$scope.polyv($scope.section.videoId);
			}else{
				alert(res.error);		
			}
		}).fail(function(res) {
			
		});
	};*/
	/*
	$scope.polyv=function(videoId){
		$http.get("/api/coursenofree/polyv",{"params": {"vid":videoId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.video=data.data;
				if($scope.video.videoId!=null){
					var player = polyvObject('#polyved').videoPlayer({
					    'width':'90%',
					    'height':'200',
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
	
	$scope.polyvnew=function(videoId){
		document.getElementById('polyved').style.display="block"; 
		$scope.polyv(videoId);
	};
	
	$scope.polyv();*/
	
	////////////////以上是通过不同的条件查章节的集合的	
	$scope.ccvideo=false;
	$scope.ccnew=function(videoId){
		$scope.ccvideo=true;
		/*document.getElementById('polyved').style.display="block"; */
		$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
		$scope.trustSrc = function() {
	         return $sce.trustAsResourceUrl($scope.scriptss1);
	     }
		$scope.scriptss2="cciframe_"+videoId;
	};
	$scope.sectionId=null;
	
	$scope.addSection=function(){
		$scope.section.id=$scope.sectionId;
		$scope.section.courseChapterId=$("#chapterId").val();
		$http.post("/api/course/subclass/courseSection/saveUpdate",$scope.section,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		if($scope.sectionId!=null){
	    			alert("更新成功~");
	    		}else{
	    			alert("保存成功~");
	    		}
	    		document.getElementById('add').style.display="none"; 
	    		$scope.sectionBases();
	    	}else{
	    		alert(data.message)
	    	}
		})
	};
	///////做选中的时候用
	$scope.checkedSection=function(c){
		$scope.selected=c;
		$scope.section=c;
		$scope.sectionId=c.id;
		$scope.videoId=c.videoId;
		$scope.ccvideo=true;
		$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+c.videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
		$scope.trustSrc = function() {
	         return $sce.trustAsResourceUrl($scope.scriptss1);
	     }
		$scope.scriptss2="cciframe_"+c.videoId;
	}
	$scope.add=function(){
		$scope.section=null;
		$scope.sectionId=null;
		//$scope.polyv();
		document.getElementById('add').style.display="block"; 
		
		
	}
	////点击修改的按钮先看看是否已经选中了
	$scope.updateSection=function(){
		if($scope.sectionId!=null){
			/*document.getElementById('polyved').style.display="block"; 
			$scope.polyv($scope.videoId);*/
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
		
	}
	
	$scope.uploadS=function(){
		document.getElementById('revise').style.display="block"; 
	}
	$scope.uploadSection=function(){
		var fd = new FormData();
	    fd.append("file", $("#file")[0].files[0]);
	    fd.append("courseChapterId",$("#chapterId").val());
	    
	    
	    $http.post("/api/course/subclass/upload/section",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	alert("上传成功~");
	    	document.getElementById('revise').style.display="none"; 
	    	$scope.sectionBases();
		})
	}
	
	$scope.deleteSection=function(){
		if($scope.sectionId!=null){
			////删除课程/
			if(confirm("您确定要删出这个课程吗")){
				$http.get("/api/course/subclass/section/delete",{"params": {"sectionId":$scope.sectionId}}, {'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=='0'){
						alert("删除成功~");
						$scope.sectionBases();
					}else{
						alert("删除失败~");
					}
				})
			}
			
		}else{
			alert("请选中信息~");
		}
	}
	$scope.sectionMoveApi=function(operateType){
		$http.get("/api/course/subclass/sectionOrders",{"params": {"id":$scope.sectionId,
			"orders":$scope.section.orders,"operateType":operateType,"tables":"cs"}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=='0'){
				$scope.section.orders = data.data;
			}else{
				alert("移动失败~");
			}
		})
	}
	
	$scope.sectionmove=function(types){
		if($scope.sectionId!=null){
			if(types==1){
				/////上移
				 var index=$scope.sectionlist.indexOf($scope.section);
				  var tmp=angular.copy($scope.sectionlist[index-1]);
				  if(index==0){
				  alert('已经是第一个了，不能再向上移动了！');
				  return ;
				  }
				  $scope.sectionlist[index-1]=$scope.sectionlist[index];
				  $scope.sectionlist[index]=tmp;
				  
				$scope.sectionMoveApi("up");
			}
			if(types==2){
				/////下移
				var index=$scope.sectionlist.indexOf($scope.section);
				 
				  if(index==$scope.sectionlist.length-1){
				  alert('已经是最后一个了，不能再向下移动了！');
				  return ;
				  }
				  var tmp=angular.copy($scope.sectionlist[index+1]);
				 
				  $scope.sectionlist[index+1]=$scope.sectionlist[index];
				  $scope.sectionlist[index]=tmp;
				  $scope.sectionMoveApi("down");
			}
			
		}else{
			alert("请选中信息~");
		}
		
	}
	
	$scope.reset1=function(){
		
		document.getElementById('add').style.display="none"; 
	}
	$scope.reset=function(){
		
		document.getElementById('revise').style.display="none"; 
	}
});