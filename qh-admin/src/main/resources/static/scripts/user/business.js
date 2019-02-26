app.controller("businessController", function($scope, $http){

	
	$http.get("/api/AddressPcd/selectprovince")
	.success(function(data){
		if(data.status=="0"){
			$scope.provincelist=data.data;
		}
	})
	
	$scope.address=null;
	$scope.getCity=function(p){
		$scope.provinceName=p.provinceName;
		$scope.address=$scope.provinceName;
		$http.get("/api/AddressPcd/selectcity",{"params": {"provinceId":p.provinceId}},{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.citylist=data.data;
			}
		})
	}
	$scope.getCityName=function(city){
		if(city!=null){
			$scope.address=$scope.provinceName+city.cityName;
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
	
	$scope.saveUpdateBusiness=function(){
		$scope.business.address=$scope.address;
		if($scope.business.confirmPassword!=$scope.business.password){
			alert("两次密码输入不一致~");
			return;
		}
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
	    		alert(data.message);
	    	}
	    })
	};
	
	
	$scope.deleteBusiness=function(){
		if($scope.businessId!=null){
			 if(confirm("您确定要删除这条加盟商记录吗")){
				$http.get("/api/business/delete",{"params": {"id":$scope.businessId}},{'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=="0"){
						alert("删除成功~");
						$scope.loaddata();
					}
				})
			}
		}else{
			alert("请选中信息~");
		}
	}
	
	$scope.newBusiness=null;
	$scope.tocharge=function(){
		if($scope.businessId!=null){
			
			$scope.businessName=$scope.business.name;
			 document.getElementById('revise').style.display="block"; 
		}else{
			alert("请选中信息~");
		}
	}
	
	$scope.submitcharge=function(){
		if($scope.newBusiness.account!=null && $scope.newBusiness.payaccount!=null){
			$scope.newBusiness.id=$scope.businessId;
			$http.post("/api/business/tocharge",$scope.newBusiness,{'Content-Type': 'application/json;charset=UTF-8'})
		    .success(function(data){
		    	if(data.status=="0"){
		    		alert("充值成功~");
		    		document.getElementById('revise').style.display="none"; 
		    		$scope.loaddata();
		    	}
		    })
		}else{
			alert("充值和兑换金额不能为空~");
		}
		
	}
	
});