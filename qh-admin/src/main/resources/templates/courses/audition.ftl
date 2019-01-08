<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/audition.js"></script>
<script src="/scripts/admin.js"></script>
<@b.body menu="sidebarmenu-course"
submenu="sidebarmenu-course-audition">
<div ng-controller="CourseNofreeController">
	
		<div>
			<div class="classify">
				<ul class="menu">

					<li class="list" ng-click="typeList(1)">医师资格
						<ul class="items" ng-class="{'active':active==1}">
							<li ng-repeat="sub in courseTypeSubclass"
								ng-click="auditionSub('医师资格',sub)"  ng-class="{'selected':selected==sub}" >{{sub.courseTypeSubclassName}}</li>

						</ul>
					</li>
					<li class="list" ng-click="typeList(2)">药师资格
						<ul class="items" ng-class="{'active':active==2}">
							<li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>

						</ul>
					</li>
					<li class="list" ng-click="typeList(3)">中医基础理论
						<ul class="items" ng-class="{'active':active==3}">
							<li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
						</ul>
					</li>
					<li class="list" ng-click="typeList(4)">卫生资格
						<ul class="items" ng-class="{'active':active==4}">
							<li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>

						</ul>
					</li>
					<li class="list"><a href="#">健康管理师</a>
						<ul class="items" ng-class="{'active':active==5}">
							<li ng-repeat="sub in courseTypeSubclass">{{sub.courseTypeSubclassName}}</li>
						</ul></li>



				</ul>
			</div>
			<div class="details">
				<div class="details-nav">
					<ul>
						<li><img src="/images/sjk-home.png" style="color: red;" />我的主页</li>
						<li>/</li>
						<li>课程章节管理</li>
					</ul>
				</div>
				<div class="details-frame">
					<div class="details-frame-content">

						<div class="select-2"
							style="float: left; margin-right: 15px; width: 15%;">
							<span>搜索课程</span>
							<form id="search">
								<input type="text" name="search" style="text-indent: 2em;" />
							</form>
						</div>
						<div class="select-2"
							style="float: left; margin-right: 15px; width: 15%;">
							<span>搜索老师</span>
							<form id="">
								<input type="text" name="search" style="text-indent: 2em;" />
							</form>
						</div>
					</div>
					<div class="manage">
						<ul style="height: 80px;" class="show">

							<li ng-click="add()"
								style="margin-left: 70px; background: #9DE879;"><span
								class="glyphicon glyphicon-plus"></span>&nbsp;添加试听课程</li>
							<li ng-click="update()" style="background: #F9CD33;"><span
								class="glyphicon glyphicon-pencil"></span>&nbsp;修改试听课程</li>
							<li ng-click="deleteAudition()" style="background: #F86846;"><span
								class="glyphicon glyphicon-trash"></span>&nbsp;删除试听课程</li>

							<li style="float: right; margin-right: 100px; background: none;"><img
								src="/images/sjk-f5.png" name="changyi" /></li>
						</ul>
						<div class="admin-table">

							<table>
								<tr>
									<th>试听分类</th>
									<th>课程图片</th>
									<th>课程名称</th>
									<th>课程老师</th>
									<th>课程时长</th>
									<th>阿里云ID</th>
									<th>备用地址</th>
									<th>是否推荐</th>
									<th>课程用途</th>
									<th>创建日期</th>
								</tr>

								<tr ng-repeat="a in auditionlist" ng-click="checkedAudition(a)"
									ng-class="{'selected':selected==a}">
									<th>{{a.courseResourceType}}</th>
									<th>{{a.imgUrl}}</th>
									<th>{{a.courseName}}</th>
									<th>{{a.teachers}}</th>
									<th>{{a.palyTime}}</th>
									<th>{{a.aliyunId}}</th>
									<th>备用地址</th>
									<th>{{a.isremmend}}</th>
									<th>{{a.courseUseDifference}}</th>
									<th>{{a.addtime}}</th>
								</tr>
							</table>

						</div>
						<div class="col-sm-6">
							<ul uib-pagination boundary-links="true" total-items="total"
								ng-model="page" items-per-page="pageSize" max-size="5"
								class="pagination-sm" previous-text="&lsaquo;"
								next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
								ng-change="courseBases()">
							</ul>
						</div>

						<!--弹窗-->
						<div class="poop" id="add">
							<form id="myform">
								<h3>添加试听课程</h3>
								<span style="margin-right: 20px;">已选专业类型:</span> <span>{{courseTypeSubclassName}}</span>
								<div class="grade-add">
									<div class="grade-left" style="padding-right: 5%;">

										<div class=" select-2">
											<span>班级名称：</span> <input type="text" ng-model="courseNofree.courseName"
												placeholder="请输入班级名称" style="width: 230px;" />
										</div>

										<div style="width: 100%; height: 90px; clear: both;">
											<div class=" select-2" style="float: left;">
												<img src="/images/sjk-xl.png" /> <span>分类：</span> <select>
													<option disabled selected style='display: none;'>选择类型</option>
													<option ng-model="courseNofree.courseResourceType"></option>
													
												</select>
											</div>
											<div class=" select-2" style="float: right;">
												<img src="/images/sjk-xl.png" /> <span>课程用途：</span> <select>
													<option disabled selected style='display: none;'>选择课程用途</option>
													<option ng-model="courseNofree.courseUseDifference"></option>
													
												</select>
											</div>
										</div>
										<div style="width: 100%; height: 90px; clear: both;">
											<div class=" select-2">

												<span>播放时长（分）：</span> <input type="text" ng-model="courseNofree.courseUseDifference"
													placeholder="请输入时长" />
											</div>
										</div>
										<div style="width: 100%; height: 90px; clear: both;">
											<div class=" select-2">
												<span>老师：</span> <input type="text" ng-model="courseNofree.teachers"
													placeholder="请选择老师" /> <i onclick="showDiv2()"
													style="position: absolute; right: 10px; top: 45px; display: inherit; cursor: pointer;"
													class="glyphicon glyphicon-search"></i>
											</div>
										</div>
										<ul>
											<li>是否推荐</li>
											<li><input type="radio" name="tuijian" />是</li>
											<li><input type="radio" name="tuijian" />否</li>
										</ul>
										<div class="costs-uploadfile-div">
											<input type="file" value="上传试听课图片" onchange="angular.element(this).scope().uploadmainimage(this)" >
											<input type="hidden" ng-model="courseNofree.imgUrl"/>
											<div
												style="height: 130px; width: 40%; border: solid 1px #B1B1B1; margin-top: 3px;">
												<img src="{{courseNofree.imgUrl}}" style="width:50px;height:30px;"/>
											</div>
										</div>

									</div>
								</div>

								<div class="grade-center">
									<div class="grade-text">
										<span>描述</span>
										<textarea></textarea>
									</div>
									<div class="costs-uploadfile-div">
										<input type="file" name="file" id="fileField"
											onchange="document.getElementById('viado').value=this.value" />
										<input type='text' id="viado"
											style="border: solid 1px #B1B1B1;" />
										<button class="allBtn costs-marl15">课程视频</button>
										<div style="margin-top: 3px;">
											<video src="" controls="controls"height:200px;width:90%;>
											</video>
										</div>
									</div>
								</div>

							</form>
							<div class="end">
								<input name="git" type="submit" value="提交"
									style="background: #5ED8A9;" /> <input name="esc" type="reset"
									value="取消" onclick="CloseDiv();formReset()" class="esc" />
							</div>


						</div>



						<div id="revise" class="resource">
							<form id="myform2">

								<div class="select-2" style="width: 100%;">
									<span>查找带回选择老师</span> <input type="text" placeholder="搜索老师名称" />
									<p
										style="position: absolute; right: 10px; top: 40px; cursor: pointer;">
										<i class="glyphicon glyphicon-search"></i>搜索
									</p>
								</div>

								<div class="admin-table">
									<table>
										<tr>
											<th>选择</th>
											<th>教师名称</th>
											<th>教师图片</th>
											<th>搜索课程</th>
										</tr>
										<tr>
											<th><input type="radio"></th>
											<th>教师名称</th>
											<th>教师图片</th>
											<th>搜索课程</th>
										</tr>
									</table>
								</div>
								<div class="fanye">

									<ul class="pagination">
										<li><a href="#">&laquo;</a></li>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">2</a></li>
										<li><a href="#">3</a></li>
										<li><a href="#">4</a></li>
										<li><a href="#">5</a></li>
										<li><a href="#">&raquo;</a></li>
									</ul>
								</div>

							</form>
							<div class="end" style="clear: both;">
								<input name="git" type="submit" value="提交"
									style="background: #5ED8A9;" /> <input name="esc" type="reset"
									value="取消" onclick="CloseDiv2();formReset2()" class="esc" />
							</div>

						</div>


					</div>

				</div>

			</div>
		</div>
		</body>
		 
		<style type="text/css">
.poop, .resource {
	overflow-y: scroll;
	position: absolute;
	left: 5%;
	top: 5%;
	width: 60%;
	height: 600px;
}

.poop span {
	font-size: 1.5rem;
}

.grade-add {
	width: 100%;
	border-top: 1px solid #F5F6F8;
	height: 80%;
	padding-top: 10px;
	margin-top: 10px;
}

.grade-left, .grade-center {
	width: 50%;
	float: left;
	height: 600px;
	overflow: auto;
}

.grade-left ul {
	height: 50px;
	line-height: 50px;
	font-size: 2rem;
}

.grade-left ul li {
	float: left;
	margin-right: 5px;
}

.grade-left ul li:nth-child(1) {
	margin-right: 10px;
}

.grade-center .grade-text {
	width: 80%;
}

.grade-center .grade-text textarea {
	width: 100%;
	height: 160px;
	font-size: 1.5rem
}

.grade-add-bottom {
	width: 100%;
	clear: both;
}

.grade-add-bottom textarea {
	width: 100%;
	height: 250px;
	background: #EDEEF0;
	border-radius: 20px;
	text-indent: 2em;
}

div.costs-uploadfile-div {
	position: relative;
}

div.costs-uploadfile-div #textfield {
	width: 40%;
	height: 30px;
}

div.costs-uploadfile-div #fileField {
	width: 100%;
	height: 30px;
	position: absolute;
	top: 0;
	left: 0;
	filter: alpha(opacity : 0);
	opacity: 0;
}

div.costs-uploadfile-div .allBtn {
	padding: 0;
	margin: 0;
	height: 30px;
	line-height: 30px;
	width: 35%;
	background-color: #18b3cf;
	border: none;
	color: #fff;
}
</style>
	
</div>
</@b.body>  
</html>
