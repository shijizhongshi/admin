<#import "/layout/header.ftl" as h/> <#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="题库章节管理"/>
<link rel="stylesheet" href="/styles/admin.css" />
<link rel="stylesheet" href="/styles/management.css" />
<script src="/scripts/admin.js"></script>
<script src="/scripts/student/management.js"></script>
<script src="/scripts/questionBank/questionChapter.js"></script>
<@b.body menu="sidebarmenu-questionBank"
submenu="sidebarmenu-questionBank-calendar">
<div ng-controller="questionChapter">

	<div class="classify">
		<ul class="menu">

			<li ng-repeat="ctl in courseTypeList" class="list" ng-click="typeList(ctl.courseTypeName,ctl.id)">{{ctl.courseTypeName}}
				<ul class="items" ng-class="{'active':active==ctl.id}">
					<li ng-repeat="sub in courseTypeSubclass"
						ng-click="questionSub(ctl.courseTypeName,sub,$event)"
						ng-class="{'selected':typeSelected==sub.courseTypeSubclassName}">{{sub.courseTypeSubclassName}}</li>

				</ul>
				</li>

</ul>
	</div>


	<div class="details">
		<div class="details-nav">
			<ul>
				<li><img src="/images/sjk-home.png" />我的主页</li>
				<li>/</li>
				<li>题库管理</li>
				<li>/</li>
				<li>考试日历</li>
				
			</ul>
		</div>
		<div class="details-frame">

			<div class="manage">
				<ul class="show">

					<li style="background: #9DE879;"  ng-click="add()"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;添加日历</li>
					<li style="background: #F9CD33;" ng-click="update()"><span
						class="glyphicon glyphicon-pencil"></span>&nbsp;修改</li>
					<li style="background: #F86846;" ng-click="deletequestion()"><span
						class="glyphicon glyphicon-trash"></span>&nbsp;删除</li>
					
				</ul>
				<div class="admin-table">

					<table id="tableExcel">

						<tbody>
							<tr>
								<th>阶段名称</th>
								<th>学年</th>
								<th>网上报名</th>
								<th>打印准考证</th>
								<th>成绩查询</th>
							<th>领取证书</th>
							</tr>

							<tr>
								<th>卫生资格</th>
								<th>2019</th>
								<th>2月2日-3日</th>
								<th>2月2日-3日</th>
								<th>2月2日-3日</th>
							<th>2月2日-3日</th>
							</tr>


						</tbody>
					</table>
				</div>
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ul uib-pagination boundary-links="true" total-items="total"
						ng-model="current" items-per-page="pageSize" max-size="5"
						class="pagination-sm" previous-text="&lsaquo;"
						next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
						ng-click="questioncate()">
					</ul>
				</div>
				
				<!--添加修改日历-->
				<div class="poop" id="add" style="width:620px;">
					<form id="myform" >
						<h3>{添加/修改}日历</h3>
					<div style="width:260px;float:left">
						<div class="select-2">
							<span>考试名称<i class="bitian">*</i></span> <input type="text" placeholder="请输入考试名称">
						</div>
					<div class="select-2">
							<span>学年<i class="bitian">*</i></span> <input type="text" placeholder="请输入学年">
						</div>
               <div class="select-2">
							<span>网上/现场报名</span> <input type="text" placeholder="请输入时间">
						</div>
						<div class="select-2">
							<span>打印准考证</span> <input type="text" placeholder="请输入时间">
						</div>
						</div>
						<div style="width:260px;float:right">
						<div class="select-2">
							<span>成绩查询</span> <input type="text" placeholder="请输入时间">
						</div>
						<div class="select-2">
							<span>领取证书</span> <input type="text" placeholder="请输入时间">
						</div>
						<div class="select-2">
							<span>网上/现场报名</span> <input type="text" placeholder="请输入时间">
						</div>
						<div class="select-radio ">
									<ul>
										<li>日历类型</li>
										<li><input type="radio" >普通时间</li>
										<li><input type="radio" >考试时间</li>
									</ul>
								</div>
						</div>
						<div class="end">
							<input name="git" type="submit" value="提交" ng-show="cateId==null" ng-click="questioncateadd()"
								style="background: #5ED8A9;"> 
							<input name="git" type="submit" value="修改" ng-show="cateId!=null"
								ng-click="updateCategory()" style="background: #5ED8A9;"
								class="ng-hide"> 
							<input name="esc" type="reset" value="取消" ng-click="reset()" class="esc">
						</div>
					</form>
				</div>

			
			</div>

		</div>

	</div>
</div>
</div>


</@b.body>

</html>