#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》
# 第14章 处理用户输入
# 14.3 移动变量

echo 
count=1
while [ -n "$1" ]
do 
	echo "Parameter #$count = $1"
	count=$[ $count +1 ]
	shift
done
