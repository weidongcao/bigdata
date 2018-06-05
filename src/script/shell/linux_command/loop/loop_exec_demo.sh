#!/bin/bash
# 查找环境变量Path中所有的可执行文件
# 将查找结果写入到一个文件中

IFS=:
for folder in $PATH
do
	echo "$folder:"
	for file in $folder
	do 
		if [ -x $file ]; then
			echo "	$file"
		fi
	done
done > path.txt
