#!/bin/bash
# 测试在脚本中使用bc命令
# 可以用命令替换运行bc命令，燕将输出赋给一个变量，基本格式如下：
# variable=$(echo "options; expression" | bc)
# 第一部分options允许你设置变量。如果你需要不止一个变量，可以用分号将其分开。
# expression参数定义了通过bc执行的数学表达式。这里有个在脚本中这么做的例子。
var1=$(echo "scale=4; 3.44 /5" | bc)
echo The answer is $var1
