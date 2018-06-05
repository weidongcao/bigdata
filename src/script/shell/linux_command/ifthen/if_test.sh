#!/bin/bash
# test命令提供了在if-then语句中测试不同条件的途径。如果test命令中列出的条件成立，
# test命令就会退出燕返回退出状态码0.这样if-then语句就与其他编程语言中的if-then语句
# 以类似的方式工作了，如果条件不成立，test命令就会退出并返回非零的退出状态码，
# 这使得if-then语句不会再执行。

my_var=""
if test $my_var
then
	echo "The $my_var expression returns a True"
else
	echo "The $my_var expression returns a False"
fi
