#!/bin/bash
# <Linux命令行与Shell脚本编程大全> 第16章 控制脚本 
# 16.1.4 捕获脚本退出
# 要捕获Shell脚本的退出,只要在trap命令后加上EXIT信号就行.

trap "echo GoodBye..." EXIT

count=1
while [ $count -le 5 ]
do 
	echo "Loop #$count"
	sleep 1
	count=$[ $count + 1 ]
done

# 当按下Ctrl + C组合键发送SIGINT信号时,脚本就会退出了.
# 但在脚本退出前捕获到了EXIT,于是Shell执行了trap命令
