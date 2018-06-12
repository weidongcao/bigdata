#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第17章 创建函数
# 17.5 函数递归
# Page 369
# 局部函数变量的一个特性是自成体系。除了从脚本命令行处获得的变量，
# 自成体系的函数不需要使用外部资源
# 这个特性使得函数可以递归地调用，也就是说，函数可以调用自己来得到结果。
# 通常递归函数都有一个最终可以迭代到的基准值。
# 许多高级数学算法用递归对复杂的方程进行逐级规约
# 直到基准值定义的那级
# 递归算法的经典例子是计算阶乘。
# 一个数的阶乘是该数之前所有数自尊心该数的值。

function factorial {
	if [ $1 -eq 1 ]
	then
		echo 1
	else
		local temp=$[ $1 - 1 ]
		local result=$(factorial $temp)
		echo $[ $result * $1 ]
	fi
}

read -p "Enter value: " value
result=$(factorial $value)
echo "The factorial of $value is: $result"

