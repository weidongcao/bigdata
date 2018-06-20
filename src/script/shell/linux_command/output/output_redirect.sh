#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章 呈现数据
# 15.2.2 永久重定向
# 如果脚本中有大师数据需要重定向，那重定向每个echo语句就会很烦琐。
# 取而代之，你可以用exec命令告诉shell在脚本执行期间重定向某个特定文件描述符

exec 1> testout

echo "This is a test of redirecting all output"
echo "from a script to another file."
echo "without havingto redirect every individual line"

# exec命令会启动一个新的shell并将STDOUT文件描述符重定向到文件。
# 脚本中发给STDOUT的所有输出会被重定向到文件。
