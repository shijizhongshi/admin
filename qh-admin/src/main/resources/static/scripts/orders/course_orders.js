app.controller("courseOrdersController", function($scope, $http){
	
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.page = 1;
    //一页显示多少条
    $scope.pageSize = 20;

    $scope.ordersType=1;
    
    var formatDate = function (date) { 
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
 
$scope.loaddata = function(){
	$scope.pageNo=($scope.page-1)*$scope.pageSize;
	$http.get("/api/orders/list",{"params": {"pageNo":$scope.pageNo,"pageSize":$scope.pageSize,"ordersType":$scope.ordersType,
		"mobile":$scope.mobile,"todate":formatDate($scope.todate),"fromdate":formatDate($scope.fromdate),
		"recommendTeacher":$scope.recommendTeacher,"orderno":$scope.orderno,"ordersStatus":$scope.ordersStatus}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(result){
    	if(result.status=="0"){
    		
    		angular.forEach(result.data, function(orders){  
    		    // item等价于array[index];
    				if(orders.ordersStatus=="NEW"){
        		    	orders.statusName="待付款的订单";
        		    }else if(orders.ordersStatus=="PAID"){
        		    	orders.statusName="已支付订单";
        		    }else if(orders.ordersStatus=="RECEIVED"){
        		    	orders.statusName="已完成订单";
        		    }
    		});
    		$scope.list=result.data;
    		$scope.total=result.count;
    		
    	}else{
    		alert(result.message);
    	}
    	
	})
	
	$http.get("/api/orders/list",{"params": {"pageNo":$scope.pageNo,"pageSize":0,"ordersType":$scope.ordersType,
		"mobile":$scope.mobile,"todate":formatDate($scope.todate),"fromdate":formatDate($scope.fromdate),
		"recommendTeacher":$scope.recommendTeacher,"orderno":$scope.orderno,"ordersStatus":$scope.ordersStatus}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(result){
    	if(result.status=="0"){
    		
    		angular.forEach(result.data, function(orders){  
    		    // item等价于array[index];
    				if(orders.ordersStatus=="NEW"){
        		    	orders.statusName="待付款的订单";
        		    }else if(orders.ordersStatus=="PAID"){
        		    	orders.statusName="已支付订单";
        		    }else if(orders.ordersStatus=="RECEIVED"){
        		    	orders.statusName="已完成订单";
        		    }
    		});
    		$scope.lists=result.data;
    		$scope.total=result.count;
    		
    	}else{
    		alert(result.message);
    	}
    	
	})
};

$scope.loaddata();
 $scope.orders=null;
	
	$scope.detail=function(o){
	 document.getElementById('revise').style.display="block"; 
	 ///////查询所有的订单的产品
	  $scope.orders=o;
	 $http.get("/api/orders/product/list",{"params":{"orderId":o.id}},{'Content-Type': 'application/json;charset=UTF-8'})
	 .success(function(result){
		 if(result.status=="0"){
			 $scope.productList=result.data;
		 }
	 })
	}

});