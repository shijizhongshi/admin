app.controller("bannerController", function($scope, $http){
	
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;
//上传主展示图片
    $scope.types=0;
    $scope.banner=null;
$scope.loaddata = function(){
	$scope.pageNo=( $scope.current-1)*$scope.pageSize;
	$http.get("/api/banner/selectlist",{"params": {"type":$scope.types,"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(result){
    	if(result.status=="0"){
    		$scope.bannerlist=result.data;
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
	    	$scope.banner.imageurl=data.data;
		})
	};
	
	
	$scope.banneradd=function(){
		$http.post("/api/banner/saveBanner",$scope.banner,{'Content-Type': 'application/json;charset=UTF-8'})
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
	$scope.bannerId=null;
	$scope.focus = false;
	$scope.checkedbanner=function(b){
		$scope.selected = b;
		$scope.bannerId=b.id;
		$scope.banner=b;
		
	}
	
	$scope.add=function(){
		$scope.bannerId=null;
		$scope.banner=null;
		$scope.selected = null;
		document.getElementById('add').style.display="block"; 
		 
	}
	$scope.updatebanner=function(){
		 if($scope.bannerId==null){
			 alert("请选择信息~")
		 }else{
			 document.getElementById('add').style.display="block"; 
		 }
	}
	
	$scope.bannerupdate=function(){
		$http.post("/api/banner/updateBanner",$scope.banner,{'Content-Type': 'application/json;charset=UTF-8'})
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
	$scope.deletebanner=function(){
		
		if($scope.bannerId==null){
			 alert("请选择信息~")
		 }else{
			 if(confirm("你确定要删除这条记录吗?")){
				 $http.get("/api/banner/deleteBanner",{"params": {"id":$scope.bannerId}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
	
	$scope.reset=function(){
		$scope.bannerId=null;
		$scope.banner=null;
		$scope.selected = null;
		document.getElementById('add').style.display="none"; 
	}
	$scope.loaddata=function(){
		
		location.reload();
	}

});