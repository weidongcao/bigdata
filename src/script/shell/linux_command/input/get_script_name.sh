#!/bin/bash
# Linux命令行与Shell脚本编程大全
# 第14章 获取脚本的名称，不包含路径

name=$(basename $0)

echo "\${name} = ${name}"
if [ $name = "dong" ];then
	total=$[ $1 + $2 ]
elif [ $name = "get_script_name.sh" ];then
	total=$[ $1 * $2 ]
fi
echo 
echo The calculated value is $total
