app.controller("bannerController", function($scope, $http){
	
	$("#sidebarmenu-system").addClass("active").addClass("expanded").addClass("opened");
    $("#sidebarmenu-system-banner").addClass("active");
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
//上传主展示图片
    $scope.types=0;
    $scope.banner=null;
    $scope.imageurl=null;
$scope.loaddata = function(){
	$scope.pageNo=( $scope.page-1)*$scope.pageSize;
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
	    	$scope.imageurl=data.data;
		})
	};
	
	
	$scope.banneradd=function(){
		console.log("打印URL = "+$scope.imageurl);
		$scope.banner.imageurl=$scope.imageurl;
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
	
	$scope.checkedbanner=function(b){
		$scope.selected = b;
		$scope.bannerId=b.id;
		$scope.banner=b;
		$scope.imageurl=b.imageurl;
	}
	
	$scope.add=function(){
		$scope.imageurl=null;
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
		$scope.banner.imageurl=$scope.imageurl;
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
				    	$scope.page = 1;
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
		$scope.imageurl=null;
		document.getElementById('add').style.display="none"; 
	}
	$scope.refresh=function(){
		
		location.reload();
	}
	
});