app
		.controller(
				"liveShowController",
				function($scope, $http,$sce) {

					$scope.courseTypeName = "医师资格";
					$scope.courseTypeSubclassName = "临床(执业)助理医师";
					$scope.active = 1;
					$scope.typeId = 1;
					$scope.imgUrl = null;
					$scope.typeList = function(typename, typeId) {
						$scope.active = typeId;
						$scope.typeId = typeId;
						$scope.typeBases();
						$scope.courseTypeName = typename;
					};
					$scope.typeBases = function() {
						$http
								.get(
										"/api/course/courseTypeSubclassList",
										{
											"params" : {
												"courseTypeId" : $scope.typeId
											}
										},
										{
											'Content-Type' : 'application/json;charset=UTF-8'
										})
								.success(
										function(data) {
											if (data.status == "0") {
												$scope.courseTypeSubclass = data.data;
												$scope.typeSelected = $scope.courseTypeSubclass[0].courseTypeSubclassName;
												$scope.courseTypeSubclassName = $scope.courseTypeSubclass[0].courseTypeSubclassName;
												$scope.liveBases();
											}
										})
					};
					$scope.typesList = function() {

						$http.get("/api/course/courseTypeList", {
							'Content-Type' : 'application/json;charset=UTF-8'
						}).success(function(data) {
							if (data.status == "0") {
								$scope.courseTypeList = data.data;

							}
						})

					}
					$scope.typesList();
					$scope.typeBases();// ////保证已经来有默认的参数

					// 总条数
					$scope.total = 0;
					// 当前的页数
					$scope.page = 1;
					// 一页显示多少条
					$scope.pageSize = 20;

					$scope.liveBases = function() {
						$scope.pageNo = ($scope.page - 1) * $scope.pageSize;
						$http
								.get(
										"/api/courselive/list",
										{
											"params" : {
												"pageNo" : $scope.pageNo,
												"pageSize" : $scope.pageSize,
												"courseTypeName" : $scope.courseTypeName,
												"courseTypeSubclassName" : $scope.courseTypeSubclassName,
												"liveName" : $scope.liveName
											}
										},
										{
											'Content-Type' : 'application/json;charset=UTF-8'
										}).success(function(data) {
									if (data.status == "0") {
										$scope.livelist = data.data;
										$scope.total = data.count;

										angular.forEach($scope.livelist,function(live){
											if(live.isPlayBack==0){
												live.isPlayBack="无回放";
											}else {
												live.isPlayBack="有回放"
											}
										})
									}
								})
					}

					$scope.liveBases();
					// ///点击专业的事件
					$scope.typeSub = function(typename, sub, $event) {
						// //////
						$scope.page = 1;
						$event.stopPropagation();
						$scope.courseTypeName = typename;
						$scope.courseTypeSubclassName = sub.courseTypeSubclassName;
						$scope.liveBases();
						$scope.typeSelected = sub.courseTypeSubclassName;
					}

					$scope.live = null;
					$scope.liveId = null;

					// 上传课程图片
					$scope.uploadmainimage = function(file) {
						if (!file.files || file.files.length < 1)
							return;
						var fd = new FormData();
						fd.append("file", file.files[0]);
						$http.post("/api/upload/single", fd, {
							withCredentials : true,
							headers : {
								'Content-Type' : undefined
							},
							transformRequest : angular.identity
						}).success(function(data) {
							$scope.imgUrl = data.data;
						})
					};
					// 上传老师头像
					$scope.uploadmainheadimage = function(file) {
						if (!file.files || file.files.length < 1)
							return;
						var fd = new FormData();
						fd.append("file", file.files[0]);
						$http.post("/api/upload/single", fd, {
							withCredentials : true,
							headers : {
								'Content-Type' : undefined
							},
							transformRequest : angular.identity
						}).success(function(data) {
							$scope.headImgUrl = data.data;
						})
					};
					// 保存功能和修改功能
					$scope.addLive = function() {
						if ($scope.live.starttime == null
								|| $scope.live.stoptime == null) {
							alert("开始时间或结束时间格式不正确~");
							return;
						}
						if ($scope.list != null) {
							$scope.live.teacherId = $scope.list.id;
						}
						$scope.live.imgUrl = $scope.imgUrl;
						$scope.live.headImgUrl = $scope.headImgUrl;
						$scope.live.courseTypeName = $scope.courseTypeName;
						$scope.live.courseTypeSubclassName = $scope.courseTypeSubclassName;
						if ($scope.liveId == null) {
							$http
									.post(
											"/api/courselive/save",
											$scope.live,
											{
												'Content-Type' : 'application/json;charset=UTF-8'
											})
									.success(
											function(data) {
												if (data.status == "0") {
													alert("保存成功~");
													document
															.getElementById('add').style.display = "none";
													$scope.headImgUrl = null;
													$scope.imgUrl = null;
													$scope.liveBases();
												} else {
													alert(data.message)
												}
											})
						} else {
							$http
									.post(
											"/api/courselive/update",
											$scope.live,
											{
												'Content-Type' : 'application/json;charset=UTF-8'
											})
									.success(
											function(data) {
												if (data.status == "0") {
													alert("更新成功~");
													document
															.getElementById('add').style.display = "none";
													$scope.liveBases();
												} else {
													alert(data.message)
												}
											})
						}

					};
					// /////做选中的时候用
					var time = null;
					$scope.checkedlive = function(l) {
						$scope.selected = l;
						$scope.live = l;
						$scope.liveId = l.id;
						$scope.imgUrl = l.imgUrl;
						$scope.headImgUrl = l.headImgUrl;
						$scope.starttime = l.starttime;

						$('#sp1').text(getTime(l.starttime));
						$('#sp2').text(getTime(l.stoptime));
					}
					// 格式化时间
					function getTime(date) {
						var date = new Date(date);

						Y = date.getFullYear() + '-';

						M = (date.getMonth() + 1 < 10 ? '0'
								+ (date.getMonth() + 1) : date.getMonth() + 1)
								+ '-';

						D = date.getDate() + ' ';

						h = date.getHours() + ':';

						h = (date.getHours() < 10 ? '0' + date.getHours()
								: date.getHours())
								+ ':';

						m = (date.getMinutes() < 10 ? '0' + date.getMinutes()
								: date.getMinutes());

						var time = (Y + M + D + h + m);
						return time;
					}

					$scope.add = function() {
						$scope.imgUrl = null;
						$scope.live = null;
						$scope.liveId = null;
						document.getElementById('add').style.display = "block";
					}
					// 选中
					$scope.update = function() {
						if ($scope.liveId == null) {
							alert("请先选中信息~");
						} else {
							document.getElementById('add').style.display = "block";

						}
					}
					$scope.deleteLive = function() {
						if ($scope.liveId != null) {
							// //删除课程/
							if (confirm("您确定要删除这个直播资料吗?")) {
								$http.get("/api/courselive/delete",{"params" : {"id" : $scope.liveId}},{'Content-Type' : 'application/json;charset=UTF-8'})
								.success(function(data) {
											if (data.status == '0') {
												alert("删除成功~");
												$scope.page = 1;
												$scope.liveBases();

											} else {
												alert("删除失败~");
											}
										})
							}

						} else {
							alert("请选中信息~");
						}
					}

					$scope.cancel = function() {

						$scope.liveId = null;
						$scope.live = null;
						$scope.imgUrl = null;
						$scope.headImgUrl = null;
						$scope.selected = null;
						document.getElementById('add').style.display = "none";
					}

					$scope.toWhite = function(liveId, liveName) {

						location.href = "/web/course/toWhite?liveId=" + liveId
								+ "&liveName=" + liveName;
					}
					$scope.toplayBack = function() {

						location.href = "/web/course/playback?liveId=" + $scope.liveId;
					}
					$scope.addPlayBack = function() {

						$scope.playback.liveId=$scope.liveId;
								$http.post("/api/playback/insert",$scope.playback,{'Content-Type' : 'application/json;charset=UTF-8'})
								
								.success(function(data){
									   if(data.status=="0"){
										   alert("添加成功");
										   $scope.playback=null;
										   document.getElementById('add2').style.display = "none";
										   $scope.liveBases();
									   }else{
										   alert(data.message);
									   }
								})
							
						
					}
					$scope.add2=function(){
						if($scope.liveId != null){
							if($scope.live.isPlayBack=="有回放"){
								return alert("直播已有回放，请前往'查看回放'中修改");
							}else{
								document.getElementById('add2').style.display = "block";
							}
						}else{
							alert("请选中信息~");
						}
						
					}
					$scope.reset2=function(){
						$scope.playback=null;
						document.getElementById('add2').style.display = "none";
					}
					$scope.ccnew=function(videoId){
						$scope.ccvideo=true;
						$scope.scriptss1="https://p.bokecc.com/playhtml.bo?vid="+videoId+"&siteid=91DD94C27B488135&autoStart=false&playerid=023C4DD30D07346E&playertype=1";
						$scope.trustSrc = function() {
					         return $sce.trustAsResourceUrl($scope.scriptss1);
					     }
						$scope.scriptss2="cciframe_"+videoId;
					};
				});