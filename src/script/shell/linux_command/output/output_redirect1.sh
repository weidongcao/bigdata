#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第15章 呈现数据
# 15.2.2 永久重定向
# 可以在脚本执行过程中重定向STDOUT

exec 2> testerror
echo "This is the start of the script"
echo "now redirecting all output to another location"

# 尽管STDOUT被重定向了，但是你仍然可以将echo语句的输出发给STDERR
# 在本例中还是重定向到testerror
exec 1> testout
echo "this output should go to the testout file"
echo "but this should go to the testerror file" >&2


