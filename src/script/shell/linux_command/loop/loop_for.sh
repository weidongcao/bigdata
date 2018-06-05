#!/bin/bash
# 《Linux命令行与Shell脚本编程大全》第13章 Page 261
# 测试for循环的循环列表

for test in Alabama Alaska Arizona California Colorado
do
	echo "The next state is $test"
done
echo "The last state we visited was $test"
test=Connecticut
echo "Wait, now we're visiting $test"
