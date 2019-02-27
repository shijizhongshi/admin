app.controller("studentController", function($scope, $http){

	$scope.active=1;
	$scope.typeId=1;
	$scope.courseTypeName="医师资格";
	$scope.courseTypeSubclassName="临床(执业)助理医师";
	$scope.typeList=function(typeId){
			$scope.active=typeId;
			$scope.typeId=typeId;
			$scope.typeBases();
	};
	$scope.typeBases=function(){
		$http.get("/api/course/courseTypeSubclassList",{"params": {"courseTypeId":$scope.typeId}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courseTypeSubclass=data.data;
			}
		})
	};
	
/////点击专业的事件
	$scope.typeSub=function(typename,sub){
		////////查班级的集合
		$scope.courseTypeName=typename;
		$scope.courseTypeSubclassName=sub.courseTypeSubclassName;
		
		if($scope.typesName=="班级"){
			$scope.classlists();
		}else if($scope.typesName=="课程"){
			$scope.courselists();
		}
		$scope.selected=sub;
		
	}
	$scope.typeBases();//////保证已经来有默认的参数
	
	
	
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
		///////学员的查询(全部查询)
		$http.get("/api/user/select/student",{"params": {"fromdate":changeDate($scope.fromdate),
			"todate":changeDate($scope.todate),"realnameORmobile":$scope.realnameORmobile,
			"status":$scope.status,"page":$scope.current}}, {'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.userlist=data.data;
			}
		})
	}
	$scope.loaddata();
	
	
	$http.get("/api/AddressPcd/selectprovince")
	.success(function(data){
		if(data.status=="0"){
			$scope.provincelist=data.data;
			$scope.openCourse=data.data[0];
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
	
	$scope.user=null;
	$scope.userId=null;
	$scope.add=function(){
		$scope.user=null;
		 document.getElementById('add').style.display="block";
	}
	
	$scope.checkUser=function(user){
		$scope.selected=user;
		$scope.user=user;
		$scope.userId=user.id;
	}
	
	
	$scope.update=function(){
		if($scope.userId!=null){
			 document.getElementById('add').style.display="block";
		}else{
			alert("请选中信息~");
		}
		
	}
	
	
	////////保存和修改学员的信息
	$scope.saveORupdateUser=function(){
		if($scope.user.password!=$scope.confirmPassword){
			alert("输入两次密码不一致~");
			return;
		}
		$scope.user.address=$scope.address;
		$http.post("/api/user/saveupdate",$scope.user,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		if($scope.userId!=null){
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
	}
	
	$scope.deleteUser=function(){
		if($scope.userId!=null){
			 if(confirm("您确定要删除这条学员信息吗")){
				$http.get("/api/user/delete",{"params": {"id":$scope.userId}},{'Content-Type': 'application/json;charset=UTF-8'})
				.success(function(data){
					if(data.status=="0"){
						alert("删除成功~");
					}else{
						alert(data.message);
					}
				})
			}
		}else{
			alert("请选中信息~");
		}
	}
	
	///////////////////////////////////////处理报班的信息开始////////////////////////////
	
	$scope.classlists=function(){
		/////////查这个专业下的所有的班级信息
		$http.get("/api/courseclass/list",{"params": {"courseTypeName":$scope.courseTypeName,"courseTypeSubclassName":$scope.courseTypeSubclassName}},{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.classlist=data.data;
				
			}
		})
	};
	$scope.courselists=function(){
/////////查这个专业下的所有的课程信息
		$http.get("/api/course/courseList",{"params": {"courseTypeName":$scope.courseTypeName,
			"courseTypeSubclassName":$scope.courseTypeSubclassName,"pageNo":0,"pageSize":0,}},{'Content-Type': 'application/json;charset=UTF-8'})
		.success(function(data){
			if(data.status=="0"){
				$scope.courselist=data.data;
				
			}
		})
	}
	
	
	$scope.types=null;
	$scope.typesName=null;
	$scope.applys=function(types){
		if($scope.userId!=null){
			$scope.openCourse.userId=$scope.userId;
			if(types==1){
				$scope.openCourse.types=1;
				$scope.types="网课报班";
				$scope.typesName="班级";
				document.getElementById('revise').style.display="block"; 
				$scope.classlists();
			}else if(types==2){
				$scope.openCourse.types=2;
				$scope.types="课程报名";
				$scope.typesName="课程";
				document.getElementById('revise').style.display="block"; 
				/////////查这个专业下的所有的课程信息
				$scope.courselists();
			}else if(types==3){
				alert("敬请期待~")
			}
			
		}else{
			alert("请选中信息~");
		}
		
	}
	$scope.productId = [];
	$scope.productlisted = [];
	$scope.prices=0;
    var updateSelected = function(action,id,product,price) {
    	if(action == 'add' && $scope.productId.indexOf(id) == -1){
    		if($scope.typesName=="课程"){
    			///////courseId
    			$http.get("/api/btl/existOpen",{"params": {"courseId":id,"userId":$scope.userId}},{'Content-Type': 'application/json;charset=UTF-8'})
    			.success(function(data){
    				if(data.status=="1"){
    					alert(data.message);
    					return;
    				}else{
    					$scope.productId.push(id);
    		    		$scope.productlisted.push(product);
    		    		$scope.prices=$scope.prices+price;
    				}
    			})
    		}
    		if($scope.typesName=="班级"){
    			///////classId
    			$http.get("/api/btl/existOpen",{"params": {"classId":id,"userId":$scope.userId}},{'Content-Type': 'application/json;charset=UTF-8'})
    			.success(function(data){
    				if(data.status=="1"){
    					alert(data.message);
    					return;
    				}else{
    					$scope.productId.push(id);
    		    		$scope.productlisted.push(product);
    		    		$scope.prices=$scope.prices+price;
    				}
    			})
    			
    			
    		}
    		
    		
    	}
      if(action == 'remove' && $scope.productId.indexOf(id) != -1){
    	  $scope.productId.splice($scope.productId.indexOf(id), 1);
    	  $scope.productlisted.splice($scope.productlisted.indexOf(product), 1);
    	  $scope.prices=$scope.prices-price;
      }
    
    };
 
    $scope.updateSelection = function($event,id,product,price) {
      var checkbox = $event.target;
      var action = (checkbox.checked ? 'add' : 'remove');
      updateSelected(action,id,product,price);
    };
 
    $scope.isSelected = function(id) {
    	/*checkbox选中*/
      return $scope.productId.indexOf(id) >= 0;
    };   
	
   
	$scope.jiamengshang=false;
	/////首先判断是加盟商还是系统管理员
	if($("#username").val()=="admin"){
		////////销售员开课
		$scope.jiamengshang=false;
		$scope.courseWays=1;/////销售人员开课
		$scope.surplusaccount=$("#surplusaccount").val();
		
	}else{
		////////加盟商开课需要加盟商的账号$("#username").val()
		$scope.surplusaccount=$("#surplusaccount").val();
		$scope.jiamengshang=true;
		$scope.adminName=$("#username").val();/////加盟商的账号的时候传账号
		$scope.courseWays=2;////加盟商开课
	}
	

	$scope.openCourses=function(){
		//alert(parseInt($scope.surplusaccount,10));
		if($scope.courseWays==1 && $scope.openCourse.salesName==null){
			alert("销售信息不能为空");
			return;
		}
		/*if($scope.courseWays==2){
			if(parseInt($scope.surplusaccount)<$scope.prices){
				alert("剩余兑换课程金额不足~");
				return;
			}
		}*/
		$scope.openCourse.adminName=$scope.adminName;
		$scope.openCourse.courseWays=$scope.courseWays;
		$scope.openCourse.productId=$scope.productId;
		$scope.openCourse.account=$scope.prices;//////兑换课程的总金额
		$http.post("/api/btl/open/course",$scope.openCourse,{'Content-Type': 'application/json;charset=UTF-8'})
	    .success(function(data){
	    	if(data.status=="0"){
	    		alert("开课成功~");
	    		document.getElementById('revise').style.display="none"; 
	    		$scope.loaddata();
	    	}else{
	    		alert(data.message);
	    	}
	    })
	}
	
	
	
});