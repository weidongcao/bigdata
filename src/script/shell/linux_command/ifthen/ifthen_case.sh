#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第12章 Page 258
# 测试case命令的使用

# case 命令会将指定的变量与不同模式进行比较。
# 如果变量和模式是匹配的，那么Shell会执行为该模式指定的命令。
# 可以通过竖线操作符在一行中分隔多个匹配模式。
# 星号会捕获所有与已知模式不匹配的值。

case $USER in
dong | cwd )
	echo "Welcome, $USER"
	echo "Please enjoy your visit";;
hadoop )
	echo "Special testing account";;
jessica )
	echo "Do not forget to log off when you're done";;
* )
	echo "Sorry, you are not allowed here";;
esac
