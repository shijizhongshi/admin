app.controller("fuwushopController", function($scope, $http) {

	$scope.total = 0;
	//当前的页数
	$scope.page = 1;
	//一页显示多少条
	$scope.pageSize = 20;

	$scope.shopList = function() {
		$http.get("/api/shop/listshort", {
			"params" : {
				"page" : $scope.page
			}
		}, {
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				$scope.shoplist = data.data;
				$scope.total = data.count;

			} else {
				alert("没有符合条件信息")
			}
		})
	};
	$scope.shopList();
	$scope.shop = null;
	$scope.shopId = null;
	
	$scope.add = function() {
		$scope.shop = null;
		$scope.shopId = null;
		$scope.doorHeadUrl=null;
		$scope.shopLogo=null;
		$scope.selected = null;
		document.getElementById('add').style.display="block"; 
	};

	$scope.update = function() {
		if ($scope.shopId != null) {
			document.getElementById('add').style.display="block"; 
		} else {
			alert("请选择信息");
		}
	};
	$scope.checkshop = function(shop) {
		if($scope.selected !=shop){
		$scope.selected = shop;
		$scope.shop = shop;
		$scope.doorHeadUrl=$scope.shop.doorHeadUrl ;
		$scope.shopLogo=$scope.shop.shopLogo;
		$scope.shopId = shop.id;

		}else{
			$scope.selected = null;
			$scope.shop = null;
			$scope.doorHeadUrl=null ;
			$scope.shopLogo=null;
			$scope.shopId = null;
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
	    	$scope.doorHeadUrl=data.data;
		})
	};

	$scope.uploadmainimage11 = function(file){
		if(!file.files || file.files.length < 1) return;
	    var fd = new FormData();
	    fd.append("file", file.files[0]);
		$http.post("/api/upload/single",fd,{
	        withCredentials: true,
	        headers: {'Content-Type': undefined },
	        transformRequest: angular.identity
	    })
	    .success(function(data){
	    	$scope.shopLogo=data.data;
		})
	};
	
	$scope.saveUpdateShop=function(){
		$scope.shop.doorHeadUrl=$scope.doorHeadUrl;
		$scope.shop.shopLogo=$scope.shopLogo;
		$http.post("/api/shop/saveUpdateShort",$scope.shop,{
			'Content-Type' : 'application/json;charset=UTF-8'
		}).success(function(data) {
			if (data.status == "0") {
				if($scope.shopId==null){
					$scope.operating.operatingStatus="添加";
			    	$scope.operating.operatingUser=$scope.shop.shopName;
			    	$scope.insertOperating();
					alert("保存成功~");
				}else{
					$scope.operating.operatingStatus="修改";
			    	$scope.operating.operatingUser=$scope.shop.shopName;
			    	$scope.insertOperating();
					alert("修改成功~");
				}
				document.getElementById('add').style.display="none"; 
				$scope.selected = null;
				$scope.shopList();
			} else {
				alert(data.message);
			}
		})
		
	}
	$scope.deleteshort=function(){
		if($scope.shopId!=null){
			if(confirm("您确定要删除这个信息吗")){
			$http.get("/api/shop/deleteshort", {
				"params" : {
					"id" : $scope.shopId
				}
			}, {
				'Content-Type' : 'application/json;charset=UTF-8'
			}).success(function(data) {
				if (data.status=="0") {
					$scope.operating.operatingStatus="删除";
			    	$scope.operating.operatingUser=$scope.shop.shopName;
			    	$scope.insertOperating();
					alert("删除成功~");
					$scope.shopList();
				} else {
					alert(data.message);
				}
			})
			}
		}else{
			alert("请选择信息");
		}
		
	};
	
	$scope.reset=function(){
		
		document.getElementById('add').style.display="none"; 
		$scope.selected = null;
		$scope.shop = null;
		$scope.doorHeadUrl=null ;
		$scope.shopLogo=null;
		$scope.shopId = null;
	}
	
	$scope.refresh=function(){
		
		location.reload();
	}
	$scope.operating={operatingScope:"临时店铺管理",userRoleUsername:$("#username").val(),operatingStatus:"",operatingUser:""}
	$scope.insertOperating = function(){
		
		$http.post("/api/operating/insert",$scope.operating, {'Content-Type': 'application/json;charset=UTF-8'})
	    
	};
	
})