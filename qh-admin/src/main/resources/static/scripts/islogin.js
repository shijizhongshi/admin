app.controller("IsloginController", function($scope, $http){
	
        //上传主展示图片
	
    	if($("#username").val()==undefined ||　$("#username").val()==""){
    		location.href="/";
    	}else{
        	$http.get("/api/admin/islogin")
            .success(function(data){
            	if(data.status=="0"){
            		$scope.menus=data.data;
            		if($(".activemenuid").val()!=null && $(".activemenuid").val()!=undefined && $(".activemenuid").val()!=""){
            			$scope.menus.forEach(function(menu,index){
                	        if(menu.category==$(".activemenuid").val()){
                	        	$scope.active=menu;
                	        	$scope.displeyactive=menu;
                	        }
                	        menu.list.forEach(function(sub,index){
                    	        if(sub.category==$(".activesubmenuid").val()){
                    	        	$scope.subactive=sub;
                    	        }
                    		})
                		})
            		}
            		
            	}else{
            		alert(data.message);
            		location.href="/";
            	}
            	
        	});
    	}
    	
    	$scope.getOpenSub=function(menu){
    		$scope.active=menu;
    		$scope.menus.forEach(function(menus,index){
    	        if(menus.category==menu.category){
    	        	$scope.active=menu;
    	        	$scope.displeyactive=menu;
    	        }
    	        
    		})
    		
    		
    	}
    	

});