app.controller("newController", function($scope, $http){
	var ue = UE.getEditor('editor');
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
//上传主展示图片
    $scope.types=0;
    $scope.news=null;
    $scope.title=null;
$scope.loaddata = function(){
	$scope.pageNo=($scope.current-1)*$scope.pageSize;
	$http.get("/api/news/newLists",{"params": {"title":$scope.title,"page":$scope.current}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(result){
    	if(result.status=="0"){
    		$scope.newlist=result.data;
    		$scope.total=result.count;
    		
    	}else{
    		alert(result.message);
    	}
    	
	})
};

$scope.loaddata();


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
	    	$scope.news.imgUrl=data.data;
		})
	};
	
	
	$scope.newadd=function(){
		$scope.news.content = ue.getContent();
		$http.post("/api/news/save",$scope.news,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("保存成功~");
	    		document.getElementById('add').style.display="none"; 
	    		$scope.loaddata();
	    	}else{
	    		alert(data.message)
	    	}
		})
	};
	$scope.newsId=null;
	$scope.checkednews=function(news){
		$scope.selected=news;
		$scope.newsId=news.id;
		$scope.news=news;
		
	}
	
	$scope.add=function(){
		$scope.newsId=null;
		$scope.news=null;
		document.getElementById('add').style.display="block"; 
		 
	}
	$scope.update=function(){
		 if($scope.newsId==null){
			 alert("请选择信息~")
		 }else{
			 ue.setContent($scope.news.content);
			 document.getElementById('add').style.display="block"; 
		 }
	}
	
	$scope.newsupdate=function(){
		$scope.news.content = ue.getContent();
		$http.post("/api/news/update",$scope.news,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("更新成功~");
	    		document.getElementById('add').style.display="none"; 
	    		$scope.loaddata();
	    	}else{
	    		alert(data.message)
	    	}
		})
	}
	$scope.deletenews=function(){
		
		if($scope.newsId==null){
			 alert("请选择信息~")
		 }else{
			 if(confirm("你确定要删除这条记录吗?")){
				 $http.get("/api/news/delete",{"params": {"id":$scope.newsId}}, {'Content-Type': 'application/json;charset=UTF-8'})
				    .success(function(result){
				    	if(result.status=="0"){
				    	alert("删除成功");
				    	$scope.loaddata();
				    		
				    	}else{
				    		alert(result.message);
				    	}
				    	
					})
				}
			  
		 }
	}
	

});