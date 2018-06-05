#!/bin/bash
# Bash Shell的if语句会运行if后面的那个命令。如果该命令的退出状态码是0，
# 位于then部分的命令就会被执行。如果该命令的退出状态码是其他值，
# then部分的命令就不会被执行，Bash Shell会继续执行脚本中的下一个命令。
# fi语句用来表示if-then语句至此结束


if pwd
then
	echo "It Worked"
fi

# 这个例子的if语句中故意放了一个不能工作的命令。由于这是一个错误的命令，
# 所以它会产生一个非0的退出状态码，且Bash Shell会跳出then部分的echo语句。
if IamNotaCommand
then
	echo "It Worked"
fi
echo "We are outside the if statement"

# 下面的这个例子if语句行使用grep命令在/etc/passwd文件中查找某个用户名
# 当前是否在系统上使用。如果有用户使用了登录名，脚本会显示一些文本信息
# 并列出该用户HOME目录的bash文件
# 但是，如果将testuser变量设置成一个系统上不存在的用户，则什么都不会显示。
testuser=dong
if grep ${testuser} /etc/passwd
then
	echo "This is my first command"
	echo "this is my second command"
	echo "I can evenput in other commands besides echo :"
	ls -a /home/${testuser}/.b*
	echo
else
	echo "The user $testuser does not exist on this system"
	echo
fi
