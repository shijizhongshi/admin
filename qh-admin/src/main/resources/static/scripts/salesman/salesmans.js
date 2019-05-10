app.controller("salesmansController", function($scope, $http){
	
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;
//上传主展示图片
    $scope.types=0;
    $scope.salesman=null;
    $scope.imgUrl=null;
$scope.SalesmanList = function(){
	$scope.pageNo=( $scope.page-1)*$scope.pageSize;
	$http.get("/api/salesman/list",{"params": {"name":$scope.name,"mobile":$scope.mobile,"address":$scope.address,
		"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(date){
    	if(date.status=="0"){
    		$scope.salesmanlist=date.data;
    		$scope.total=date.count;
    		
    	}
    	
	})
};

$scope.SalesmanList();

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
	
	
	$scope.salesmanAdd=function(){
		$scope.salesman.imgUrl=$scope.imgUrl;
		$http.post("/api/salesman/insert",$scope.salesman,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("保存成功~");
	    		document.getElementById('add').style.display="none"; 
	    		$scope.SalesmanList();
	    	}else{
	    		alert(data.message)
	    	}
		})
	};
	$scope.id=null;
	$scope.checkedsalesman=function(sl){
		$scope.selected=sl;
		$scope.id=sl.id;
		$scope.salesman=sl;
		$scope.imgUrl=sl.imgUrl;
	}
	
	$scope.add=function(){
		$scope.id=null;
		$scope.salesman=null;
		$scope.imgUrl=null;
		$scope.html="添加";
		document.getElementById('add').style.display="block"; 
		 
	}
	$scope.update=function(){
		 if($scope.id==null){
			 alert("请选择信息~")
		 }else{
			 $scope.html="修改";
			 document.getElementById('add').style.display="block"; 
		 }
	}
	
	$scope.salesmanupdate=function(){

		$scope.salesman.imgUrl = $scope.imgUrl;
		$http.post("/api/salesman/update",$scope.salesman,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("更新成功~");
	    		document.getElementById('add').style.display="none"; 
	    		$scope.SalesmanList();
	    	}else{
	    		alert(data.message)
	    	}
		})
	}
	$scope.deletesalesman=function(){
		
		if($scope.id==null){
			 alert("请选择信息~")
		 }else{
			 if(confirm("你确定要删除这条记录吗?")){
				 $http.get("/api/salesman/delete",{"params": {"id":$scope.id}}, {'Content-Type': 'application/json;charset=UTF-8'})
				    .success(function(result){
				    	if(result.status=="0"){
				    	alert("删除成功");
				    	$scope.page = 1;
				    	$scope.SalesmanList();
				    		
				    	}else{
				    		alert(result.message);
				    	}
				    	
					})
				}
			  
		 }
	}
	
	$scope.reset=function(){
		
		location.reload();
	}

	$scope.cancel=function(){
		
		$scope.id=null;
		$scope.salesman=null;
		$scope.selected = null;
		document.getElementById('add').style.display="none"; 
	}
	
	$scope.toClient=function(salesmanId){
		
		location.href="/web/salesman/salesmanClient?salesmanId="+salesmanId;
	}
})