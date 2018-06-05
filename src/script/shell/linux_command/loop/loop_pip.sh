#!/bin/bash
# 在Shell脚本中可以对循环的输出使用管道或进行重定向。
# 这可以通过在done命令之后添加一个处理命令来实现
# 这里对结果排序并将排序结果写入到state.txt文件中

for state in "North Dakota" Connecticut Illinois Alabama Tennessee
do
	echo "Then next place to go is $state"
done | sort > state.txt

