#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第17章 创建脚本
# 17.4.2 从函数返回数据
# Page 368
# 从函数里向Shell脚本传回数组变量也用类似的方法，函数用echo语句来按正确顺序输出单个数组值
# 然后脚本再将它们重新放进一个新的数组变量中。

function arraydblr {
	local origarray
	local newarray
	local elements
	local i
	origarray=($(echo "$@"))
	newarray=($(echo "$@"))
	elements=$[ $# -1 ]
	for (( i = 0; i <= $elements; i++ )){
		newarray[$i]=$[ ${origarray[$i]} * 2 ]
	}
	echo ${newarray[*]}
}

myarray=(1 2 3 4 5)
echo "The original array is: ${myarray[*]}"
arg1=$(echo ${myarray[*]})
result=($(arraydblr $arg1))
echo "The new array is: ${result[*]}"
