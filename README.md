# erp 项目管理系统

#如何启动应用
1.工程代码存放位置（git仓库）：/Users/jiayiwu/project/erp/erp/

2.在编辑器中工程空间位置：/Users/jiayiwu/project/springBoot

3.在编辑器中改好代码后，在终端中找到代码存放位置，使用git同步代码到远程仓库

4.修改application.properties中的spring.profiles.active=xxx ，xxx为生产环境prod或者测试环境test

5.在终端中找到代码存放位置，在根目录中执行 mvn package

6.此时文件将打包到target目录下，如erp-0.0.1-TEST.jar.

7.打开终端输入如下脚本，使用文件上传命令将打包好的文件上传的服务器
scp -r erp-0.0.1-TEST.jar root@10.51.36.108:/usr/local/src/prod
修改好对应的文件名称，使得下面的脚本能正常执行

8.使用终端登录服务器,执行如下命令，输入密码成功登录
ssh root@10.51.36.108

9.在服务器根目录～下面，输入执行如下脚本，启动服务即可大功告成
sh erp_prod_restart.sh
------------start------------
#!/bin/sh
set -m
#项目路径
p='/usr/local/src/prod'
#项目名
project_name='erp-0.0.1-PROD.jar'
echo 'operate restart tomcat: '$project_name
pid=`ps aux | grep $project_name | grep -v grep | awk '{print $2}'`
echo 'exist pid:'$pid

if [ -n "$pid" ]
then
{
  echo ===========shutdown================
  sleep 2
  echo ========kill tomcat begin==============
  echo $pid
  kill -9 $pid
  echo ========kill tomcat end==============
    
  echo ===========startup.sh==============
}
fi
sleep 2
#test.log为自定义日志文件
nohup java -jar ${p}'/'${project_name} > ${p}'/my.log' &
sleep 2
tail -f ${p}'/my.log'

------------end------------

10.登录网址，输入账号密码即可使用
域名：http://erp.xhmind.com 
前端地址：http://202.105.96.131:8084 
后台接口：http://202.105.96.131:8083
接口文档地址：http://202.105.96.131:8083/swagger-ui.html