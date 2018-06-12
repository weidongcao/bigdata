#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第17章 创建函数
# 17.4.1 向函数传数组参数
# Page 367

function addarray {
	local sum=0
	local newarray
	newarray=($(echo "$@"))
	for value in ${newarray[*]}
	do
		sum=$[ $sum + $value ]
	done
	echo $sum
}

myarray=(1 2 3 4 5)
echo "The original array is: ${myarray[*]}"
arg1=$(echo ${myarray[*]})
result=$(addarray $arg1)
echo "The result is $result"
