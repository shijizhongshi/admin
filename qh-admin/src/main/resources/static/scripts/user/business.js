app.controller("businessController", function($scope, $http){

	
	$http.get("/api/AddressPcd/selectprovince")
	.success(function(data){
		if(data.status=="0"){
			$scope.provincelist=data.data;
		}
	})
	
	$scope.address=null;
	$scope.getCity=function(p){
		$scope.address=p.provinceName;
		$http.get("/api/AddressPcd/selectcity",{"params": {"provinceId":p.provinceId}},{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.citylist=data.data;
			}
		})
	}
	$scope.getCityName=function(cityName){
		if(cityName!=null){
			$scope.address=$scope.provinceName+cityName;
		}
	}
	var changeDate = function (date) { 
		if(date){
		  	var y = date.getFullYear();  
		    var m = date.getMonth() + 1;  
		    m = m < 10 ? '0' + m : m;  
		    var d = date.getDate();  
		    d = d < 10 ? ('0' + d) : d;  
		    return y + '-' + m + '-' + d;
		} else{
			return '';
		}
	      
	};
	$scope.total=0;
	$scope.current=1;
	$scope.pageSize=20;
	$scope.loaddata=function(){
		$http.get("/api/business/list",{"params": {"name":$scope.name,"fromdate":changeDate($scope.fromdate),
			"todate":changeDate($scope.todate),"address":$scope.address,"page":$scope.current}},{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.businesslist=data.data;
			}
		})
	}
	
	$scope.loaddata();
	$scope.business=null;
	$scope.businessId=null;
	
	$scope.add=function(){
		$scope.business=null;
		 document.getElementById('add').style.display="block";
	}
	
	$scope.checkBusiness=function(business){
		$scope.selected=business;
		$scope.business=business;
		$scope.businessId=business.id;
	}
	
	
	$scope.update=function(){
		if($scope.businessId!=null){
			 document.getElementById('add').style.display="block";
		}else{
			alert("请选中信息~");
		}
		
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
	    	$scope.business.logo=data.data;
	    	
		})
	};
	$scope.confirm=function(confirmpassword,password){
		if(confirmpassword!=password){
			alert("两次密码输入不一致~");
		}
	}
	
	
	$scope.saveUpdateBusiness=function(){
		$scope.business.address=$scope.address;
		$http.post("/api/business/saveupdate",$scope.business,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		if($scope.businessId!=null){
	    			alert("修改成功~");
	    		}else{
	    			alert("添加成功~");
	    		}
	    			document.getElementById('add').style.display="none"; 
	    			$scope.loaddata();
	    	}else{
	    		alert("修改失败~");
	    	}
	    })
	};
	
	
	
	
});