#!/bin/bash
# Bash Shell的if语句会运行if后面的那个命令。如果该命令的退出状态码是0，
# 位于then部分的命令就会被执行。如果该命令的退出状态码是其他值，
# then部分的命令就不会被执行，Bash Shell会继续执行脚本中的下一个命令。
# fi语句用来表示if-then语句至此结束

# 测试else if
testuser=NoSuchUser
if grep $testuser /etc/passwd
then 
	echo "The user $testuser exists on this system."
#
elif ls -d /home/$testuser
then
	echo "The user $testuser does not exit on this system"
	echo "However, $testuser has a directory."
else
	echo "The user $testuser does not exist on this system"
	echo "And, $testuser does not have a directory"
fi	

