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
    $scope.qrcode=null;
$scope.SalesmanList = function(){
	$scope.pageNo=( $scope.page-1)*$scope.pageSize;
	$http.get("/api/salesman/list",{"params": {"name":$scope.name,"mobile":$scope.mobile,"address":$scope.address,
		"pageNo":$scope.pageNo,"pageSize":$scope.pageSize}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(date){
    	if(date.status=="0"){
    		$scope.salesmanlist=date.data;
    		$scope.total=date.count;
    		angular.forEach($scope.salesmanlist,function(salesman){
    			salesman.secondCount=salesman.second.length;
    		})
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
	$scope.uploadmainimage2 = function(file){
		
	    
		if(!file.files || file.files.length < 1) return;

	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/single",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	$scope.imgUrl2=data.data;
	    	$scope.addQrcode();
		})
	};
	
	
	$scope.salesmanAdd=function(){
		$scope.salesman.imgUrl=$scope.imgUrl;
		$http.post("/api/salesman/insert",$scope.salesman,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("保存成功~");
	    		$scope.id=null;
	    		$scope.salesman=null;
	    		$scope.selected = null;
	    		$scope.qrcode = null;
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
		$scope.qrcode=sl.qrCode;
		$scope.salesman=sl;
		$scope.imgUrl=sl.imgUrl;
	}
	$scope.idnew=null;
	$scope.checkedsalesmanNew=function(sln){
		$scope.selected1=sln;
		$scope.idnew=sln.id;
		}
	
	$scope.add=function(){
		$scope.id=null;
		$scope.qrcode=null;
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
		$scope.salesman.qrCode = $scope.qrcode;
		$http.post("/api/salesman/update",$scope.salesman,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("更新成功~");
	    		$scope.id=null;
	    		$scope.salesman=null;
	    		$scope.selected = null;
	    		$scope.qrcode = null;
	    		document.getElementById('add').style.display="none"; 
	    		$scope.SalesmanList();
	    	}else{
	    		alert(data.message)
	    	}
		})
	}
	
	$scope.inputNewSalesman=function(){

		$http.get("/api/salesmanSecond/update",{"params": {"salesmanId":$scope.id,"mobile":null,"salesmanIdNew":$scope.idnew}},{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("转入成功~");
	    		$scope.idnew=null;
	    		$scope.selected1=null;
	    		$scope.id=null;
	    		$scope.salesman=null;
	    		$scope.selected = null;
	    		$scope.qrcode = null;
	    		document.getElementById('revise').style.display="none"; 
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
				 if($scope.salesman.second.length<=0){
				 $http.get("/api/salesman/delete",{"params": {"id":$scope.id,"types":0}}, {'Content-Type': 'application/json;charset=UTF-8'})
				    .success(function(result){
				    	if(result.status=="0"){
				    	alert("删除成功");
				    	$scope.page = 1;
				    	$scope.SalesmanList();
				    		
				    	}else{
				    		alert(result.message);
				    	}
				    	
					})
				 }else{
					 if(confirm("账号下有学员，是否转入到其他账号?")){
						 document.getElementById('revise').style.display="block"; 
					 }else{
						 $http.get("/api/salesman/delete",{"params": {"id":$scope.id,"types":1}}, {'Content-Type': 'application/json;charset=UTF-8'})
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
			  
		 }
	}
	
	$scope.reset=function(){
		
		location.reload();
	}
	$scope.resets=function(){
		$scope.idnew=null;
		$scope.selected1=null;
		$scope.id=null;
		$scope.salesman=null;
		$scope.selected = null;
		$scope.qrcode = null;
		 document.getElementById('revise').style.display="none"; 
	}

	$scope.cancel=function(){
		
		$scope.id=null;
		$scope.salesman=null;
		$scope.selected = null;
		$scope.qrcode = null;
		document.getElementById('add').style.display="none"; 
		document.getElementById('addQrCode').style.display="none"; 
	}
	
	$scope.toSecond=function(salesmanId){
		
		location.href="/web/salesman/salesmanSecond?salesmanId="+salesmanId;
	}
	$scope.showQrcode=function(){
		if($scope.id==null){
			 return alert("请选择信息~");
			 
		}
		if($scope.qrcode==null){
			 return alert("无二维码~");
			 
		}
		document.getElementById('addQrCode').style.display="block"; 
	}
////////获取二维码
	/*$scope.ShowQrcode = function(file){
		
	    
		if(!file.files || file.files.length < 1) return;

	    var fd = new FormData();
	    fd.append("file", file.files[0]);
	    fd.append("id", file.files[1]);
		$http.post("/api/qrcode/geturl",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	$scope.qrcode=data.data;
		})
	};*/
	$scope.addQrcode = function(){
		$http.get("/api/qrcode/geturl",{"params": {"imgUrl":$scope.imgUrl2,"id":$scope.salesman.id,"address":$scope.salesman.address,"name":$scope.salesman.name}}, {'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(date){
	    	if(date.status=="0"){
	    		$scope.qrcode=date.data;
	    	}
	    	
		})
	};
})