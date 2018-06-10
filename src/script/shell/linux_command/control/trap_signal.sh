#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第16章 控制脚本 
# 16.1.3 捕获信号
# 也可以不忽略信号,在信号出现时捕获它们并执行其他命令.
# trap命令允许你来指定Shell脚本要并从Shell中拦截的Linux信号.
# 如果脚本收到了trap命令中列出的信号,该信号不同由Shell处理,而是将由 本地处理
# trap命令的格式是:
# trap commands signals
# 非常简单!在trap命行上,你只要列出想要Shell执行的命令,以及一组全空格分开的待捕获的信号
# 可以用数值或Linux信号名来指定信号

trap "echo ' Sorry! I have trapped Ctrl - C'" SIGINT
echo This is a test script

count=1
while [ $count -le 10 ]
do
	echo "Loop #$count"
	sleep 1
	count=$[ $count + 1 ]
done

echo "This is the end of the test script"

# 每次使用Ctrl + C组合键,脚本都会执行trap命令中指定的echo 语句,而不是处理该信号并允许Shell停止该脚本
