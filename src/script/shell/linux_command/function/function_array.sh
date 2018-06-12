#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第17章 创建函数
# 17.4.1 向函数传数组参数
# Page 367

function testit {
	local newarray
	newarray=("$@")
	echo "The new array value is: ${newarray[*]}"
}
myarray=(1 2 3 4 5)
echo "the original array is ${myarray[*]}"
testit ${myarray[*]}
