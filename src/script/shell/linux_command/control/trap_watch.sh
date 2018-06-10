#!/bin/bash
# <Linux命令行与Shell脚本编程大全> 第16章 控制脚本
# 16.4 作业控制
# 启动,停止,终止,恢复作业的这些功能统称为作业控制
# 通过作业控制,就能完全控制Shell环境中所有进程的运行方式了.

echo "Script Process ID: $$"

count=1
while [ $count -le 10 ]
do
	echo "$$ Loop #$count"
	sleep 20
	count=$[ $count + 1 ]
done

echo "End of script..."
