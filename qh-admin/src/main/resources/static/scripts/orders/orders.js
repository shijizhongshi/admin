app.controller("ordersController", function($scope, $http){
	
	 //总条数
    $scope.total = 0;
    //当前的页数
    $scope.current = 1;
    //一页显示多少条
    $scope.pageSize = 20;

    $scope.ordersType=0;
    $scope.tab0=0
    $scope.onmousedowngo=function(ordersType){
    	////ordersType 1:课程订单   0:商品订单   2:服务订单
    	$scope.tab0=ordersType;
    	$scope.ordersType=ordersType;
    	$scope.loaddata();
    }
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
	$scope.pageNo=( $scope.current-1)*$scope.pageSize;
	$http.get("/api/orders/list",{"params": {"page":$scope.current,"ordersType":$scope.ordersType,
		"mobile":$scope.mobile,"todate":formatDate($scope.todate),"fromdate":formatDate($scope.fromdate)}}, {'Content-Type': 'application/json;charset=UTF-8'})
    .success(function(result){
    	if(result.status=="0"){
    		
    		angular.forEach(result.data, function(orders){  
    		    // item等价于array[index];
    			if(orders.ordersType=="0"){
    				orders.refund="无";
    				if(orders.ordersStatus=="NEW"){
        		    	orders.statusName="待付款的订单";
        		    }else if(orders.ordersStatus=="PAID"){
        		    	orders.statusName="待发货的订单";
        		    }else if(orders.ordersStatus=="DELIVERED"){
        		    	orders.statusName="待收货的订单";
        		    }else if(orders.ordersStatus=="RECEIVED"){
        		    	orders.statusName="已完成订单";
        		    }else{
        		    	orders.refund="有";
        		    	orders.statusName="退款订单";
        		    } 
    			}
    			if(orders.ordersType=="2"){
    				orders.refund="无";
    				if(orders.ordersStatus=="NEW"){
        		    	orders.statusName="待付款的订单";
        		    }else if(orders.ordersStatus=="PAID"){
        		    	orders.statusName="待使用的订单";
        		    }else if(orders.ordersStatus=="RECEIVED"){
        		    	orders.statusName="已使用的订单";
        		    }else if(orders.ordersStatus=="CANCELSERVE"){
        		    	orders.statusName="取消预订等待商家处理";
        		    	orders.refund="有";
        		    }else if(orders.ordersStatus=="CANCELSERVEED"){
        		    	orders.statusName="已取消订单";
        		    	orders.refund="有";
        		    }else if(orders.ordersStatus=="REJECTCANCELSERVEED"){
        		    	orders.statusName="商家拒绝了您的取消预约申请";
        		    	orders.refund="有";
        		    } 
    			}
    		    
    		});
    		$scope.list=result.data;
    		$scope.total=result.count;
    		
    	}else{
    		alert(result.message);
    	}
    	
	})
};

$scope.loaddata();
$scope.order=null;
$scope.detail=function(o){
	if($scope.ordersType==0){
		 document.getElementById('revise1').style.display="block"; 
	}else{
		 document.getElementById('revise1').style.display="none"; 
	}
	if($scope.ordersType==2){
		 document.getElementById('revise').style.display="block"; 
	}else{
		document.getElementById('revise').style.display="none"; 
	}
	
	 ///////查询所有的订单的产品
	  $scope.order=o;
	 $http.get("/api/orders/product/list",{"params":{"orderId":o.id}},{'Content-Type': 'application/json;charset=UTF-8'})
	 .success(function(result){
		 if(result.status=="0"){
			 $scope.productList=result.data;
		 }
	 })
	}
	

});