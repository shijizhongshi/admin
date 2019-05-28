app.controller(
				"newController",
				function($scope, $http) {
					// var ue = UE.getEditor('editor');
					// 总条数
					$scope.total = 0;
					// 当前的页数
					$scope.page = 1;
					// 一页显示多少条
					$scope.pageSize = 20;
					// 上传主展示图片
					$scope.types = 0;
					$scope.news = null;
					$scope.title = null;
					$scope.imgUrl = null;
					$scope.loaddata = function() {
						$scope.pageNo = ($scope.page - 1) * $scope.pageSize;
						$http.get("/api/news/newLists", {
							"params" : {
								"title" : $scope.title,
								"pageNo" : $scope.pageNo,
								"pageSize" : $scope.pageSize
							}
						}, {
							'Content-Type' : 'application/json;charset=UTF-8'
						}).success(function(result) {
							if (result.status == "0") {
								$scope.newlist = result.data;
								$scope.total = result.count;

							} else {
								alert(result.message);
							}

						})
					};

					$scope.loaddata();

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

					$scope.newadd = function() {
						$scope.news.content = editor.txt.html();
						$scope.news.imgUrl = $scope.imgUrl;
						// $scope.news.content = ue.getContent();
						$http
								.post(
										"/api/news/save",
										$scope.news,
										{
											'Content-Type' : 'application/json;charset=UTF-8'
										})
								.success(
										function(data) {
											if (data.status == "0") {
												alert("保存成功~");
												document.getElementById('add').style.display = "none";
												$scope.loaddata();
											} else {
												alert(data.message)
											}
										})
					};
					$scope.newsId = null;
					var content = null;
					$scope.checkednews = function(news) {
						$scope.selected = news;
						$scope.newsId = news.id;
						$scope.news = news;
						$scope.imgUrl = news.imgUrl;
						//先清空再赋值
						$("p").empty();
					}
					//点击弹出添加窗口
					$scope.add = function() {
						$scope.newsId = null;
						$scope.news = null;
						$scope.imgUrl = null;
						$("p").empty();
						
						document.getElementById('add').style.display = "block";
					}
					$scope.update = function() {
						if ($scope.newsId == null) {
							alert("请选择信息~")
						} else {
							if ($scope.news.content != null) {
								$("p").after($scope.news.content);	
							}
							document.getElementById('add').style.display = "block";
						}
					}

					$scope.newsupdate = function() {
						$scope.news.content = editor.txt.html();
						// $scope.news.content = ue.getContent();

						$scope.news.imgUrl = $scope.imgUrl;
						$http
								.post(
										"/api/news/update",
										$scope.news,
										{
											'Content-Type' : 'application/json;charset=UTF-8'
										})
								.success(
										function(data) {
											if (data.status == "0") {
												$("p").empty();
												alert("更新成功~");
												document.getElementById('add').style.display = "none";
												$scope.loaddata();
											} else {
												alert(data.message)
											}
										})
					}
					$scope.deletenews = function() {

						if ($scope.newsId == null) {
							alert("请选择信息~")
						} else {
							if (confirm("你确定要删除这条记录吗?")) {
								$http
										.get(
												"/api/news/delete",
												{
													"params" : {
														"id" : $scope.newsId
													}
												},
												{
													'Content-Type' : 'application/json;charset=UTF-8'
												}).success(function(result) {
											if (result.status == "0") {
												alert("删除成功");
												$scope.page = 1;
												$scope.loaddata();

											} else {
												alert(result.message);
											}

										})
							}

						}
					}

					$scope.reset = function() {

						location.reload();
					}

					$scope.cancel = function() {
						$("p").empty();

						$scope.newsId = null;
						$scope.news = null;
						$scope.selected = null;
						document.getElementById('add').style.display = "none";
					}

					// 测试 富文本编辑器
					// 获取元素
					var div = document.getElementById('div1');

					// 生成编辑器
					var editor = new wangEditor(div);
					// 开启debug模式
					editor.customConfig.debug = editor.customConfig.debug = location.href
							.indexOf('wangeditor_debug_mode=1') > 0;
					// 上传图片到服务器
					editor.customConfig.uploadFileName = 'file'; // 设置文件上传的参数名称
					editor.customConfig.uploadImgServer = '/api/upload/wangEditor'; // 设置上传文件的服务器路径
					editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024; // 将图片大小限制为
																			// 3M
					editor.customConfig.uploadImgMaxLength = 5;// 限制一次最多上传 5 张图片
					
					// 自定义菜单配置
				    editor.customConfig.menus = [
				    	'head',  // 标题
				        'bold',  // 粗体
				        'fontSize',  // 字号
				        'fontName',  // 字体
				        'italic',  // 斜体
				        'underline',  // 下划线
				        'strikeThrough',  // 删除线
				        'foreColor',  // 文字颜色
				        'backColor',  // 背景颜色
				        'link',  // 插入链接
				        'list',  // 列表
				        'justify',  // 对齐方式
				        'quote',  // 引用
				        'emoticon',  // 表情
				        'image',  // 插入图片
				        'table',  // 表格
				       // 'video',  // 插入视频
				        //'code',  // 插入代码
				        'undo',  // 撤销
				        'redo'  // 重复
				    ]

					// 表情面板可以有多个 tab ，因此要配置成一个数组。数组每个元素代表一个 tab 的配置
					editor.customConfig.emotions = [ {
						// tab 的标题
						title : '默认',
						// type -> 'emoji' / 'image'
						type : 'image',
						// content -> 数组
						content : [
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif",
									alt : "[草泥马]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif",
									alt : "[神马]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif",
									alt : "[浮云]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c9/geili_thumb.gif",
									alt : "[给力]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f2/wg_thumb.gif",
									alt : "[围观]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/vw_thumb.gif",
									alt : "[威武]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/panda_thumb.gif",
									alt : "[熊猫]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/81/rabbit_thumb.gif",
									alt : "[兔子]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/otm_thumb.gif",
									alt : "[奥特曼]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/15/j_thumb.gif",
									alt : "[囧]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/hufen_thumb.gif",
									alt : "[互粉]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c4/liwu_thumb.gif",
									alt : "[礼物]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ac/smilea_thumb.gif",
									alt : "[呵呵]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0b/tootha_thumb.gif",
									alt : "[嘻嘻]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/laugh.gif",
									alt : "[哈哈]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/14/tza_thumb.gif",
									alt : "[可爱]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/af/kl_thumb.gif",
									alt : "[可怜]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a0/kbsa_thumb.gif",
									alt : "[挖鼻屎]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f4/cj_thumb.gif",
									alt : "[吃惊]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6e/shamea_thumb.gif",
									alt : "[害羞]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c3/zy_thumb.gif",
									alt : "[挤眼]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/29/bz_thumb.gif",
									alt : "[闭嘴]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/71/bs2_thumb.gif",
									alt : "[鄙视]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/lovea_thumb.gif",
									alt : "[爱你]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/9d/sada_thumb.gif",
									alt : "[泪]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/19/heia_thumb.gif",
									alt : "[偷笑]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/8f/qq_thumb.gif",
									alt : "[亲亲]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/b6/sb_thumb.gif",
									alt : "[生病]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/58/mb_thumb.gif",
									alt : "[太开心]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/17/ldln_thumb.gif",
									alt : "[懒得理你]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/98/yhh_thumb.gif",
									alt : "[右哼哼]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/zhh_thumb.gif",
									alt : "[左哼哼]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a6/x_thumb.gif",
									alt : "[嘘]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/af/cry.gif",
									alt : "[衰]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/73/wq_thumb.gif",
									alt : "[委屈]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/9e/t_thumb.gif",
									alt : "[吐]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f3/k_thumb.gif",
									alt : "[打哈欠]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/27/bba_thumb.gif",
									alt : "[抱抱]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7c/angrya_thumb.gif",
									alt : "[怒]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/5c/yw_thumb.gif",
									alt : "[疑问]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/a5/cza_thumb.gif",
									alt : "[馋嘴]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/88_thumb.gif",
									alt : "[拜拜]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/e9/sk_thumb.gif",
									alt : "[思考]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/24/sweata_thumb.gif",
									alt : "[汗]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7f/sleepya_thumb.gif",
									alt : "[困]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6b/sleepa_thumb.gif",
									alt : "[睡觉]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/90/money_thumb.gif",
									alt : "[钱]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/0c/sw_thumb.gif",
									alt : "[失望]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/cool_thumb.gif",
									alt : "[酷]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/8c/hsa_thumb.gif",
									alt : "[花心]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/49/hatea_thumb.gif",
									alt : "[哼]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/36/gza_thumb.gif",
									alt : "[鼓掌]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d9/dizzya_thumb.gif",
									alt : "[晕]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/1a/bs_thumb.gif",
									alt : "[悲伤]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/62/crazya_thumb.gif",
									alt : "[抓狂]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/91/h_thumb.gif",
									alt : "[黑线]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6d/yx_thumb.gif",
									alt : "[阴险]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/89/nm_thumb.gif",
									alt : "[怒骂]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/hearta_thumb.gif",
									alt : "[心]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/ea/unheart.gif",
									alt : "[伤心]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/58/pig.gif",
									alt : "[猪头]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d6/ok_thumb.gif",
									alt : "[ok]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d9/ye_thumb.gif",
									alt : "[耶]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d8/good_thumb.gif",
									alt : "[good]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c7/no_thumb.gif",
									alt : "[不要]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d0/z2_thumb.gif",
									alt : "[赞]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/40/come_thumb.gif",
									alt : "[来]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d8/sad_thumb.gif",
									alt : "[弱]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/91/lazu_thumb.gif",
									alt : "[蜡烛]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/cake.gif",
									alt : "[蛋糕]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/d3/clock_thumb.gif",
									alt : "[钟]"
								},
								{
									src : "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/1b/m_thumb.gif",
									alt : "[话筒]"
								}

						]
					} ]
					 // 自定义字体
				    editor.customConfig.fontNames = [
				        '默认字体',
				        '宋体',
				        '楷体',
				        '黑体',
				        'Arial',
				        '微软雅黑'
				    ]
					// 隐藏“网络图片”tab
				    editor.customConfig.showLinkImg = false;
				    
					editor.create();
				});