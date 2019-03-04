<#import "/layout/header.ftl" as h/>

<!DOCTYPE html>
<html lang="en">
<@h.header title="登陆"/>
<script src="/scripts/login.js"></script>
<body ng-app="app">
 <div class="load" ng-controller="loginController">
 	<div class="load-body">
 		<form>
 			<input type="text" ng-model="username" placeholder="请输入用户名"/>
 			<input type="password" ng-model="password" placeholder="请输入密码" />
 			<input type="submit" value="登陆" ng-click="login()"/>
 		</form>
 	</div>
 </div>
</body>
 <style type="text/css">
body{overflow: hidden;}

.load{width: 100%;height: 100%;background: url(../images/load1.png) no-repeat;background-size: cover;display:flex;justify-content:center;align-items:center;}
.load-body{background: url(../images/load2.png) no-repeat;background-size: 100% 100%;width:60%;}
@media screen and (max-width: 1201px){.load-body {width:90%;}}
.load-body form{width: 50%;height: 80%;float: right;padding:16% 8% 8% 5%;margin-top:0.1rem;}
.load-body  input{width: 100%;height: 40px;border: 1px solid #B1B1B1;padding: 8%;margin-top:15px;font-size: 1.5rem;line-height: 5%;}
</style>
 </html>
