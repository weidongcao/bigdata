#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15.3章 在脚本中重定向输入
# 可以使用与脚本中重定向STDOUT和STDERR相同的方法来将STDIN从键盘重定向到其他位置
# exec命令允许你将STDIN重定向到Linux系统上的文件中。

exec 0< input_redirect.sh 
count=1

while read line
do
	echo "Line #$count: $line"
	count=$[ $count + 1 ]
done
