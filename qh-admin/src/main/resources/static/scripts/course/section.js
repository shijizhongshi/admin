app.controller("sectionController", function($scope, $http){

	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
    $scope.section=null;
	$scope.sectionBases=function(){
		$http.get("/api/course/subclass/courseSectionList",{"params": {"page":$scope.current,"courseChapterId":$("#chapterId").val()}}, 
			{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.sectionlist=data.data;
				$scope.total=data.count;
			}
		})
	}
	
	
	$scope.sectionBases();
	
	$scope.uploadmainimage = function(file){
		if(!file.files || file.files.length < 1) return;
		var formData = new FormData();
		formData.append('Filedata', $('#file')[0].files[0]);
		formData.append("writetoken","a1df864b-405e-4782-9494-733e9b51c5d5");
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
				$scope.videoId = res.data[0].vid;
				$scope.videoUrl = res.data[0].mp4_2;
				
			}else{
				alert(res.error);		
			}
		}).fail(function(res) {
			
		});
	};
	
	////////////////以上是通过不同的条件查章节的集合的	
	$scope.sectionId=null;
	
	$scope.addSection=function(){
		$scope.section.id=$scope.sectionId;
		$scope.section.videoId=$scope.videoId;
		$scope.section.videoUrl=$scope.videoUrl;
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
	$scope.checkedChapter=function(c){
		$scope.selected=c;
		$scope.section=c;
		$scope.sectionId=c.id;
	}
	$scope.add=function(){
		$scope.section=null;
		$scope.sectionId=null;
		document.getElementById('add').style.display="block"; 
		
		
	}
	////点击修改的按钮先看看是否已经选中了
	$scope.updateSection=function(){
		if($scope.sectionId!=null){
			document.getElementById('add').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
		
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

});