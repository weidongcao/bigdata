#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》
# 第14章 处理用户输入 
# 14.4 处理选项

echo 

while [ -n "$1" ]
do
	case "$1" in
	-a)	echo "Found the -a option" ;;
	-b)	echo "Foune the -b option" ;;
	-c)	echo "Found the -c option" ;;
	*)	echo "$1 is not an option" ;;
	esac
	shift
done

