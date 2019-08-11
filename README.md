
############################git使用############################
--------------------------------------------------------------
https://github.com/huang-xiaofei/
username:huang-xiaofei    
pass:changjian1223

一、创建git版本控制
1.在项目根目录执行
	echo ''>.gitignore
	编辑文件:忽略的文件夹
	# Maven #
		target/
	# Eclipse #
		.settings/
		.classpath
		.project
2.执行	
	git init
	执行完该命令后会在当前目录下创建一个隐藏的. git 目录这个目录是Git 用来跟踪当前版
	本库的，切记不要轻易修改或删除该目录下的内容
3.提交项目到githup
	git add --all #提交所有
	git commit -m "first commit" #提交加注释
	git remote add origin https://github.com/huang-xiaofei/EstateProject.git #添加别名origin
	git push -u origin master #提交master分支
4.提交某个文件
	 git add README.md
	 git commit -m '提交readme'
	 git push -u origin master
	 git push  origin master


--------------------------------------------------------------
git remote add origin https://github.com/huang-xiaofei/EstateProject.git
git push -u origin master
--------------------------------------------------------------
常用指令
	git status  查看当前文件状态，是否准备提交
	git add --all  添加
	git remote remove origin  移除别名
	git remote -v  查看别名状态
二.Fork创建仓库副本，并设置与原本一致
	1.进入https://github.com/mybatis/mybatis-3.git点击右上角fork按钮
	2.下载创建的副本
		git clone https://github.com/huang-xiaofei/mybatis-3.git
	3.进入mybatis-3 目录，输入如下命令查看远程仓库
		git remote -v
	4.输入以下命令，给当前的仓库增加一个上游的仓库，上游仓库的地址从mybatis 官方仓
		库页面以相同的方式复制url		
		git remote add upstream  https://github.com/mybatis/mybatis-3.git
	5.
		git remote -v
	6.从upstream 中进行同步。
			git pull upstream master
	7.假如在自己的仓库中进行过更改，可以使用如下命令先从上游仓库将代码取回到本地，
		然后再合井提交。
		git fetch upstream

		git merge upstream/master
	8.将同步后的内容提交到自己的GitHub 仓库中
		git push origin master
