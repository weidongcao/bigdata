#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第20章 正则表达式
# 统计PATH环境变量中定义的目录里的可执行文件的数量
# 

mypath=$(echo $PATH | sed 's/:/ /g')
count=0
for directory in $mypath
do
	check=$(ls $directory)
	for item in $check
	do 
		count=$[ $count + 1 ]
	done
	echo "$directory - $count"
	count=0
done
