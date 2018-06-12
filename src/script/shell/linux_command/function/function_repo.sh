#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第17章 创建函数
# 17.6 创建库
# Page 370
# 在用到这些函数的脚本文件中包含function_repo.sh库文件是不行的
# 问题出在Shell函数的作用域上
# 和环境变量一样，Shell函数仅在定义它的Shell会话内有效。
# 如果你在Shell命令行界面的提示符下运行function.repo.sh 脚本，
# shell会创建一个新的shell并在其中运行这个脚本
# 它会为建仓新Shell定义这三个函数，但当你运行另外一个要用到这些函数时，它们是无法使用的。
# 这同样适用于脚本。如果你尝试像普通脚本文件那样运行库文件，函数决不会出现在脚本中。

function addem {
	echo $[ $1 + $2 ]
}

function multem {
	echo $[ $1 * $2 ]
}

function divem {
	if [ $2 -ne 0 ]
	then
		echo $[ $1 / $2 ]
	else
		echo -1
	fi
}
