 <#import "/layout/header.ftl" as h/> 
<#import "/layout/body.ftl" as b/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="课程管理页面"/>
<link rel="stylesheet" href="/styles/admin.css" />
<script src="/scripts/course/grade-template.js"></script>
<script src="/scripts/admin.js"></script>

<@b.body menu="sidebarmenu-user" submenu="sidebarmenu-user-userinfo">
<div ng-controller="CourseClassTemplateController">
<div class="details" style="width: 100%">
	<div class="details-nav">
		<ul>
			<li><img src="/images/sjk-home.png" style="color: red;"/>我的主页</li>
			<li>/</li>
		<li>班级模板</li>
		</ul>
	</div>
<div class="details-frame" >
	<div class="details-frame-content">
<ul class="managr-dianpu">

	          <div class="select-3">
	          <span></span>
				<span><i class="glyphicon glyphicon-user">全部用户</i></span>
								
								</div>
								<div class="select-3">
									<span>昵称</span>
									<input type="text"/>
								</div>
						<div class="select-3">
							<span>手机号</span>
								<input type="text" name="search"/>
						</div>
				<div class=" select-3">
									<img src="/images/sjk-xl.png"/>
									<span>用户类型</span>
									<select ng-model="news.contentTypes">
										<option ></option>
										<option></option>
									</select>
										</div>		
			
			<div>
					<input type="button" class="btn-lg im-key"
						value="检索" />
				</div>
							</ul>
	</div>
<div class="manage">

				<div class="admin-table">

					<table>
						<tr>
							<th>账号</th>
							<th>昵称</th>
							<th>手机号</th>
							<th>用户类型</th>
                           <th>操作</th>
						</tr>

						<tr>
							<th>账号</th>
							<th>昵称</th>
							<th>手机号</th>
							<th>用户类型</th>
                           <th><form ><span>允许</span><input type="radio" value="允许" style="background:#7bc472;" name="caozuo" /><span>&nbsp;拒绝</span><input type="radio" value="拒绝"  name="caozuo"/></form></th>

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



	
			
					</div>
			
			
				</div>
		</div>

 
<style type="text/css">
 .managr-dianpu .select-3{width: 10%;margin-left: 18px;margin-right: 0;}
 .admin-table table tr form span{font-size:1.4rem;}

</style>
</@b.body>
</div>  
</div>
</html>
